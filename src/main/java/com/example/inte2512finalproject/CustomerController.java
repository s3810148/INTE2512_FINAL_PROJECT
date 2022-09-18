package com.example.inte2512finalproject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class CustomerController implements Initializable {

	private ObservableList<String> listGroup = FXCollections.observableArrayList();

	private ObservableList<Customer> listCustomer = FXCollections.observableArrayList();

	private SwitchToScene sw = new SwitchToScene();

	private CustomerDao customerDao = new CustomerDao();

	private List<Customer> listCus = null;

	public static Customer customer = null;

	@FXML
	private Label lb_loginAs;

	@FXML
	private TextField txt_search;
	
	@FXML
	private Button btn_listItem;

	@FXML
	private ChoiceBox<String> choice_group;

	@FXML
	private TableView<Customer> table;

	@FXML
	private TableColumn<Customer, String> col_id;

	@FXML
	private TableColumn<Customer, String> col_name;

	@FXML
	private TableColumn<Customer, String> col_address;

	@FXML
	private TableColumn<Customer, String> col_phone;

	@FXML
	private TableColumn<Customer, String> col_type;

	@FXML
	private TableColumn<Customer, Integer> col_number;

	@FXML
	private ChoiceBox<String> choice_typeCustomer;

    @FXML
    void getListItemOfCustomer(ActionEvent event) {
    	if(customer == null) {
    		Message.getMess("select a customer in table");
    	}
    	else {
    		try {
    		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customerItem.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
    		    Stage stage = new Stage();
    		    stage.initModality(Modality.APPLICATION_MODAL);
    		    stage.initStyle(StageStyle.UNDECORATED);
    		    stage.setTitle("ABC");
    		    stage.setScene(new Scene(root1));  
    		    stage.show();
    		    
    		}
    		catch (Exception e) {
				
			}
    	}
    }

	@FXML
	void logout(ActionEvent event) {
		sw.switchToAnotherScene(event, "login.fxml");
	}

	@FXML
	void searchCustomer(KeyEvent event) {
		if (!txt_search.getText().equals("")) {
			initTable(null, true);
			System.out.println(txt_search.getText());
		} else {
			initTable(null, false);
		}
	}

	@FXML
	void switchToItemManager(ActionEvent event) {
		sw.switchToAnotherScene(event, "item.fxml");
	}

	@FXML
	void update(ActionEvent event) {
		if (customer == null) {
			Message.getMess("select a customer in table");
		} else {
			if (choice_typeCustomer.getValue() != null) {
				customer.setCustomerType(choice_typeCustomer.getValue());
				customerDao.update(customer);
				initTable(null, false);
			} else {
				Message.getMess("select a customer type in choice box");
			}
			choice_group.setValue(null);
			customer = null;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listGroup.add("VIP");
		listGroup.add("Guest");
		listGroup.add("Regular");
		choice_group.setItems(listGroup);
		choice_typeCustomer.setItems(listGroup);
		initTable(null, false);
		lb_loginAs.setText("Login as: " + AccountLogged.admin.getUsername());
		filterCustomer();
		clickTable();
	}

	public void initTable(String group, boolean search) {
		listCustomer.clear();
		if (search && !txt_search.getText().equals("")) {
			listCus = customerDao.search(txt_search.getText());

		} else {
			listCus = customerDao.getCustomer(group);
		}
		listCus.forEach(p -> {
			listCustomer.add(p);
		});
		col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_number.setCellValueFactory(new PropertyValueFactory<>("numberOfRentals"));
		col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		col_type.setCellValueFactory(new PropertyValueFactory<>("customerType"));
		table.setItems(listCustomer);
	}

	public void filterCustomer() {
		choice_group.valueProperty().addListener((obs, oldItem, newItem) -> {
			initTable(newItem, false);
		});
	}

	public void clickTable() {
		table.setRowFactory(tv -> {
			TableRow<Customer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getButton() == MouseButton.PRIMARY) {
					customer = row.getItem();
				}

			});
			return row;
		});
	}
}
