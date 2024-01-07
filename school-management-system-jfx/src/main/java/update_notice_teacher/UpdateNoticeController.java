package update_notice_teacher;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import login.Login;
import notice_dashboard_teacher.NoticeDashBoard;

public class UpdateNoticeController {

	
	@FXML
	private DatePicker datePicker;

	@FXML
	private ComboBox combobox;

	@FXML
	private Button updatenotice;

	@FXML
	private TextField notice;
	
	@FXML
	private TextField idnumber;
	@FXML
	private Button back;
	@FXML
	private Button notification;
	@FXML
	private Button logOut;
	 private void updateData() {
	    	
	  
	    	String IDnumber = idnumber.getText();

			String apiUrl = ApiEndPoint.UPDATENOTICE + IDnumber;
	        
	        try {
	        	
	            URL url = new URL(apiUrl); 
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("PUT");
	            connection.setRequestProperty("Content-Type", "application/json");
	            connection.setDoOutput(true);
	           
	            String payload = "{"
	                    + "\"noticeDate\":\"" + datePicker.getValue() + "\","
	                    + "\"notice\":\"" + notice.getText() + "\","
	                    + "\"createdBy\":\"" + combobox.getValue() + "\""
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
	 
	 public void updateNoticeData(ActionEvent event) {
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
		new NoticeDashBoard().show();
		 
	 }
 public void logOut(ActionEvent event) {
	 new Login().show();
		 
	 }
 public void notification(ActionEvent event) {
	 
 }
}
