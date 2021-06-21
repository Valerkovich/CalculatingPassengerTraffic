package ua.cn.stu.diploma.dao;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import ua.cn.stu.diploma.domain.Train;
import ua.cn.stu.diploma.domain.Route;

import java.util.List;

public class RouteDAO {
    private Session session;

    public RouteDAO(Session session) {
        this.session = session;
    }



    public Route createRoute(Route Route) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(Route);
        transaction.commit();
        return Route;
    }

    public Route updateRoute(Route Route) {
        Transaction transaction = session.beginTransaction();
        session.merge(Route);
        transaction.commit();
        return Route;
    }

    public void deleteRoute(Route Route) {
        Transaction transaction = session.beginTransaction();
        session.delete(Route);
        transaction.commit();
    }

    public void deleteRouteById(Long RouteId) {
        Route Route = (Route) session.get(Route.class, RouteId);
        deleteRoute(Route);
    }

    public List<Route> getAllRoutes() {
        SQLQuery query = session.createSQLQuery(
                "select * from route").addEntity(Route.class);
        return (List<Route>) query.list();
    }

    public Route getRouteByTrain(Train train) {
        Criteria criteria = session.createCriteria(Route.class)
                .add(Restrictions.eq("train_table", train.getTrain_table()));
        if(criteria.list().size() == 0){
            return null;
        }
        return (Route) criteria.list().get(0);
    }


}
