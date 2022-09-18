package com.example.inte2512finalproject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LoginController implements Initializable {

	private CustomerDao customerDao = new CustomerDao();
	
	private AdminDao adminDao = new AdminDao();

	@FXML
	private TextField username;

	@FXML
	private TextField repassword;

	@FXML
	private PasswordField password;

	@FXML
	private CheckBox lbshowpassword;
	
    @FXML
    private CheckBox checkAdmin;

	@FXML
	void Login(ActionEvent event) {
		if (username.getText().equals("") || password.getText().equals("")) {
			Message.getMess("Username or password can't be blank");
		} else if (!username.getText().equals("") || !password.getText().equals("")) {
			
			if(checkAdmin.isSelected()) {
				AccountLogged.admin = adminDao.checkAdmin(username.getText(), password.getText());
				if (AccountLogged.admin != null) {
					SwitchToScene switchToScene = new SwitchToScene();
					switchToScene.switchToAnotherScene(event, "item.fxml");
					AccountLogged.role = "admin";
				} else {
					Message.getMess("login failure");
				}
				return;
			}
			
			AccountLogged.customerLogged = customerDao.checkLogin(username.getText(), password.getText());
			if (AccountLogged.customerLogged != null) {
				SwitchToScene switchToScene = new SwitchToScene();
				switchToScene.switchToAnotherScene(event, "customerForUser.fxml");
				AccountLogged.role = "user";
			} else {
				Message.getMess("login failure");
			}
		}
	}

	@FXML
	void openSignUpForm(MouseEvent event) {
		SwitchToScene switchToScene = new SwitchToScene();
		switchToScene.switchToAnotherScene(event, "regis.fxml");
	}

	@FXML
	void passKey(KeyEvent event) {
		repassword.setText(password.getText());
	}

	@FXML
	void repassKey(KeyEvent event) {
		password.setText(repassword.getText());
	}

	@FXML
	void showPass(MouseEvent event) {
		if (lbshowpassword.isSelected()) {
			repassword.setVisible(true);
			repassword.toFront();
			password.toBack();
			password.setVisible(false);
		} else {
			password.setVisible(true);
			password.toFront();
			repassword.toBack();
			repassword.setVisible(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		repassword.setVisible(false);
	}

}
