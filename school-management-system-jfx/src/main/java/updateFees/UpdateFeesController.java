package updateFees;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import common.ApiEndPoint;
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

public class UpdateFeesController implements Initializable {

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
	private Button AddUpdate;
	@FXML
	private Button back;
	@FXML
	private Button fees;
	@FXML
	private Button logOut;
	
	
	
	@FXML
	private TextField idnumber;
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
	@FXML
	private Label validationForId;
	@FXML
	private Label validationForUpdateFees;
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<String> list = FXCollections.observableArrayList("First", "Second", "Third","Fourth","Sixth");
		appliedclass.setItems(list);
		
	}
	
	 
	
	    private void updateData() {
	    	
	    	if (idnumber.getText().isEmpty()) {
	    		idnumber.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
	    		validationForId.setText("Id no is required");
		        } else {
		        	validationForId.setOpacity(0);
		        	idnumber.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
		        }

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
	    	
	    	
	    	
	    	
	        String grnNoValue = grnNo.getText();
	        String studentNameValue = studentName.getText();
	        String appliedClassValue = appliedclass.getValue().toString(); 
	        String totalFeesValue = totalFees.getText();
	        String feesPaidValue = feesPaid.getText();
	        String remainingFeesValue = RemainingFees.getText();
	        String acadmicYearValue = date.getValue().toString();

	    	String IDnumber = idnumber.getText();

			String apiUrl = ApiEndPoint.UPDATEFEES + IDnumber;
	        
	        try {
	        	
	            URL url = new URL(apiUrl); 
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("PUT");
	            connection.setRequestProperty("Content-Type", "application/json");
	            connection.setDoOutput(true);
	           
	            String payload = "{\n" +
	                    "    \"grnNo\":\"" + grnNoValue + "\",\n" +
	                    "    \"studentName\":\"" + studentNameValue + "\",\n" +
	                    "    \"appliedClass\":\"" + appliedClassValue + "\",\n" +
	                    "    \"totalFees\":\"" + totalFeesValue + "\",\n" +
	                    "    \"feesPaid\":\"" + feesPaidValue + "\",\n" +
	                    "    \"remainingFees\":\"" + remainingFeesValue + "\",\n" +
	                    "    \"acadmicYear\":\"" + acadmicYearValue + "\"\n" +
	                    "}";

	            // Send the PUT request
	            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
	                outputStream.writeBytes(payload);
	                outputStream.flush();
	            }
	            int responseCode = connection.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	               
	                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                String response = reader.readLine();
	                reader.close();
					 showAlert(Alert.AlertType.INFORMATION, "Update Successful", "The data has been successfully updated!");
					 validationForUpdateFees.setText("Fees updated successfully");
	                System.out.println("Update Successful: " + response);
	            } else {
	            	 showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update the data. Please try again later.");
	            	 validationForUpdateFees.setText("Failed to  update fees");
	                System.out.println("Update Failed: " + responseCode);
	            }

	            connection.disconnect();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void addupdatedata(ActionEvent event) {
	    	
	    	updateData();
	    }
	    private void showAlert(Alert.AlertType type, String title, String content) {
	        Alert alert = new Alert(type);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
 public void back(ActionEvent event) {
	 new FeesDashBoard().show();
	    	
	    	
	    }
 public void logOut(ActionEvent event) {
	 new Login().show();
 	
 }
 public void fees(ActionEvent event) {
 	
 }
}
