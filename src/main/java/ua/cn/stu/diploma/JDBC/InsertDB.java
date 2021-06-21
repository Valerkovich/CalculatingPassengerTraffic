package ua.cn.stu.diploma.JDBC;

import ua.cn.stu.diploma.domain.PaymentCSV;
import ua.cn.stu.diploma.domain.Stop;
import ua.cn.stu.diploma.domain.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class InsertDB {

    public static void addPaymentList(List<PaymentCSV> paymentList) {


        String sqlInsert = "Insert into payment " +
                "(date_of, shift_begin, shift_end, train_table, type_ticket, day_of_week) values (?,?,?,?,?,?)";
        try (
                Connection conn = Connect.getConnect();
                PreparedStatement st = conn.prepareStatement(sqlInsert);
             ){
            int i = 0;
            for (PaymentCSV payment : paymentList) {
                if(payment.getTrain_table() < 12) {
                    int day_of_week = Integer.parseInt(new SimpleDateFormat("u")
                            .format(stringToDate(payment.getDate_of())));

                    st.setLong(1, stringToDate(payment.getDate_of()).getTime());
                    st.setLong(2, stringToDate(payment.getShift_begin()).getTime());
                    st.setLong(3, stringToDate(payment.getShift_end()).getTime());
                    st.setInt(4, payment.getTrain_table());
                    st.setInt(5, setTypeTicket(day_of_week));
                    st.setInt(6, day_of_week);
                    st.addBatch();

                    i++;
                    if (i % 1000 == 0 || i == paymentList.size()) {
                        st.executeBatch();
                    }
                }
            }
            System.out.println("Add " + paymentList.size() + " payments");
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void addTrainList(List<Train> trainList) {
        String sqlInsert = "Insert into train " +
                "(initial_stop, shift_begin, shift_end, train_table) values (?,?,?,?)";
        try (
                Connection conn = Connect.getConnect();
                PreparedStatement st = conn.prepareStatement(sqlInsert);
        ){
            int i = 0;
            for (Train train : trainList) {

                st.setInt(1, train.getInitial_stop());
                st.setLong(2, train.getShift_begin());
                st.setLong(3, train.getShift_end());
                st.setInt(4, train.getTrain_table());
                st.addBatch();

                i++;
                if(i % 1000 == 0 || i == trainList.size()){
                    st.executeBatch();
                }
            }
            System.out.println("Add " + trainList.size() + " trains");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void addStopList(List<Stop> stopList){
        String sqlInsert = "Insert into stop " +
                "(stop_number, train_table, train_shift_begin, arrival," +
                " amount_passengers, hour) " +
                "values (?,?,?,?,?,?)";
        try (
                Connection conn = Connect.getConnect();
                PreparedStatement st = conn.prepareStatement(sqlInsert);
        ){
            int i = 0;
            for (Stop stop : stopList) {

                st.setInt(1, stop.getStop_number());
                st.setInt(2, stop.getTrain_table());
                st.setLong(3, stop.getTrain_shift_begin());
                st.setLong(4, stop.getArrival());
                st.setInt(5, stop.getAmount_passengers());
                st.setInt(6, stop.getHour());
                st.addBatch();

                i++;
                if(i % 1000 == 0 || i == stopList.size()){
                    st.executeBatch();
                }
            }
            System.out.println("Add " + stopList.size() + " stops");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    private static int setTypeTicket(int day_of_week){
        Random rnd = new Random();
        if(day_of_week < 6){
            if(rnd.nextInt(5) == 1){
                return 1;
            } else return 0;
        } else {
            if(rnd.nextInt(20) == 1){
                return 1;
            } else return 0;
        }


    }

    private static Date stringToDate(String sDate) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(sDate);
    }
}
