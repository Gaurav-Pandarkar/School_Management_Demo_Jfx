package diaryInformation;

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

public class DiaryInformationController {

	@FXML
	private TextField grnNo;
	
	@FXML
	private TextField studentName;
	
	@FXML
	private DatePicker date;
	
	@FXML
	private TextField information;
	
	@FXML
	private Button send;
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button diary;
	
	public void sendDiaryInformation(ActionEvent event) throws IOException {

		

		final String messageContent = "{\n" + "\"grnNO\"" + ":\"" + grnNo.getText() + "\", \r\n"
				+ "\"studentName\"" + ":\"" + studentName.getText() + "\", \r\n"
				+ "\"date\"" + ":\"" + date.getValue() + "\", \r\n"
				+ "\"information\"" + ":\"" + information.getText() 
				+ "\" \r\n" + "\n}";

		System.out.println(messageContent);

		HttpURLConnection Connection = HttpUtil.postJsonRequest(ApiEndPoint.DIARYINFO, messageContent);

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
			

		} else {

			System.out.println("POST Request did not work.");

		}
	}
	public void back(ActionEvent event) {
		
	}
public void logOut(ActionEvent event) {
		
	}
public void diary(ActionEvent event) {
	
}

}
