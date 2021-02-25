package lk.ijse.studentManagement.bo.custom;

import lk.ijse.studentManagement.bo.SuperBO;
import lk.ijse.studentManagement.dto.CourseDTO;
import lk.ijse.studentManagement.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean add(StudentDTO s) throws Exception;
    public List getAll () throws Exception;
    public boolean update (StudentDTO s) throws Exception;
    public boolean delete (StudentDTO s) throws Exception;
    public StudentDTO search (String id) throws Exception;
    public String getNewStudentId() throws Exception;
}
