package com.example.inte2512finalproject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;


public class CustomerForUserController implements Initializable{
	
	private ObservableList<Items> listItemOb = FXCollections.observableArrayList();
	
	private SwitchToScene sw = new SwitchToScene();
	
	private ItemDao itemDao = new ItemDao();
	
	@FXML
	private Label lb_loginAs;

	@FXML
	private TextField txt_search;

	@FXML
	private TableView<Items> table;

	@FXML
	private TableColumn<Items, String> col_id;

	@FXML
	private TableColumn<Items, String> col_title;

	@FXML
	private TableColumn<Items, String> col_type;

	@FXML
	private TableColumn<Items, String> col_subType;

	@FXML
	private TableColumn<Items, String> col_loantype;

	@FXML
	private TableColumn<Items, Float> col_fee;

	@FXML
	void logout(ActionEvent event) {
		sw.switchToAnotherScene(event, "login.fxml");
	}

	@FXML
	void searchItem(KeyEvent event) {

	}

	@FXML
	void switchToItemManager(ActionEvent event) {
		
	}

	@FXML
	void switchToListItem(ActionEvent event) {
		sw.switchToAnotherScene(event, "ItemForUser.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lb_loginAs.setText("Login as: "+ AccountLogged.customerLogged.getUsername());
		setInitTable();
	}
	
	public void setInitTable() {
		listItemOb.clear();
		itemDao.getItemByListId(AccountLogged.customerLogged.getListItem()).forEach(p->{
			listItemOb.add(p);
		});
		col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_fee.setCellValueFactory(new PropertyValueFactory<>("rentalFee"));
		col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
		col_loantype.setCellValueFactory(new PropertyValueFactory<>("loanType"));
		col_subType.setCellValueFactory(new PropertyValueFactory<>("subType"));
		table.setItems(listItemOb);
	}
	
}
