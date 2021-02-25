package lk.ijse.studentManagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.studentManagement.bo.BOFactory;
import lk.ijse.studentManagement.bo.BOTypes;
import lk.ijse.studentManagement.bo.custom.CourseBO;
import lk.ijse.studentManagement.bo.custom.RegistrationBO;
import lk.ijse.studentManagement.bo.custom.StudentBO;
import lk.ijse.studentManagement.dto.CourseDTO;
import lk.ijse.studentManagement.dto.RegistrationDTO;
import lk.ijse.studentManagement.dto.StudentDTO;
import org.jvnet.staxex.BinaryText;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


public class RegistrationFormController {
    public Label lblStudentId;
    public Label lblDate;
    public JFXButton btnCancel;
    public JFXButton btnRegister;
    public JFXComboBox cmbCourseName;
    public TextField txtRegFee;
    public Label lblStudentName;
    public JFXButton btnSearch;
    public Label lblAddress;
    public Label lblContact;
    public JFXButton btnNew;
    public Label lblRegNo;
    public TextField txtStudentId;
    public JFXButton btnViewRegistration;

    private StudentDTO selectedStudent;
    private List<CourseDTO> courses;

    StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    CourseBO courseBO = BOFactory.getInstance().getBO(BOTypes.COURSE);
    RegistrationBO registrationBO = BOFactory.getInstance().getBO(BOTypes.REGISTRATION);

    public void initialize() {
        loadCourses();
        generateDate();
        getNewRegNo();
        //btnViewRegistration.setVisible(false);
    }

    private void loadCourses() {
        try {
            courses = courseBO.getAll();
            ObservableList<String> coursesName = FXCollections.observableArrayList();
            for (CourseDTO c : courses) {
                coursesName.add(c.getCourseName());
            }
            cmbCourseName.setItems(coursesName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        lblDate.setText(dateFormat.format(date));
    }

    private void getNewRegNo() {
        try {
            String newRegNo = registrationBO.getNewRegNo();
            lblRegNo.setText(newRegNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void txtStudentIdOnAction(ActionEvent actionEvent) {
        btnSearchOnAction(actionEvent);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        if (txtStudentId.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Enter Student ID !").show();
        } else {

            try {
                String id = txtStudentId.getText();

                selectedStudent = studentBO.search(id);

                lblStudentId.setText(selectedStudent.getId());
                lblStudentName.setText(selectedStudent.getStudentName());
                lblAddress.setText(selectedStudent.getAddress());
                lblContact.setText(String.valueOf(selectedStudent.getContact()));

            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING, "Invalid Student ID !").show();
            }
        }
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/ijse/studentManagement/view/AddNewStudentForm.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {

        if (lblStudentId.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select student !").show();
            txtStudentId.requestFocus();
        } else if (cmbCourseName.getSelectionModel().getSelectedItem() == null) {
            cmbCourseName.requestFocus();
            cmbCourseName.setFocusColor(Paint.valueOf("red"));
        } else if (txtRegFee.getText().isEmpty()) {
            txtRegFee.requestFocus();
        } else {

            try {

                if(!Pattern.compile("[0-9]{1,}(.)(0)(0)").matcher(txtRegFee.getText()).matches()){
                    new Alert(Alert.AlertType.WARNING,"Invalid Registration Fee !").show();
                    txtRegFee.requestFocus();
                    return;
                }

                String regNo = lblRegNo.getText();
                String regdate = lblDate.getText();
                double regFee = Double.parseDouble(txtRegFee.getText());

                String code = "No";
                CourseDTO selectedCourseOb = null;
                String selectedCourseName = (String) cmbCourseName.getSelectionModel().getSelectedItem();

                for (CourseDTO c : courses) {
                    String courseName = c.getCourseName();
                    code = c.getCode();
                    selectedCourseOb = c;

                    if (selectedCourseName.equals(courseName)) {
                        break;
                    }
                }

            /*System.out.println(selectedStudent);
            //System.out.println(code);
            System.out.println(selectedCourseOb);*/

                RegistrationDTO registrationDTO = new RegistrationDTO(regNo, regdate, regFee, selectedStudent, selectedCourseOb);

                boolean isAdded = registrationBO.add(registrationDTO);
                if (isAdded) {
                    new Alert(Alert.AlertType.WARNING, "Registered !").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Failed !").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        clear();
    }

    private void clear() {
        txtStudentId.clear();
        lblStudentId.setText("");
        lblStudentName.setText("");
        lblAddress.setText("");
        lblContact.setText("");
        cmbCourseName.getSelectionModel().clearSelection();
        txtRegFee.clear();
    }


    public void btnViewRegistrationOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/ijse/studentManagement/view/ViewRegistrationDetailsForm.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
