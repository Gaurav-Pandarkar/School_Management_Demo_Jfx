package leaveRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import common.ApiEndPoint;
import common.HttpUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import leave_dashboard.LeaveDashBoard;
import login.Login;

public class LeaveRequestController {

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
	private Button apply;
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button leave;

	public void addEntry(ActionEvent event) throws IOException {
		if (grnNo.getText().isEmpty() || studentName.getText().isEmpty() || startDate.getValue() == null
				|| endDate.getValue() == null || reason.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Please fill all The fields.");
			alert.showAndWait();
			return;
		}

		String messageContent = "{\n" +
			    "  \"grnNo\": \"" + grnNo.getText() + "\",\n" +
			    "  \"studentName\": \"" + studentName.getText() + "\",\n" +
			    "  \"reason\": \"" + reason.getText() + "\",\n" +
			    "  \"startDate\": \"" + startDate.getValue() + "\",\n" +
			    "  \"endDate\": \"" + endDate.getValue() + "\"\n" +
			    "}";

			System.out.println(messageContent);

			HttpURLConnection connection = HttpUtil.postJsonRequest(ApiEndPoint.LEAVEREQUEST, messageContent);

			int responseCode = connection.getResponseCode();
			System.out.println("Response from the server is: \n");
			System.out.println("The POST Request Response Code: " + responseCode);
			System.out.println("The POST Request Response Message: " + connection.getResponseMessage());
			if (responseCode == HttpURLConnection.HTTP_CREATED) {
			    InputStreamReader inputst = new InputStreamReader(connection.getInputStream());
			    BufferedReader br = new BufferedReader(inputst);
			    String input = null;
			    StringBuffer stringBuffer = new StringBuffer();
			    while ((input = br.readLine()) != null) {
			        stringBuffer.append(input);
			       
			    }

			    br.close();
			    connection.disconnect();

			    String jsonResponse = stringBuffer.toString();
			    System.out.println(jsonResponse);
	           	 showAlert(Alert.AlertType.INFORMATION, "Leave Request", "Leave request added successfully");


			    // Process the jsonResponse if needed

			} else {
				 showAlert(Alert.AlertType.ERROR, "Request Failed", "Failed to send  the request. Please try again later.");

			    System.out.println("POST Request did not work.");
			    // Handle the error scenario if needed
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
		new LeaveDashBoard().show();
		
	}
public void logOut(ActionEvent event) {
	new Login().show();
		
	}
public void leave(ActionEvent event) {
	
}

}
