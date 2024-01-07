package addfees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import common.ApiEndPoint;
import common.HttpUtil;
import data_model.DataModel;
import data_model.FeesDataModel;
import fees_dashboard.FeesDashBoard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import login.Login;
import principle.Principle;
import print_fees.PrintFeesReciept;

public class AddFeesController implements Initializable {
	@FXML
	private TextField grnNo;
	
	@FXML
	private TextField studentName;
	
	@FXML
	private ComboBox appliedclass;
	
	@FXML
	private TextField totalFees;
	
	@FXML
	private TextField feesPaid;
	
	@FXML
	private TextField RemainingFees;
	
	@FXML
	private DatePicker date;
	
	@FXML
	private Button add;
	@FXML
	private Button print;
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button fees;
	@FXML
	private Label validationForGrnNo;
	@FXML
	private Label validationForStudentName;
	@FXML
	private Label validationForTotalFees;
	@FXML
	private Label validationForFeesPaid;
	@FXML
	private Label validationForRemainingFees;
	@FXML
	private Label validationForAddFees;
	private FeesDataModel dataModel = new FeesDataModel();
	
	public void addFees(ActionEvent event) throws IOException {
		
		if (grnNo.getText().isEmpty()) {
			grnNo.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
			validationForGrnNo.setText("grn no is required");
	        } else {
	        	validationForGrnNo.setOpacity(0);
	        	grnNo.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
	        }

	        if (studentName.getText().isEmpty()) {
	        	studentName.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
	        	validationForStudentName.setText("Student name is required");
	        } else {
	        	validationForStudentName.setOpacity(0);
	        	studentName.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
	        }
	        if (totalFees.getText().isEmpty()) {
	        	totalFees.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
	        	validationForTotalFees.setText("fees required");
		        } else {
		        	validationForTotalFees.setOpacity(0);
		        	totalFees.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
		        }

		        if (feesPaid.getText().isEmpty()) {
		        	feesPaid.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
		        	validationForFeesPaid.setText("fees paid required");
		        } else {
		        	validationForFeesPaid.setOpacity(0);
		        	feesPaid.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
		        }
		        if (RemainingFees.getText().isEmpty()) {
		        	RemainingFees.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
		        	validationForRemainingFees.setText("Remaining fees required");
		        } else {
		        	validationForRemainingFees.setOpacity(0);
		        	RemainingFees.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
		        }
		        if(grnNo.getText().isEmpty() && studentName.getText().isEmpty() && totalFees.getText().isEmpty() && feesPaid.getText().isEmpty()&& RemainingFees.getText().isEmpty()) {
		        	validationForAddFees.setText("please fill all the fields");
				}else {
					validationForAddFees.setText("fees added  successfully");
			
				}
		        Object appliedclassname=appliedclass.getValue();
		        
		        dataModel.setGrnNo(grnNo.getText());
		        dataModel.setFeesPaid(feesPaid.getText());
		        dataModel.setStudentName(studentName.getText());
		        dataModel.setTotalFees(totalFees.getText());
		        dataModel.setRemainingFees(RemainingFees.getText());
		      
		
		
		
		final String messageContent = "{\n" + "\"grnNo\"" + ":\"" + grnNo.getText() + "\", \r\n"
				+ "\"studentName\"" + ":\"" + studentName.getText() + "\", \r\n"
				+ "\"appliedClass\"" + ":\"" + appliedclass.getValue() + "\", \r\n"
				+ "\"totalFees\"" + ":\"" + totalFees.getText() + "\", \r\n"
				+ "\"feesPaid\"" + ":\"" + feesPaid.getText() + "\", \r\n"
				+ "\"remainingFees\"" + ":\"" + RemainingFees.getText() + "\", \r\n" + "\"acadmicYear\"" + ":\"" + date.getValue()
				+ "\" \r\n" + "\n}";

		System.out.println(messageContent);

		HttpURLConnection Connection = HttpUtil.postJsonRequest(ApiEndPoint.FEESENTRY, messageContent);

		int responseCode = Connection.getResponseCode();
		System.out.println("Response from the server is: \n");
		System.out.println("The POST Request Response Code :  " + responseCode);
		System.out.println("The POST Request Response Message : " + Connection.getResponseMessage());
		if (responseCode == HttpURLConnection.HTTP_CREATED) {

			InputStreamReader inputst = new InputStreamReader(Connection.getInputStream());
			BufferedReader br = new BufferedReader(inputst);
			String input = null;
			StringBuffer stringBuffer = new StringBuffer();
			while ((input = br.readLine()) != null) {
				stringBuffer.append(input);
			}
			br.close();
			Connection.disconnect();

			System.out.println(stringBuffer.toString());

			String JsonResponse = Connection.getResponseMessage();
			//new Principle().show();

		} else {

			System.out.println("POST Request did not work.");

		}
	}
	public void back(ActionEvent event) {
		new FeesDashBoard().show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		ObservableList<String> list = FXCollections.observableArrayList("first", "second", "third", "fourth", "fifth",
				"sixth", "seventh", "eight", "nineth", "tenth");
		appliedclass.setItems(list);

	}
	public void print(ActionEvent event) {
		new PrintFeesReciept().show();
		
	}
public void logOut(ActionEvent event) {
	new Login().show();
		
	}
public void fees(ActionEvent event) {
	
}
	


}
