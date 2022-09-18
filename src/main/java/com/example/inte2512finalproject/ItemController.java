package com.example.inte2512finalproject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;

public class ItemController implements Initializable {

	private ObservableList<String> listType = FXCollections.observableArrayList();
	private ObservableList<String> listSubType = FXCollections.observableArrayList();
	private ObservableList<String> listLoanType = FXCollections.observableArrayList();
	private ObservableList<String> listStatus = FXCollections.observableArrayList();
	private ObservableList<Items> listItemOb = FXCollections.observableArrayList();
	private List<Items> listItem = null;
	private Items items = null;
	private ItemDao itemDao = new ItemDao();
	private SwitchToScene sw = new SwitchToScene();

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
	private TableColumn<Items, String> col_rentType;

	@FXML
	private TableColumn<Items, String> col_loanType;

	@FXML
	private TableColumn<Items, Integer> col_numOfCopy;

	@FXML
	private TableColumn<Items, Float> col_rentalFee;

	@FXML
	private TableColumn<Items, String> col_status;

	@FXML
	private TextField txt_title;

	@FXML
	private ChoiceBox<String> choice_type;

	@FXML
	private ChoiceBox<String> choice_loanType;

	@FXML
	private TextField txt_numOfCopy;

	@FXML
	private TextField txt_rentalfee;

	@FXML
	private TextField txt_id;

	@FXML
	private ChoiceBox<String> choice_status;

	@FXML
	private Label lb_subType;

	@FXML
	private ChoiceBox<String> choice_subType;

	@FXML
	void add(ActionEvent event) {
		if (checkValueInput()) {
			Items items2 = new Items(txt_id.getText(), txt_title.getText(), choice_type.getValue(),
					choice_loanType.getValue(), Integer.valueOf(txt_numOfCopy.getText()),
					Float.valueOf(txt_rentalfee.getText()),
					choice_status.getValue());
			if (!choice_type.getValue().equals("Game")) {
				if (choice_subType.getValue() != null) {
					items2.setSubType(choice_subType.getValue());
				}
				else {
					Message.getMess("data cannot be left blank");
				}
			}
			
			if(itemDao.save(items2)) {
				Message.getMess("insert success");
				clearInput();
				setInitTable(false, false);
			}
			
		} else {
			Message.getMess("data cannot be left blank");
		}
	}

	@FXML
	void delete(ActionEvent event) {
		if(items != null) {
			itemDao.delete(items.getId());
			items = null;
			Message.getMess("Delete success!");
			setInitTable(false, false);
		}
		else {
			Message.getMess("select an item in table");
		}
	}

	@FXML
	void filterOutOfStock(ActionEvent event) {
		setInitTable(true, false);
	}

	@FXML
	void logout(ActionEvent event) {
		sw.switchToAnotherScene(event, "login.fxml");
	}

	@FXML
	void refresh(ActionEvent event) {
		setInitTable(false, false);
	}

	@FXML
	void searchIteam(KeyEvent event) {
		setInitTable(false, true);
	}

	@FXML
	void switchToCustomerManager(ActionEvent event) {
		sw.switchToAnotherScene(event, "customer.fxml");
	}

	@FXML
	void update(ActionEvent event) {
		if(items != null) {
			if (checkValueInput()) {
				Items items2 = new Items(txt_id.getText(), txt_title.getText(), choice_type.getValue(),
						choice_loanType.getValue(), Integer.valueOf(txt_numOfCopy.getText()),
						Float.valueOf(txt_rentalfee.getText()),
						choice_status.getValue());
				if (!choice_type.getValue().equals("Game")) {
					if (choice_subType.getValue() != null) {
						items2.setSubType(choice_subType.getValue());
					}
					else {
						Message.getMess("data cannot be left blank");
						return;
					}
				}
				
				itemDao.update(items2);
				clearInput();
				items = null;
				setInitTable(false, false);
				
			} else {
				Message.getMess("data cannot be left blank");
			}
		}
		else {
			Message.getMess("select an item in table");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lb_subType.setVisible(false);
		choice_subType.setVisible(false);
		listLoanType.add("2-day");
		listLoanType.add("1-week");
		choice_loanType.setItems(listLoanType);

		listStatus.add("borrowed");
		listStatus.add("available");
		choice_status.setItems(listStatus);

		listType.add("Record");
		listType.add("DVD");
		listType.add("Game");
		choice_type.setItems(listType);

		listSubType.add("Action");
		listSubType.add("Horror");
		listSubType.add("Drama");
		listSubType.add("Comedy");

		onclickType();
		setInitTable(false, false);
		clickTable();
		lb_loginAs.setText("login as: "+AccountLogged.admin.getUsername());
	}

	public void onclickType() {
		choice_type.valueProperty().addListener((obs, oldItem, newItem) -> {
			if (!newItem.equals("Game")) {
				lb_subType.setVisible(true);
				choice_subType.setVisible(true);
				choice_subType.setItems(listSubType);
			} else {
				lb_subType.setVisible(false);
				choice_subType.setVisible(false);
			}
		});
	}

	public void setInitTable(boolean outOfStock, boolean search) {
		listItemOb.clear();

		if (search && !txt_search.getText().equals("")) {
			listItem = itemDao.search(listItem, txt_search.getText());

		} else {
			listItem = itemDao.getListItem(outOfStock);
		}
		listItem.forEach(p -> {
			listItemOb.add(p);
		});
		col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		col_loanType.setCellValueFactory(new PropertyValueFactory<>("loanType"));
		col_numOfCopy.setCellValueFactory(new PropertyValueFactory<>("numberOfCopies"));
		col_rentalFee.setCellValueFactory(new PropertyValueFactory<>("rentalFee"));
		col_rentType.setCellValueFactory(new PropertyValueFactory<>("type"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("rentalStatus"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("title"));

		table.setItems(listItemOb);
	}

	public void clickTable() {
		table.setRowFactory(tv -> {
			TableRow<Items> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getButton() == MouseButton.PRIMARY) {
					items = row.getItem();
					setValueForInput();
				}

			});
			return row;
		});
	}

	public void setValueForInput() {
		txt_id.setText(items.getId());
		txt_numOfCopy.setText(items.getNumberOfCopies().toString());
		txt_rentalfee.setText(items.getRentalFee().toString());
		txt_title.setText(items.getTitle());
		choice_loanType.setValue(items.getLoanType());
		choice_status.setValue(items.getRentalStatus());
		choice_type.setValue(items.getType());
		if (!items.getType().equals("Game")) {
			choice_subType.setValue(items.getSubType());
		}
	}

	public void clearInput() {
		txt_id.setText("");
		txt_numOfCopy.setText("");
		txt_rentalfee.setText("");
		txt_title.setText("");
		choice_loanType.setValue(null);
		choice_status.setValue(null);
		choice_subType.setValue(null);
	}

	public boolean checkValueInput() {
		if (!txt_id.getText().equals("") && !txt_numOfCopy.getText().equals("") && !txt_rentalfee.getText().equals("")
				&& !txt_title.getText().equals("") && choice_loanType.getValue() != null
				&& choice_status.getValue() != null && choice_type.getValue() != null) {
			return true;
		}
		return false;
	}

}
