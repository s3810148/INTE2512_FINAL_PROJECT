package com.example.inte2512finalproject;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegisController implements Initializable{
	
	private CustomerDao customerDao = new CustomerDao();
	
	@FXML
	private TextField name;

	@FXML
	private TextField address;

	@FXML
	private TextField phone;

	@FXML
	private TextField username;

	@FXML
	private TextField password;

	@FXML
	void backToLogin(MouseEvent event) {
		SwitchToScene switchToScene = new SwitchToScene();
		switchToScene.switchToAnotherScene(event, "login.fxml");
	}

	@FXML
	void regis(ActionEvent event) {
		Customer customer = new Customer(name.getText(), address.getText(), phone.getText(), username.getText(), password.getText());
		if(customerDao.regis(customer) == 1) {
			Message.getMess("Account already exists");
		}
		else {
			Message.getMess("Regis successful");
			clearInput();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void clearInput() {
		username.setText("");
		password.setText("");
		phone.setText("");
		address.setText("");
		name.setText("");
	}
}
