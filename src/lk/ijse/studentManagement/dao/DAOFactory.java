package lk.ijse.studentManagement.dao;


import lk.ijse.studentManagement.dao.custom.RegistrationDAO;
import lk.ijse.studentManagement.dao.custom.impl.CourseDAOImpl;
import lk.ijse.studentManagement.dao.custom.impl.RegistrationDAOImpl;
import lk.ijse.studentManagement.dao.custom.impl.StudentDAOImpl;
import lk.ijse.studentManagement.entity.Course;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){
    }

    public static DAOFactory getInstance() {
        return daoFactory==null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes){
        switch (daoTypes) {
            case COURSE:
                return (T) new CourseDAOImpl();
            case STUDENT:
                return (T) new StudentDAOImpl();
            case REGISTRATION:
                return (T) new RegistrationDAOImpl();
            default:
                return null;
        }
    }
}
