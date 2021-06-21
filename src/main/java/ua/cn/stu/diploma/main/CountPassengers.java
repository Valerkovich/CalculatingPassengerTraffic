package ua.cn.stu.diploma.main;

import ua.cn.stu.diploma.JDBC.InsertDB;
import ua.cn.stu.diploma.dao.HibernateDAOFactory;
import ua.cn.stu.diploma.domain.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CountPassengers {

    public static void countPassengersEveryStop() throws ParseException {
        List<Stop> stopList = new ArrayList<>();

        int indexCurrentStop, amount_passengers = 0;
        Long currentTime, endTime;

        Route route = null;

        List<Payment> paymentList;
        List<Train> trainList = HibernateDAOFactory.getInstance().getTrainDAO().getAllTrains();

        for (Train train : trainList) {


            paymentList = HibernateDAOFactory.getInstance().getPaymentDAO().getPaymentsByTrain(train);

            //Данные с которыми будем работать
            indexCurrentStop = train.getInitial_stop();
            currentTime = train.getShift_begin();
            endTime = train.getShift_end();

            //имеется ли данные о маршруте данного тролейбуса
            if(route == null || route.getTrain_table() != train.getTrain_table()) {
                route = HibernateDAOFactory.getInstance().getRouteDAO().getRouteByTrain(train);
                if(route == null){
                    continue;
                }
            }

            //Выезд с депо к начальной остановке
            currentTime = addMinutesToUnix(route.getInitial_time()[indexCurrentStop], currentTime);

            //Получаем индекс начальной остановки
            for (int i = 0; i < route.getStops().length; i++) {
                if (route.getStops()[i] == route.getInitial_stops()[indexCurrentStop]) {
                    indexCurrentStop = i;
                    break;
                }
            }
            //движемся по маршруту и считаем пассажиров
            while (currentTime < endTime) {

                for(Payment payment: paymentList){
                    if(payment.getDate_of() > currentTime &&
                            payment.getDate_of() < addMinutesToUnix(route.getTime()[indexCurrentStop], currentTime)){
                        amount_passengers++;
                    }
                }

                stopList.add(new Stop(route.getStops()[indexCurrentStop],
                        route.getTrain_table(),
                        train.getShift_begin(),
                        currentTime,
                        amount_passengers,
                        Integer.parseInt(new SimpleDateFormat("HH").format(new Date(currentTime)))));

                amount_passengers = 0;
                currentTime = addMinutesToUnix(route.getTime()[indexCurrentStop], currentTime);
                indexCurrentStop++;
                if(indexCurrentStop >= route.getStops().length){
                    indexCurrentStop = 0;
                }
            }
        }
        InsertDB.addStopList(stopList);
        stopList.clear();

    }

    public static void averageCountPassenger(){

        List<Route> routeList = HibernateDAOFactory.getInstance().getRouteDAO().getAllRoutes();

        int count = 0;

        for(Route route: routeList){
            int[] stops = route.getStops();

            for (int stop : stops) {
                float averagePassengers = HibernateDAOFactory.getInstance().getStopDAO()
                        .getAveragePassengerByTrainTableAndNumberStop(route.getTrain_table(), stop);

                HibernateDAOFactory.getInstance().getAverageCountPassengerDAO()
                        .createAverageCountPassenger(
                                new AverageCountPassenger(
                                        stop,
                                        route.getTrain_table(),
                                        (int) Math.ceil(averagePassengers)
                                )
                        );
                count++;
            }
        }

        System.out.println("Add " + count + " averageCountPassenger");
    }

    public static void countPassengerRatioWithPrivilege(){

        int count = 0;
        List<Route> routeList = HibernateDAOFactory.getInstance().getRouteDAO().getAllRoutes();
        List<PassengerRatio> passengerRatio =
                HibernateDAOFactory.getInstance().getPaymentDAO().getPassengerRatio();
        for (PassengerRatio ratio: passengerRatio) {
            for (Route route: routeList) {
                if(route.getTrain_table() == ratio.getTrain_table()) {
                    HibernateDAOFactory.getInstance().getPassengerRatioDAO().createPassengerRatio(
                            new PassengerRatio(ratio.getRatio(), ratio.getDay_of_week(), ratio.getTrain_table())
                    );

                    count++;
                }
            }
        }
        System.out.println("Add " + count + " PassengerRatio");
    }

    private static Long addMinutesToUnix(int minutes, Long beforeTime){
        final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

        return beforeTime + (minutes * ONE_MINUTE_IN_MILLIS);
    }
}
