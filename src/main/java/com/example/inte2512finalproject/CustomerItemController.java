package com.example.inte2512finalproject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;


public class CustomerItemController implements Initializable{
	
	private ObservableList<Items> listItemOb = FXCollections.observableArrayList();
	
	private CustomerDao customerDao = new CustomerDao();
	
	private ItemDao itemDao = new ItemDao();
	
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
	private TableColumn<Items, Float> col_rentalFee;
	
	private Items item = null;

	@FXML
	void close(ActionEvent event) {
		Stage stage = (Stage) txt_search.getScene().getWindow();
		 stage.close();
	}

	@FXML
	void reeturnItem(ActionEvent event) {
		if(item == null) {
			Message.getMess("select an item in table");
		}
		else {
			for(int i=0; i<CustomerController.customer.getListItem().size(); i++) {
				if(CustomerController.customer.getListItem().get(i).equals(item.getId())) {
					CustomerController.customer.getListItem().remove(i);
					break;
				}
			}
			customerDao.returnItem(CustomerController.customer);
			customerDao.updateNumberItem(item.getId());
			setInitTable();
		}
	}

	@FXML
	void search(KeyEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setInitTable();
		onclickTable();
	}
	
	public void setInitTable() {
		listItemOb.clear();
		itemDao.getItemByListId(CustomerController.customer.getListItem()).forEach(p->{
			listItemOb.add(p);
		});
		col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_rentalFee.setCellValueFactory(new PropertyValueFactory<>("rentalFee"));
		col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
		table.setItems(listItemOb);
	}
	
	public void onclickTable() {
		table.setRowFactory(tv -> {
			TableRow<Items> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getButton() == MouseButton.PRIMARY) {
					item = row.getItem();
				}

			});
			return row;
		});
	}
}
