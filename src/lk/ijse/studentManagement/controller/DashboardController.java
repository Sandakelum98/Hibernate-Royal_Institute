package lk.ijse.studentManagement.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {
    public AnchorPane content;
    public AnchorPane paneStudent;
    public AnchorPane paneCourse;
    public AnchorPane paneRegistration;

    public void initialize() throws IOException {
        loadDefault();
    }

    private void loadDefault() throws IOException {
        setUi("StudentForm");
    }

    public void setUi(String name) throws IOException {
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.
                load(this.getClass().
                        getResource("/lk/ijse/studentManagement/view/" + name + ".fxml")));
    }

    public void paneRegistrationMousecCickAction(MouseEvent mouseEvent) throws IOException {
        setUi("RegistrationForm");
    }

    public void paneStudentMousecCickAction(MouseEvent mouseEvent) throws IOException {
        setUi("StudentForm");
    }

    public void paneCourseMousecCickAction(MouseEvent mouseEvent) throws IOException {
        setUi("CourseForm");
    }
}
