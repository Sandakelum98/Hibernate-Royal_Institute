package lk.ijse.studentManagement.bo.custom.impl;

import lk.ijse.studentManagement.bo.custom.StudentBO;
import lk.ijse.studentManagement.dao.DAOFactory;
import lk.ijse.studentManagement.dao.DAOTypes;
import lk.ijse.studentManagement.dao.custom.StudentDAO;
import lk.ijse.studentManagement.dto.StudentDTO;
import lk.ijse.studentManagement.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);

    @Override
    public boolean add(StudentDTO s) throws Exception {
        return studentDAO.add(new Student(
                s.getId(),
                s.getStudentName(),
                s.getAddress(),
                s.getContact(),
                s.getDob(),
                s.getGender()
        ));
    }

    @Override
    public List getAll() throws Exception {
        List<Student> list = studentDAO.getAll();
        List<StudentDTO> listDto = new ArrayList<>();
        for (Student s : list) {
            StudentDTO studentDTO = new StudentDTO(
                 s.getId(),
                 s.getStudentName(),
                 s.getAddress(),
                 s.getContact(),
                 s.getDob(),
                 s.getGender()
            );
            listDto.add(studentDTO);
        }
        return listDto;
    }

    @Override
    public boolean update(StudentDTO s) throws Exception {
        return studentDAO.update(new Student(
                s.getId(),
                s.getStudentName(),
                s.getAddress(),
                s.getContact(),
                s.getDob(),
                s.getGender()
        ));
    }

    @Override
    public boolean delete(StudentDTO s) throws Exception {
        return studentDAO.delete(new Student(
                s.getId(),
                s.getStudentName(),
                s.getAddress(),
                s.getContact(),
                s.getDob(),
                s.getGender()
        ));
    }

    @Override
    public StudentDTO search(String id) throws Exception {
        Student student = studentDAO.search(id);
        StudentDTO studentDTO = new StudentDTO(
                student.getId(),
                student.getStudentName(),
                student.getAddress(),
                student.getContact(),
                student.getDob(),
                student.getGender()
        );
        return studentDTO;
    }

    @Override
    public String getNewStudentId() throws Exception {
        String lastId = studentDAO.getLastStudentId();

        int newId = Integer.parseInt(lastId.substring(1, 4))+1;
        //System.out.println("Student xxx = "+newId);

        if(newId < 10){
            return "S00"+newId;
        }else if(newId < 100){
            return "S0"+newId;
        }else{
            return "S"+newId;
        }
    }
}
