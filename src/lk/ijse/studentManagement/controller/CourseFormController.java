package lk.ijse.studentManagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.studentManagement.bo.BOFactory;
import lk.ijse.studentManagement.bo.BOTypes;
import lk.ijse.studentManagement.bo.custom.CourseBO;
import lk.ijse.studentManagement.dto.CourseDTO;

import java.util.List;
import java.util.Optional;

public class CourseFormController {
    public TableView tblCourses;
    public TableColumn clmCode;
    public TableColumn clmCourseName;
    public TableColumn clmDuration;
    public TableColumn clmCourseFee;
    public JFXTextField txtCourseID;
    public JFXTextField txtCourseName;
    public JFXTextField txtDuration;
    public JFXTextField txtCourseFee;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;

    CourseBO courseBO = BOFactory.getInstance().getBO(BOTypes.COURSE);

    public void initialize() {
        loadAllCourses();
    }

    private void loadAllCourses() {
        try {
            List<CourseDTO> list = courseBO.getAll();
            tblCourses.setItems(FXCollections.observableArrayList(list));

            clmCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            clmCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
            clmDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            clmCourseFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String btnText = btnSave.getText();

        if (btnText.equals("Save")) {
            try {
                String code = txtCourseID.getText();
                String courseName = txtCourseName.getText();
                String duration = txtDuration.getText();


                if (code.length() == 0 || courseName.length() == 0 || duration.length() == 0 || txtCourseFee.getText().isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, "Please fill all fields").show();
                } else {
                    double courseFee = Double.parseDouble(txtCourseFee.getText());

                    CourseDTO courseDTO = new CourseDTO(code, courseName, duration, courseFee);

                    boolean isAdded = courseBO.add(courseDTO);

                    if (isAdded) {
                        new Alert(Alert.AlertType.WARNING, "Saved !").show();
                        clear();
                        loadAllCourses();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Failed !").show();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            try {
                String code = txtCourseID.getText();
                String courseName = txtCourseName.getText();
                String duration = txtDuration.getText();
                double courseFee = Double.parseDouble(txtCourseFee.getText());

                CourseDTO courseDTO = new CourseDTO(code, courseName, duration, courseFee);

                boolean isAdded = courseBO.update(courseDTO);

                if (isAdded) {
                    new Alert(Alert.AlertType.WARNING, "Updated !").show();
                    clear();
                    loadAllCourses();
                    btnSave.setText("Save");
                    txtCourseID.setEditable(true);
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

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (tblCourses.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select Course !").show();
            return;
        }

        btnSave.setText("Update");

        CourseDTO c = (CourseDTO) tblCourses.getSelectionModel().getSelectedItem();
        txtCourseID.setText(c.getCode());
        txtCourseName.setText(c.getCourseName());
        txtDuration.setText(c.getDuration());
        txtCourseFee.setText(String.valueOf(c.getCourseFee()));

        txtCourseID.setEditable(false);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (tblCourses.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select Course !").show();

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ? Do you want to delete this course ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                CourseDTO courseDTO = (CourseDTO) tblCourses.getSelectionModel().getSelectedItem();
                try {
                    boolean isDelete = courseBO.delete(courseDTO);
                    if (isDelete) {
                        new Alert(Alert.AlertType.WARNING, "Deleted !").show();
                        loadAllCourses();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Failed !").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void clear() {
        txtCourseID.clear();
        txtCourseName.clear();
        txtDuration.clear();
        txtCourseFee.clear();
        btnSave.setText("Save");
    }
}
