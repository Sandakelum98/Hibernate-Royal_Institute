package lk.ijse.studentManagement.bo.custom.impl;

import lk.ijse.studentManagement.bo.custom.RegistrationBO;
import lk.ijse.studentManagement.dao.DAOFactory;
import lk.ijse.studentManagement.dao.DAOTypes;
import lk.ijse.studentManagement.dao.custom.RegistrationDAO;
import lk.ijse.studentManagement.dto.CourseDTO;
import lk.ijse.studentManagement.dto.RegistrationDTO;
import lk.ijse.studentManagement.dto.StudentDTO;
import lk.ijse.studentManagement.entity.Course;
import lk.ijse.studentManagement.entity.Registration;
import lk.ijse.studentManagement.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAO registrationDAO = DAOFactory.getInstance().getDAO(DAOTypes.REGISTRATION);

    @Override
    public boolean add(RegistrationDTO r) throws Exception {
        Student student = new Student(
                r.getStudent().getId(),
                r.getStudent().getStudentName(),
                r.getStudent().getAddress(),
                r.getStudent().getContact(),
                r.getStudent().getDob(),
                r.getStudent().getGender()
        );
        Course course = new Course(
                r.getCourse().getCode(),
                r.getCourse().getCourseName(),
                r.getCourse().getDuration(),
                r.getCourse().getCourseFee()
        );

        return registrationDAO.add(new Registration(
                r.getRegNo(),
                r.getRegDate(),
                r.getRegFee(),
                student,
                course
        ));
    }

    @Override
    public String getNewRegNo() throws Exception {
        String lastRegNo = registrationDAO.getLastRegNo();

        int newRegNo = Integer.parseInt(lastRegNo.substring(1, 4)) + 1;
        //System.out.println("xxx = "+newRegNo);

        if (newRegNo < 10) {
            return "R00" + newRegNo;
        } else if (newRegNo < 100) {
            return "R0" + newRegNo;
        } else {
            return "R" + newRegNo;
        }
    }

    @Override
    public List<RegistrationDTO> getAll() throws Exception {
        List<Registration> list = registrationDAO.getAll();
        for (Registration r : list) {
            System.out.println("ASS - "+r);
        }
        List<RegistrationDTO> listDTO = new ArrayList<>();
        for (Registration r : list) {
            StudentDTO studentDTO = new StudentDTO(
                    r.getStudent().getId(),
                    r.getStudent().getStudentName(),
                    r.getStudent().getAddress(),
                    r.getStudent().getContact(),
                    r.getStudent().getDob(),
                    r.getStudent().getGender()
            );

            CourseDTO courseDTO = new CourseDTO(
                    r.getCourse().getCode(),
                    r.getCourse().getCourseName(),
                    r.getCourse().getDuration(),
                    r.getCourse().getCourseFee()
            );

            RegistrationDTO registrationDTO = new RegistrationDTO(
                    r.getRegNo(),
                    r.getRegDate(),
                    r.getRegFee(),
                    studentDTO,
                    courseDTO
            );
            System.out.println("FUCK _ "+registrationDTO);
            listDTO.add(registrationDTO);
        }
        return listDTO;
    }
}
