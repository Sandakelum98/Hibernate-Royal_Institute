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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.studentManagement.bo.BOFactory;
import lk.ijse.studentManagement.bo.BOTypes;
import lk.ijse.studentManagement.bo.custom.StudentBO;
import lk.ijse.studentManagement.dto.StudentDTO;

public class AddNewStudentFormController {
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtDOB;
    public Label lblStudentId;
    public JFXComboBox cmbGender;
    public JFXButton btnSave;
    public JFXButton btnCancel;

    StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    public void initialize () throws Exception {
        getNewStudentId();
        loadGenderCombo();
    }

    private void getNewStudentId() throws Exception {
        String id = studentBO.getNewStudentId();
        lblStudentId.setText(id);
    }
    private void clear() {
        txtStudentName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDOB.clear();
        cmbGender.getSelectionModel().clearSelection();
    }
    private void loadGenderCombo() {
        ObservableList<String> genderList = FXCollections.observableArrayList();
        genderList.add("Male");
        genderList.add("Female");
        cmbGender.setItems(genderList);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
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
                String studentId = lblStudentId.getText();
                String studentName = txtStudentName.getText();
                String address = txtAddress.getText();
                int contact = Integer.parseInt(txtContact.getText());
                String dob = txtDOB.getText();
                String gender = (String) cmbGender.getSelectionModel().getSelectedItem();

                StudentDTO studentDTO = new StudentDTO(studentId, studentName, address, contact, dob, gender);

                boolean isAdded = studentBO.add(studentDTO);

                if (isAdded) {
                    new Alert(Alert.AlertType.WARNING, "Saved!").show();
                    getNewStudentId();
                    clear();

                    Stage stage = (Stage) btnSave.getScene().getWindow();
                    stage.close();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Failed").show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        clear();
    }
}
