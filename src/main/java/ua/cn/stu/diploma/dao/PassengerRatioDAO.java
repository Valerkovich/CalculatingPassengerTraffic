package ua.cn.stu.diploma.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.cn.stu.diploma.domain.PassengerRatio;

public class PassengerRatioDAO {

    private Session session;

    public PassengerRatioDAO(Session session) {
        this.session = session;
    }

    public PassengerRatio createPassengerRatio(PassengerRatio ratio) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(ratio);
        transaction.commit();
        return ratio;
    }



}
