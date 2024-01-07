package updateTimeTable;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import login.Login;
import time_table_dashboard.TimeTableDashBoard;

public class UpdateTimeTableController {
	
	@FXML
	private ComboBox className;
	
	@FXML
	private ComboBox timeSlot;
	
	@FXML
	private TextField monday;
	
	@FXML
	private TextField Tuesday;
	
	@FXML
	private TextField wednesday;
	
	@FXML
	private TextField thursDay;
	
	@FXML
	private TextField friday;
	
	@FXML
	private TextField saturDay;
	
	@FXML
	private Button addEntry;
	
	@FXML
	private TextField idNumber;

	@FXML
	private Button back;

	@FXML
	private Button logOut;

	@FXML
	private Button timeTable;

	@FXML
	private Label validationForCreateTimeTable;
	
	private void updateData() {

		
	}

	public void addupdatedata(ActionEvent event) {
		String classValue = className.getValue().toString();
		String timeslotValue = timeSlot.getValue().toString();
		String mondayValue = monday.getText();
		String tuesDayValue = Tuesday.getText();
		String wednesdayValue = wednesday.getText();
		String thursdayValue = thursDay.getText();
		String fridayValue = friday.getText();
		String saturdayValue = saturDay.getText();

		String IDnumber = idNumber.getText();

		String apiUrl = ApiEndPoint.UPDATETIMETABLE + IDnumber;

		try {

			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			String payload = "{\n" +
				    "    \"className\":\"" + classValue + "\",\n" +
				    "    \"timeSlot\":\"" + timeslotValue + "\",\n" +
				    "    \"monDay\":\"" + mondayValue + "\",\n" +
				    "    \"tuesDay\":\"" + tuesDayValue + "\",\n" +
				    "    \"wednesDay\":\"" + wednesdayValue + "\",\n" +
				    "    \"thursDay\":\"" + thursdayValue + "\",\n" +
				    "    \"friDay\":\"" + fridayValue + "\",\n" +
				    "    \"saturDay\":\"" + saturdayValue + "\"\n" +
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
				 validationForCreateTimeTable.setText("Update Successful\", \"The data has been successfully updated!");
				System.out.println("Update Successful: " + response);
			} else {
				 showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update the data. Please try again later.");
				System.out.println("Update Failed: " + responseCode);
				 validationForCreateTimeTable.setText("Update Failed\", \"Failed to update the data. Please try again later.");

				
			}

			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 private void showAlert(Alert.AlertType type, String title, String content) {
	        Alert alert = new Alert(type);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	 public void back(ActionEvent event) {
		 new TimeTableDashBoard().show();
		 //validationForCreateTimeTable.setText("Update Failed\", \"Failed to update the data. Please try again later.");

		}
	 public void logOut(ActionEvent event) {
		 new Login().show();
		}
	 public void timeTable(ActionEvent event) {
		}
	
}
