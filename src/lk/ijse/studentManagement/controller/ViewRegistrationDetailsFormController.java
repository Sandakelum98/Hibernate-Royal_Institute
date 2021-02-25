package lk.ijse.studentManagement.controller;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.studentManagement.bo.BOFactory;
import lk.ijse.studentManagement.bo.BOTypes;
import lk.ijse.studentManagement.bo.custom.RegistrationBO;
import lk.ijse.studentManagement.dto.RegistrationDTO;

import java.util.List;

public class ViewRegistrationDetailsFormController {

    public TableView tblRegistrationDetail;
    public TableColumn clmRegNo;
    public TableColumn clmRegDate;
    public TableColumn clmStudentId;
    public TableColumn clmCourseCode;
    public TableColumn clmRegFee;

    RegistrationBO registrationBO = BOFactory.getInstance().getBO(BOTypes.REGISTRATION);

    public void initialize () {
        //loadRegDetails();
    }

    private void loadRegDetails() {
        try {
            List<RegistrationDTO> list = registrationBO.getAll();
            tblRegistrationDetail.setItems(FXCollections.observableArrayList(list));

            clmRegNo.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
