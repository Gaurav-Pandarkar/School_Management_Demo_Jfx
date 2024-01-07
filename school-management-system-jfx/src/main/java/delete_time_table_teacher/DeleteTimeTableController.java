package delete_time_table_teacher;

import java.io.BufferedReader;
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
import time_table_dashboard_teacher.TimeTableDashBoard;

public class DeleteTimeTableController {

	@FXML
	private ComboBox classNO;
	
	@FXML
	private Button delete;

	@FXML
	private Button back;

	@FXML
	private Button timeTable;

	@FXML
	private Button logOut;
	@FXML
	private Label validationForDeleteTimeTable;
	
	public void deleteTimeTable(ActionEvent event) {

		
		String number = classNO.getValue().toString();

		String apiUrl = ApiEndPoint.DELETETIMETABLE + number;

		try {
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// showAlert(Alert.AlertType.INFORMATION, "Delete Successful", "The data has been deleted Successfully !");
				validationForDeleteTimeTable.setText("The data has been deleted Successfully !");

				System.out.println("Record deleted successfully");
			} else {
				// showAlert(Alert.AlertType.ERROR, "Delete Failed", "Failed to Delete the data. Please try again later.");
				validationForDeleteTimeTable.setText("Failed to Delete the data. Please try again later.");
				System.out.println("Failed to delete record");
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
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
		 
	 }
 public void logOut(ActionEvent event) {
	 new Login().show();
		 
	 }
 public void timeTable(ActionEvent event) {
	 
 }

}
