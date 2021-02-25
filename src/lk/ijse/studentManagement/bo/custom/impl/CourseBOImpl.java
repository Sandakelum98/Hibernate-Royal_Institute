package lk.ijse.studentManagement.bo.custom.impl;

import lk.ijse.studentManagement.bo.custom.CourseBO;
import lk.ijse.studentManagement.dao.DAOFactory;
import lk.ijse.studentManagement.dao.DAOTypes;
import lk.ijse.studentManagement.dao.custom.CourseDAO;
import lk.ijse.studentManagement.dto.CourseDTO;
import lk.ijse.studentManagement.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOTypes.COURSE);

    @Override
    public boolean add(CourseDTO c) throws Exception {
        return courseDAO.add(new Course(
                c.getCode(),
                c.getCourseName(),
                c.getDuration(),
                c.getCourseFee()
        ));
    }

    @Override
    public List getAll() throws Exception {
        List<Course> list = courseDAO.getAll();
        List<CourseDTO> dtoList = new ArrayList<>();
        for(Course c : list){
            CourseDTO courseDTO = new CourseDTO(
                    c.getCode(),
                    c.getCourseName(),
                    c.getDuration(),
                    c.getCourseFee()
            );
            dtoList.add(courseDTO);
        }
        return dtoList;
    }

    @Override
    public boolean update(CourseDTO c) throws Exception {
        return courseDAO.update(new Course(
                c.getCode(),
                c.getCourseName(),
                c.getDuration(),
                c.getCourseFee()
        ));
    }

    @Override
    public boolean delete(CourseDTO c) throws Exception {
        return courseDAO.delete(new Course(
                c.getCode(),
                c.getCourseName(),
                c.getDuration(),
                c.getCourseFee()
        ));
    }
}
