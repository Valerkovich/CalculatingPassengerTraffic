package ua.cn.stu.diploma.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.cn.stu.diploma.domain.AverageCountPassenger;

public class AverageCountPassengerDAO {

    private Session session;

    public AverageCountPassengerDAO(Session session) {
        this.session = session;
    }

    public AverageCountPassenger createAverageCountPassenger(AverageCountPassenger average) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(average);
        transaction.commit();
        return average;
    }
}
