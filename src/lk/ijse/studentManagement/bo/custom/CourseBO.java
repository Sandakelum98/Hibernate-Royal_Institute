package lk.ijse.studentManagement.bo.custom;

import lk.ijse.studentManagement.bo.SuperBO;
import lk.ijse.studentManagement.dto.CourseDTO;
import lk.ijse.studentManagement.entity.Course;

import java.util.List;

public interface CourseBO extends SuperBO {
    public boolean add(CourseDTO c) throws Exception;
    public List getAll () throws Exception;
    public boolean update (CourseDTO c) throws Exception;
    public boolean delete (CourseDTO c) throws Exception;


}
