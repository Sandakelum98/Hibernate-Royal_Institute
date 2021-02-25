package lk.ijse.studentManagement.util;

import lk.ijse.studentManagement.entity.Course;
import lk.ijse.studentManagement.entity.Registration;
import lk.ijse.studentManagement.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Registration.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration==null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession () {
        return sessionFactory.openSession();
    }
}
