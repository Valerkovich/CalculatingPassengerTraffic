package ua.cn.stu.diploma.dao;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import ua.cn.stu.diploma.domain.Train;
import ua.cn.stu.diploma.domain.Payment;

import java.util.List;

public class TrainDAO {
    private Session session;

    public TrainDAO(Session session) {
        this.session = session;
    }



    public Train createTrain(Train Train) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(Train);
        transaction.commit();
        return Train;
    }

    public Train updateTrain(Train Train) {
        Transaction transaction = session.beginTransaction();
        session.merge(Train);
        transaction.commit();
        return Train;
    }

    public void deleteTrain(Train Train) {
        Transaction transaction = session.beginTransaction();
        session.delete(Train);
        transaction.commit();
    }

    public void deleteTrainById(Long TrainId) {
        Train Train = (Train) session.get(Train.class, TrainId);
        deleteTrain(Train);
    }

    public List<Train> getAllTrains() {
        SQLQuery query = session.createSQLQuery(
                "select * from train").addEntity(Train.class);
        return (List<Train>) query.list();
    }

    public List<Train> getTrainsByTrainTable(int train_table) {
        Criteria criteria = session.createCriteria(Payment.class)
                .add(Restrictions.eq("train_table", train_table));
        return criteria.list();
    }
}
