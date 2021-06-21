package ua.cn.stu.diploma.dao;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import ua.cn.stu.diploma.domain.PassengerRatio;
import ua.cn.stu.diploma.domain.Train;
import ua.cn.stu.diploma.domain.Payment;

import java.util.List;

public class PaymentDAO {

    private Session session;

    public PaymentDAO(Session session) {
        this.session = session;
    }

    public Payment createPayment(Payment Payment) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(Payment);
        transaction.commit();
        return Payment;
    }

    public Payment updatePayment(Payment Payment) {
        Transaction transaction = session.beginTransaction();
        session.merge(Payment);
        transaction.commit();
        return Payment;
    }

    public void deletePayment(Payment Payment) {
        Transaction transaction = session.beginTransaction();
        session.delete(Payment);
        transaction.commit();
    }

    public void deletePaymentById(Long PaymentId) {
        Payment Payment = (Payment) session.get(Payment.class, PaymentId);
        deletePayment(Payment);
    }

    public List<Payment> getAllPayments() {
        SQLQuery query = session.createSQLQuery(
                "select * from payment").addEntity(Payment.class);
        return (List<Payment>) query.list();
    }

    public List<Train> getAllTrains() {
        SQLQuery query = session.createSQLQuery(
                "select row_number() OVER (ORDER BY train_table) AS id, 0 as initial_stop, train_table, shift_begin, shift_end " +
                        "from payment " +
                        "group by train_table, shift_begin, shift_end " +
                        "order by train_table, shift_begin ").addEntity(Train.class);
        return (List<Train>) query.list();
    }

    public List<Payment> getPaymentsByTrain(Train train) {
        Criteria criteria = session.createCriteria(Payment.class)
                .add(Restrictions.eq("shift_begin", train.getShift_begin()))
                .add(Restrictions.eq("train_table", train.getTrain_table()));
        return criteria.list();
    }

    public List<Payment> getPaymentsByTrainTable(int train_table) {
        Criteria criteria = session.createCriteria(Payment.class)
                .add(Restrictions.eq("train_table", train_table));
        return criteria.list();
    }

    public List<PassengerRatio> getPassengerRatio() {
        SQLQuery query = session.createSQLQuery(
                "select  (row_number() OVER (ORDER BY common_type.train_table)) + 100000 AS id," +
                        "((CAST(privilege_type.avenger as float)" +
                        "/CAST((common_type.avenger+privilege_type.avenger) as float))* 100) as ratio, " +
                        "common_type.day_of_week, common_type.train_table\n" +
                        "from \n" +
                        "\t(select COUNT(*) AS avenger, day_of_week, train_table\n" +
                        "\tfrom payment\n" +
                        "\twhere type_ticket = 0\n" +
                        "\tgroup by day_of_week, train_table\n" +
                        "\torder by train_table) as common_type,\n" +
                        "\t\n" +
                        "\t(select COUNT(*) AS avenger, day_of_week, train_table\n" +
                        "\tfrom payment\n" +
                        "\twhere type_ticket = 1\n" +
                        "\tgroup by day_of_week, train_table\n" +
                        "\torder by train_table) as privilege_type\n" +
                        "\t\n" +
                        "where common_type.train_table = privilege_type.train_table\n" +
                        "\t\tand common_type.day_of_week = privilege_type.day_of_week").addEntity(PassengerRatio.class);
        return query.list();
    }


}
