package ua.cn.stu.diploma.main;

import com.opencsv.bean.CsvToBeanBuilder;
import ua.cn.stu.diploma.JDBC.InsertDB;
import ua.cn.stu.diploma.dao.HibernateDAOFactory;
import ua.cn.stu.diploma.domain.PaymentCSV;
import ua.cn.stu.diploma.domain.Route;
import ua.cn.stu.diploma.domain.Train;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InitDB {
    public static void init() throws FileNotFoundException {
        initRouteToDB();
        csvToPayments("t_data.csv");
        initTrainToDB();

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<PaymentCSV> csvToPayments(String file_path) throws FileNotFoundException {
        List<PaymentCSV> beans = new CsvToBeanBuilder(new FileReader(file_path))
                .withSeparator(';').withQuoteChar('"')
                .withType(PaymentCSV.class).build().parse();
        InsertDB.addPaymentList(beans);
        return beans;
    }

    public static void initTrainToDB(){
        List<Train> bean = HibernateDAOFactory.getInstance().getPaymentDAO().getAllTrains();
        InsertDB.addTrainList(bean);
    }

    public static void initRouteToDB(){
        List<Route> routes = new ArrayList<>();
        routes.add(new Route(1,
                new int[] {2, 3, 90, 5, 6, 7, 8, 9, 10, 11},
                new int[] {7, 6, 16, 7, 3, 10, 15, 6, 6, 4},
                new int[] {5, 10},
                new int[] {16, 6}));
        routes.add(new Route(3,
                new int[] {15, 44, 35, 20, 6, 7, 27, 77, 14},
                new int[] {13, 7, 5, 7, 3, 12, 9, 13, 3},
                new int[] {27},
                new int[] {12}));
        routes.add(new Route(4,
                new int[] {19, 52, 12, 77, 14, 15, 44, 35, 51, 18},
                new int[] {14, 9, 7, 13, 3, 13, 7, 7, 14, 3},
                new int[] {12, 51},
                new int[] {14, 20}));
        routes.add(new Route(5,
                new int[] {23,24,71,20,6,7,27,36,21,22},
                new int[] {6,7,11,7,3,12,9,7,6,4},
                new int[] {21},
                new int[] {23}));
        routes.add(new Route(6,
                new int[] {26,27,36,28,72,73,29,71,58,30,25},
                new int[] {11,9,9,6,3,6,10,9,19,6,2},
                new int[] {27, 30},
                new int[] {12, 14}));
        routes.add(new Route(7,
                new int[] {19,52,58,30,25,26,27,51,18},
                new int[] {14,9,19,6,2,11,9,14,6},
                new int[] {27, 30},
                new int[] {14, 14}));
        routes.add(new Route(8,
                new int[] {19,33,40,89,25,30,31,90,62,32,18},
                new int[] {11,8,10,13,5,2,14,9,7,11,6},
                new int[] {25, 62},
                new int[] {13, 9}));
        routes.add(new Route(9,
                new int[] {15,44,8,89,54,26,90,27,77,14},
                new int[] {13,9,15,13,7,13,13,9,13,3},
                new int[] {26, 27},
                new int[] {16, 13}));
        routes.add(new Route(10,
                new int[] {15,66,37,10,3,34,77,14},
                new int[] {13,7,15,8,14,7,13,3},
                new int[] {3, 34},
                new int[] {8, 12}));
        routes.add(new Route(11,
                new int[] {3,34,65,28,72,73,29,66,37,10},
                new int[] {13,8,7,6,2,6,9,7,15,6},
                new int[] {3},
                new int[] {8}));
        for(Route route: routes){
            HibernateDAOFactory.getInstance().getRouteDAO().createRoute(route);
        }
        System.out.println("Add " + routes.size() + " routes");
    }
}
