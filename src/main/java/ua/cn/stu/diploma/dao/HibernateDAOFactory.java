package ua.cn.stu.diploma.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import ua.cn.stu.diploma.domain.*;

public class HibernateDAOFactory {

    private static HibernateDAOFactory instance;

    private Session session;

    private PaymentDAO paymentDAO;
    private RouteDAO routeDAO;
    private TrainDAO trainDAO;
    private StopDAO stopDAO;
    private PassengerRatioDAO passengerRatioDAO;
    private AverageCountPassengerDAO averageCountPassengerDAO;

    //Ініціалізація синглетону
    public static HibernateDAOFactory getInstance() {
        if (null == instance) {
            instance = new HibernateDAOFactory();
        }
        return instance;
    }

    //Створення обєкта Session для взаємодії з Hibernate
    private Session getSession() {
        if (null == session) {
            Configuration configuration = new Configuration();
            configuration.setProperty(Environment.DRIVER, "org.postgresql.Driver");
            configuration.setProperty(Environment.URL,
                    "jdbc:postgresql://localhost:5432/Diploma");
            configuration.setProperty(Environment.USER, "postgres");
            configuration.setProperty(Environment.PASS, "12061996");
            configuration.setProperty(Environment.DIALECT,
                    "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty(Environment.HBM2DDL_AUTO, "create");
            configuration.setProperty(Environment.SHOW_SQL, "false");
            configuration.addAnnotatedClass(Train.class);
            configuration.addAnnotatedClass(Route.class);
            configuration.addAnnotatedClass(Payment.class);
            configuration.addAnnotatedClass(Stop.class);
            configuration.addAnnotatedClass(PassengerRatio.class);
            configuration.addAnnotatedClass(AverageCountPassenger.class);
            StandardServiceRegistryBuilder serviceRegistryBuilder =
                    new StandardServiceRegistryBuilder();
            serviceRegistryBuilder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            SessionFactory sessionFactory =
                    configuration.buildSessionFactory(serviceRegistry);
            session = sessionFactory.openSession();
        }
        return session;
    }

    public PaymentDAO getPaymentDAO() {
        if (null == paymentDAO) {
            paymentDAO = new PaymentDAO(getSession());
        }
        return paymentDAO;
    }

    public RouteDAO getRouteDAO() {
        if (null == routeDAO) {
            routeDAO = new RouteDAO(getSession());
        }
        return routeDAO;
    }

    public TrainDAO getTrainDAO() {
        if (null == trainDAO) {
            trainDAO = new TrainDAO(getSession());
        }
        return trainDAO;
    }

    public StopDAO getStopDAO(){
        if (null == stopDAO){
            stopDAO = new StopDAO(getSession());
        }

        return stopDAO;
    }


    public AverageCountPassengerDAO getAverageCountPassengerDAO(){
        if(null == averageCountPassengerDAO){
            averageCountPassengerDAO = new AverageCountPassengerDAO(getSession());
        }

        return averageCountPassengerDAO;
    }

    public PassengerRatioDAO getPassengerRatioDAO(){
        if(null == passengerRatioDAO){
            passengerRatioDAO = new PassengerRatioDAO(getSession());
        }
        return passengerRatioDAO;
    }

    public void closeSession(){
        session.close();
    }
}
