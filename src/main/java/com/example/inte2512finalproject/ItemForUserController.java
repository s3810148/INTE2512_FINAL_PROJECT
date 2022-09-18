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

public class ItemForUserController implements Initializable{

	private ObservableList<Items> listItemOb = FXCollections.observableArrayList();
	
	private ObservableList<String> listType = FXCollections.observableArrayList();
	
	private ObservableList<String> listSubType = FXCollections.observableArrayList();

	private SwitchToScene sw = new SwitchToScene();

	private ItemDao itemDao = new ItemDao();
	
	private CustomerDao customerDao = new CustomerDao();

	private Items items = null;
	
	@FXML
	private Label lb_loginAs;

	@FXML
	private TextField txt_search;

	@FXML
	private ChoiceBox<String> choice_Type;

	@FXML
	private ChoiceBox<String> choice_Subtype;

	@FXML
	private TableView<Items> table;

	@FXML
	private TableColumn<Items, String> col_id;

	@FXML
	private TableColumn<Items, String> col_title;

	@FXML
	private TableColumn<Items, String> col_type;

	@FXML
	private TableColumn<Items, String> col_subtitle;

	@FXML
	private TableColumn<Items, String> col_loantype;

	@FXML
	private TableColumn<Items, Float> col_fee;

	@FXML
	void confirm(ActionEvent event) {
		if(items == null) {
			Message.getMess("select an item in table");
		}
		else {
			if(customerDao.confirmItem(AccountLogged.customerLogged, items)) {
				sw.switchToAnotherScene(event, "customerForUser.fxml");
			}
		}
	}

	@FXML
	void logout(ActionEvent event) {
		sw.switchToAnotherScene(event, "login.fxml");
	}

	@FXML
	void search(KeyEvent event) {
		if(!txt_search.getText().equals("")) {
			setInitTable(itemDao.search(itemDao.getListItem(false), txt_search.getText()));
		}
		else {
			setInitTable(itemDao.getListItem(false));
		}
	}

	@FXML
	void switchToMyItem(ActionEvent event) {
		sw.switchToAnotherScene(event, "customerForUser.fxml");
	}

    @FXML
    void refresh(ActionEvent event) {
    	setInitTable(itemDao.getListItem(false));
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choice_Subtype.setVisible(false);
		listType.add("Record");
		listType.add("DVD");
		listType.add("Game");
		choice_Type.setItems(listType);

		listSubType.add("Action");
		listSubType.add("Horror");
		listSubType.add("Drama");
		listSubType.add("Comedy");
		choice_Subtype.setItems(listSubType);
		setInitTable(itemDao.getListItem(false));
		clickRow();
		filter();
	}

	public void setInitTable(List<Items> list) {
		listItemOb.clear();
		list.forEach(p->{
			listItemOb.add(p);
		});
		col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_fee.setCellValueFactory(new PropertyValueFactory<>("rentalFee"));
		col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
		col_loantype.setCellValueFactory(new PropertyValueFactory<>("loanType"));
		col_subtitle.setCellValueFactory(new PropertyValueFactory<>("subType"));
		table.setItems(listItemOb);
	}
	
	public void clickRow() {
		table.setRowFactory(tv -> {
			TableRow<Items> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getButton() == MouseButton.PRIMARY) {
					items = row.getItem();
				}

			});
			return row;
		});
	}
	
	public void filter() {
		choice_Type.valueProperty().addListener((obs, oldItem, newItem) -> {
			if(!newItem.equals("Game")) {
				choice_Subtype.setVisible(true);
				setInitTable(itemDao.getItemByType(newItem, null));
				choice_Subtype.valueProperty().addListener((obss, oldItems, newItems) -> {
					setInitTable(itemDao.getItemByType(newItem,newItems));
				});
			}
			else {
				setInitTable(itemDao.getItemByType(newItem, null));
				choice_Subtype.setVisible(false);
			}
		});

	}
}
