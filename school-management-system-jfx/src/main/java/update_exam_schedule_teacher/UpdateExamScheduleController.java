package update_exam_schedule_teacher;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import common.ApiEndPoint;
import exam_schedule_dashboard_teacher.ExamScheduleDashBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import login.Login;

public class UpdateExamScheduleController {

	@FXML
	private TextField idNumber;
	
	@FXML
	private TextField className;
	
	@FXML
	private TextField subject;
	
	@FXML
	private DatePicker date;
	
	@FXML
	private TextField Time;
	
	@FXML
	private Button Add;
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button examShedule;
	
	private void updateData() {

		String classValue = className.getText();
		String subjectValue = subject.getText();
		String dateValue = date.getValue().toString();
		String TimeValue = Time.getText();
		
		
		String IDnumber = idNumber.getText();
		String apiUrl = ApiEndPoint.UPDATEEXAMSCHEDULE + IDnumber;

		try {

			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			String payload = "{\n" +
				    "    \"className\":\"" + classValue + "\",\n" +
				    "    \"subject\":\"" + subjectValue + "\",\n" +
				    "    \"examDate\":\"" + dateValue + "\",\n" +
				    "    \"examTime\":\"" + TimeValue + "\"\n" +
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
		 new ExamScheduleDashBoard().show();
	 }
 public void logOut(ActionEvent event) {
	 new Login().show();
		 
	 }
 public void examShedule(ActionEvent event) {
	 
 }
}
