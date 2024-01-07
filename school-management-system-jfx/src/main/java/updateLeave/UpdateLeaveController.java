package updateLeave;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import common.ApiEndPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import leave_dashboard.LeaveDashBoard;
import login.Login;

public class UpdateLeaveController {

	@FXML
	private TextField grnNo;

	@FXML
	private TextField studentName;

	@FXML
	private DatePicker startDate;

	@FXML
	private DatePicker endDate;

	@FXML
	private TextField reason;

	@FXML
	private Button UpdateLeave;
	@FXML
	private Button back;
	@FXML
	private Button leave;
	@FXML
	private Button logOut;
	
	@FXML
	private TextField idnumber;
	
	 private void updateData() {
	    	
		  
	    	String IDnumber = idnumber.getText();

			String apiUrl = ApiEndPoint.UPDATELEAVE + IDnumber;
	        
	        try {
	        	
	            URL url = new URL(apiUrl); 
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("PUT");
	            connection.setRequestProperty("Content-Type", "application/json");
	            connection.setDoOutput(true);
	           
	            String payload = "{"
	                    + "\"id\":\"" + idnumber.getText() + "\","
	                    + "\"grnNo\":\"" + grnNo.getText() + "\","
	                    + "\"studentName\":\"" + studentName.getText() + "\","
	                    + "\"startDate\":\"" + startDate.getValue() + "\","
	                    + "\"endDate\":\"" + endDate.getValue() + "\","
	                    + "\"reason\":\"" + reason.getText() + "\""
	                    + "}";

	          
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

	                System.out.println("Update Successful: " + response);
	            } else {
	            	 showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update the data. Please try again later.");

	                System.out.println("Update Failed: " + responseCode);
	            }

	            connection.disconnect();
	        } catch (Exception e) {
	            e.printStackTrace();
	           
	        }
	       
	    }
	 
	 public void updateLeaveData(ActionEvent event) {
		 updateData() ;
	 }
	 private void showAlert(Alert.AlertType type, String title, String content) {
	        Alert alert = new Alert(type);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	 public void back(ActionEvent event) {
		 new LeaveDashBoard().show();
		 
	 }
 public void logOut(ActionEvent event) {
	 new Login().show();
		 
	 }
 public void leave(ActionEvent event) {
	 
 }
}
