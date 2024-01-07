package create_notice_teacher;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import login.Login;
import notice_dashboard_teacher.NoticeDashBoard;

public class NoticeController {

	@FXML
	private DatePicker datePicker;

	@FXML
	private ComboBox combobox;

	@FXML
	private Button create;

	@FXML
	private TextField notice;
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button notification;
	@FXML
	private Label validationForNotice;
	

	public void createNotice(ActionEvent event) throws IOException {

		if (datePicker.getValue()==null || combobox.getValue()==null || notice.getText().isEmpty()) {
			validationForNotice.setText("Please fill all the fields");
		}
		else {
			validationForNotice.setOpacity(0);
		}

		final String messageContent = "{\n" + "\"noticeDate\"" + ":\"" + datePicker.getValue() + "\", \r\n"
				+ "\"createdBy\"" + ":\"" + combobox.getValue() + "\", \r\n" + "\"notice\"" + ":\"" + notice.getText()
				+ "\" \r\n" + "\n}";

		System.out.println(messageContent);

		HttpURLConnection Connection = HttpUtil.postJsonRequest(ApiEndPoint.NOTICEENTRY, messageContent);

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
			validationForNotice.setText("Notice created successfully !");
			
         	 showAlert(Alert.AlertType.INFORMATION, "Create Notice", "Notice has been created successfully");


		} else {
			
			validationForNotice.setText("Failed to create notice !");
        	 showAlert(Alert.AlertType.ERROR, "Create Notice", "Failed To create notice");


			System.out.println("POST Request did not work.");
			
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
		new NoticeDashBoard().show();
		
	}
public void logOut(ActionEvent event) {
	new Login().show();
		
	}
public void notification(ActionEvent event) {
	
}

}
