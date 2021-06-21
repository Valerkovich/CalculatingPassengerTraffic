package ua.cn.stu.diploma.dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.cn.stu.diploma.domain.Stop;

import java.util.List;

public class StopDAO {
    private Session session;

    public StopDAO(Session session) {
        this.session = session;
    }



    public Stop createStop(Stop stop) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(stop);
        transaction.commit();
        return stop;
    }

    public Stop updateStop(Stop stop) {
        Transaction transaction = session.beginTransaction();
        session.merge(stop);
        transaction.commit();
        return stop;
    }

    public void deleteStop(Stop stop) {
        Transaction transaction = session.beginTransaction();
        session.delete(stop);
        transaction.commit();
    }

    public void deleteStopById(Long stopId) {
        Stop stop = (Stop) session.get(Stop.class, stopId);
        deleteStop(stop);
    }

    public List<Stop> getAllStops() {
        SQLQuery query = session.createSQLQuery(
                "select * from train").addEntity(Stop.class);
        return (List<Stop>) query.list();
    }

    public float getAveragePassengerByTrainTableAndNumberStop(int train_table, int stop_number) {
        SQLQuery query = session.createSQLQuery("SELECT AVG(amount_passengers) FROM stop " +
                "WHERE train_table = " + train_table +
                " and stop_number = " + stop_number +
                "and amount_passengers != 0");
        return Float.parseFloat(query.list().get(0).toString());
    }

    public float getAveragePassengerByTrainTableAndTypeTicket(int train_table, int stop_number) {
        SQLQuery query = session.createSQLQuery("SELECT AVG(amount_passengers) FROM stop " +
                "WHERE train_table = " + train_table +
                " and stop_number = " + stop_number +
                "and amount_passengers != 0");
        return Float.parseFloat(query.list().get(0).toString());
    }
}
