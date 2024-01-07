package create_exam_schedule_teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import common.ApiEndPoint;
import common.HttpUtil;
import exam_schedule_dashboard_teacher.ExamScheduleDashBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import login.Login;

public class AddExamScheduleController {

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
	
	public void addExamSchedule(ActionEvent event) throws IOException {

		

		final String messageContent = "{\n" + "\"className\"" + ":\"" + className.getText() + "\", \r\n"
				+ "\"subject\"" + ":\"" + subject.getText() + "\", \r\n"
				+ "\"examDate\"" + ":\"" + date.getValue() + "\", \r\n"
				+ "\"examTime\"" + ":\"" + Time.getText() 		 
				+ "\" \r\n" + "\n}";

		System.out.println(messageContent);

		HttpURLConnection Connection = HttpUtil.postJsonRequest(ApiEndPoint.EXAMSCHEDULE, messageContent);

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
        	 showAlert(Alert.AlertType.INFORMATION, "Exam shedule", "Exam shedule has been created successfully");


		} else {
       	 showAlert(Alert.AlertType.ERROR, "Exam shedule", "Failed to create exam schedule");

			System.out.println("POST Request did not work.");

		}
	}
	public void back(ActionEvent event) {
		new ExamScheduleDashBoard().show();
		
	}
	 private void showAlert(Alert.AlertType type, String title, String content) {
	        Alert alert = new Alert(type);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
public void logOut(ActionEvent event) {
	new Login().show();
		
	}
public void examShedule(ActionEvent event) {
	
}
}
