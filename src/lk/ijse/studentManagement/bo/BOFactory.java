package lk.ijse.studentManagement.bo;

import lk.ijse.studentManagement.bo.custom.CourseBO;
import lk.ijse.studentManagement.bo.custom.impl.CourseBOImpl;
import lk.ijse.studentManagement.bo.custom.impl.RegistrationBOImpl;
import lk.ijse.studentManagement.bo.custom.impl.StudentBOImpl;
import lk.ijse.studentManagement.dao.custom.impl.CourseDAOImpl;
import lk.ijse.studentManagement.entity.Course;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory () {

    }

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO>T getBO (BOTypes boTypes) {
        switch (boTypes) {
            case COURSE:
                return (T) new CourseBOImpl();
            case STUDENT:
                return (T) new StudentBOImpl();
            case REGISTRATION:
                return (T) new RegistrationBOImpl();
            default:
                return null;
        }
    }
}
