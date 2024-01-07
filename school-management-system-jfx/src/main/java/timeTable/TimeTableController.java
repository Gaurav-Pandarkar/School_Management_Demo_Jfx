package timeTable;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import login.Login;
import time_table_dashboard.TimeTableDashBoard;

public class TimeTableController {

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
	private Label validationForCreateTimeTable;
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button timeTable;
	
	
	public void addTimetableEntry(ActionEvent event) throws IOException {

	

		final String messageContent = "{\n" +
			    "\"classname\"" + ":\"" + className.getValue() + "\", \r\n" +
			    "\"timeslot\"" + ":\"" + timeSlot.getValue() + "\", \r\n" +
			    "\"monDay\"" + ":\"" + monday.getText() + "\", \r\n" +
			    "\"tuesDay\"" + ":\"" + Tuesday.getText() + "\", \r\n" +
			    "\"wednesDay\"" + ":\"" + wednesday.getText() + "\", \r\n" +
			    "\"thursDay\"" + ":\"" + thursDay.getText() + "\", \r\n" +
			    "\"friDay\"" + ":\"" + friday.getText() + "\", \r\n" +
			    "\"saturDay\"" + ":\"" + saturDay.getText() + "\" \r\n" +
			    "}";


		System.out.println(messageContent);

		HttpURLConnection Connection = HttpUtil.postJsonRequest(ApiEndPoint.TIMETABLEENTRY, messageContent);

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
			
			validationForCreateTimeTable.setText("Time Table created successfully");

		} else {
			validationForCreateTimeTable.setOpacity(0);
			validationForCreateTimeTable.setText("Failed to create time table");

			System.out.println("POST Request did not work.");
			

		
		}
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
