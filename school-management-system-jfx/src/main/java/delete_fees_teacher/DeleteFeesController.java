package delete_fees_teacher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import common.ApiEndPoint;
import fees_dashboard_teacher.FeesDashBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import login.Login;

public class DeleteFeesController {
	@FXML
	private TextField grnNumber;
	@FXML
	private Label validationForGrnNo;
	@FXML
	private Label validationForDeleteFees;

	@FXML
	private Button Delete;
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button fees;

	public void deleteFeesRecord(ActionEvent event) {

		String grnNo = grnNumber.getText();

		String apiUrl = ApiEndPoint.DELETEFEES + grnNo;

		try {
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				System.out.println("Record deleted successfully");
				validationForDeleteFees.setText("Record deleted successfully");
			} else {
				System.out.println("Failed to delete record");
				validationForDeleteFees.setText("Failed to delete record");

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

	private void showSuccessAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void showErrorAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
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
