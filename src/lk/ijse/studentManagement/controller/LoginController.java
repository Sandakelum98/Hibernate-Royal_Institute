package lk.ijse.studentManagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
    public JFXTextField txtUserName;
    public JFXButton btnCancel;
    public JFXButton btnLogin;
    public JFXPasswordField psdPassword;
    public JFXTextField txtPassword;
    public ImageView imgEye;
    public ImageView imgEyeClose;


    public void initialize() throws Exception {
        txtPassword.setVisible(false);
        imgEyeClose.setVisible(false);
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        try {

            if (username.equals("ijse") & password.equals("1234")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/ijse/studentManagement/view/Dashboard.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                Stage stage1 = (Stage) btnLogin.getScene().getWindow();
                stage1.close();

            } else if (!username.equals("ijse")) {
                new Alert(Alert.AlertType.WARNING, "Wrong username").show();
                txtUserName.clear();
            } else if (!password.equals("1234")) {
                new Alert(Alert.AlertType.WARNING, "Wrong Password").show();
                txtPassword.clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Wrong Username & Password").show();
                txtPassword.clear();
                txtUserName.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        txtPassword.clear();
        txtUserName.clear();
    }

    public void imgEyeOnAction(MouseEvent mouseEvent) {
        psdPassword.setVisible(false);
        txtPassword.setVisible(true);
        txtPassword.setText(psdPassword.getText());
        imgEyeClose.setVisible(true);
        imgEye.setVisible(false);
    }

    public void imgEyeCloseOnAction(MouseEvent mouseEvent) {
        txtPassword.setVisible(false);
        psdPassword.setVisible(true);
        psdPassword.setText(txtPassword.getText());
        imgEyeClose.setVisible(false);
        imgEye.setVisible(true);
    }
}
