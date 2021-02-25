package lk.ijse.studentManagement.dao.custom.impl;

import lk.ijse.studentManagement.dao.custom.RegistrationDAO;
import lk.ijse.studentManagement.entity.Registration;
import lk.ijse.studentManagement.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean add(Registration entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Registration entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Registration entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(entity);

        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public Registration search(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from registration where id = ?1");
        query.setParameter(1, s);
        Registration registration = (Registration) query.uniqueResult();

        transaction.commit();
        session.close();
        return registration;
    }

    @Override
    public List<Registration> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from registration");
        List<Registration> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String getLastRegNo() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("select regNo from registration order by regNo desc limit 1");
        String regNo = (String) sqlQuery.uniqueResult();

        transaction.commit();
        session.close();
        return regNo;
    }
}
