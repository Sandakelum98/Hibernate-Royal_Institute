package lk.ijse.studentManagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.ijse.studentManagement.bo.BOFactory;
import lk.ijse.studentManagement.bo.BOTypes;
import lk.ijse.studentManagement.bo.custom.StudentBO;
import lk.ijse.studentManagement.dto.StudentDTO;

import java.sql.Date;
import java.util.List;
import java.util.regex.Pattern;

public class StudentFormController {
    public TableView tblStudent;
    public TableColumn clmStudentId;
    public TableColumn clmStudentName;
    public TableColumn clmAddress;
    public TableColumn clmContact;
    public TableColumn clmDOB;
    public TableColumn clmGender;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtDOB;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public Label lblStudentID;
    public JFXComboBox cmbGender;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;


    StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    public void initialize() throws Exception {
        loadGenderCombo();
        loadAllStudents();
        getNewStudentId();
    }

    private void getNewStudentId() throws Exception {
        String id = studentBO.getNewStudentId();
        lblStudentID.setText(id);
    }

    private void loadGenderCombo() {
        ObservableList<String> genderList = FXCollections.observableArrayList();
        genderList.add("Male");
        genderList.add("Female");
        cmbGender.setItems(genderList);
    }

    private void loadAllStudents() {
        try {
            List<StudentDTO> list = studentBO.getAll();
            tblStudent.setItems(FXCollections.observableArrayList(list));

            clmStudentId.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
            clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            clmContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            clmDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
            clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clear() {
        txtStudentName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDOB.clear();
        cmbGender.getSelectionModel().clearSelection();
        btnSave.setText("Save");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String btnText = btnSave.getText();

        if (btnText.equals("Save")) {

            try {

                if (txtStudentName.getText().isEmpty() && txtAddress.getText().isEmpty() && txtContact.getText().isEmpty() && txtDOB.getText().isEmpty() && cmbGender.getSelectionModel().getSelectedItem() == null) {
                    new Alert(Alert.AlertType.WARNING, "Please fill all fields !").show();
                } else if (txtStudentName.getText().isEmpty()) {
                    txtStudentName.requestFocus();
                    txtStudentName.setFocusColor(Paint.valueOf("red"));
                } else if (txtAddress.getText().isEmpty()) {
                    txtAddress.requestFocus();
                    txtAddress.setFocusColor(Paint.valueOf("red"));
                } else if (txtContact.getText().isEmpty()) {
                    txtContact.requestFocus();
                    txtContact.setFocusColor(Paint.valueOf("red"));
                } else if (txtDOB.getText().isEmpty()) {
                    txtDOB.requestFocus();
                    txtDOB.setFocusColor(Paint.valueOf("red"));
                } else if (cmbGender.getSelectionModel().getSelectedItem() == null) {
                    cmbGender.requestFocus();
                    cmbGender.setFocusColor(Paint.valueOf("red"));
                } else {

                    if(!Pattern.compile("[A-Z]{1}[a-z]{1,} [A-Z]{1}[a-z]{1,}").matcher(txtStudentName.getText()).matches()){
                        new Alert(Alert.AlertType.WARNING,"Invalid name type !").show();
                        txtStudentName.requestFocus();
                        return;
                    }

                    if(!Pattern.compile("[A-Z]{1}[a-z]{1,}").matcher(txtAddress.getText()).matches()){
                        new Alert(Alert.AlertType.WARNING,"Invalid address type !").show();
                        txtAddress.requestFocus();
                        return;
                    }

                    if(!Pattern.compile("(0){1}[0-9]{9}").matcher(txtContact.getText()).matches()){
                        new Alert(Alert.AlertType.WARNING,"Invalid Mobile Number !").show();
                        txtContact.requestFocus();
                        return;
                    }

                    if(!Pattern.compile("[1-2]{1}[0-9]{3}(-)[0-1]{1}[0-9]{1}(-)[0-3]{1}[0-9]{1}").matcher(txtDOB.getText()).matches()){
                        new Alert(Alert.AlertType.WARNING,"Invalid DOB !").show();
                        txtDOB.requestFocus();
                        return;
                    }

                    String studentId = lblStudentID.getText();
                    String studentName = txtStudentName.getText();
                    String address = txtAddress.getText();
                    int contact = Integer.parseInt(txtContact.getText());
                    String dob = txtDOB.getText();
                    String gender = (String) cmbGender.getSelectionModel().getSelectedItem();

                    StudentDTO studentDTO = new StudentDTO(studentId, studentName, address, contact, dob, gender);

                    boolean isAdded = studentBO.add(studentDTO);

                    if (isAdded) {
                        new Alert(Alert.AlertType.WARNING, "Saved!").show();
                        clear();
                        loadAllStudents();
                        getNewStudentId();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Failed").show();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            try {

                /*if (txtStudentName.getText().isEmpty() && txtAddress.getText().isEmpty() && txtContact.getText().isEmpty() && txtDOB.getText().isEmpty() && cmbGender.getSelectionModel().getSelectedItem() == null) {
                    new Alert(Alert.AlertType.WARNING, "Please fill all fields !").show();
                } else*/
                if (txtStudentName.getText().isEmpty()) {
                    txtStudentName.requestFocus();
                    txtStudentName.setFocusColor(Paint.valueOf("red"));
                } else if (txtAddress.getText().isEmpty()) {
                    txtAddress.requestFocus();
                    txtAddress.setFocusColor(Paint.valueOf("red"));
                } else if (txtContact.getText().isEmpty()) {
                    txtContact.requestFocus();
                    txtContact.setFocusColor(Paint.valueOf("red"));
                } else if (txtDOB.getText().isEmpty()) {
                    txtDOB.requestFocus();
                    txtDOB.setFocusColor(Paint.valueOf("red"));
                } else if (cmbGender.getSelectionModel().getSelectedItem() == null) {
                    cmbGender.requestFocus();
                    cmbGender.setFocusColor(Paint.valueOf("red"));
                } else {
                    String studentId = lblStudentID.getText();
                    String studentName = txtStudentName.getText();
                    String address = txtAddress.getText();
                    int contact = Integer.parseInt(txtContact.getText());
                    String dob = txtDOB.getText();
                    String gender = (String) cmbGender.getSelectionModel().getSelectedItem();

                    StudentDTO studentDTO = new StudentDTO(studentId, studentName, address, contact, dob, gender);

                    boolean isAdded = studentBO.update(studentDTO);
                    if (isAdded) {
                        new Alert(Alert.AlertType.WARNING, "Updated !").show();
                        clear();
                        loadAllStudents();
                        getNewStudentId();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Failed !").show();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws Exception {
        clear();
        getNewStudentId();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (tblStudent.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select Student !").show();
            return;
        }
        btnSave.setText("Update");
        StudentDTO s = (StudentDTO) tblStudent.getSelectionModel().getSelectedItem();
        lblStudentID.setText(s.getId());
        txtStudentName.setText(s.getStudentName());
        txtAddress.setText(s.getAddress());
        txtContact.setText(String.valueOf(s.getContact()));
        txtDOB.setText(s.getDob());
        if (s.getGender().equals("Male")) {
            cmbGender.getSelectionModel().selectFirst();
        } else {
            cmbGender.getSelectionModel().selectLast();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        if (tblStudent.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select student to delete !").show();

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? Do you want to delete this Student ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                StudentDTO student = (StudentDTO) tblStudent.getSelectionModel().getSelectedItem();
                try {
                    boolean isDelete = studentBO.delete(student);
                    if (isDelete) {
                        new Alert(Alert.AlertType.WARNING, "Deleted !").show();
                        loadAllStudents();
                        getNewStudentId();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Failed !").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
