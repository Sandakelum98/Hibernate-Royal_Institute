package lk.ijse.studentManagement.dao.custom;

import lk.ijse.studentManagement.dao.SuperDAO;
import lk.ijse.studentManagement.entity.Student;

public interface StudentDAO extends SuperDAO<Student,String> {
    public String getLastStudentId() throws Exception;
}
