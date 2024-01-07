package addTeacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import admission_dashboard_principle.AdmissionDashBoard;
import common.ApiEndPoint;
import common.HttpUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import login.Login;

public class AddTeacherController {
	@FXML
	private TextField name;
	
	@FXML
	private TextField mobileNo;
	
	@FXML
	private TextField address;
	
	@FXML
	private TextField  qualification;
	
	@FXML
	private TextField subject; 
	
	@FXML
	private TextField email;
	
	@FXML
	private ComboBox gender;
	
	@FXML
	private Button addTeacher;
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button teacher;
	
	public void addTeacher(ActionEvent event) throws IOException {

		if (name.getText().isEmpty() || mobileNo.getText().isEmpty() || address.getText().isEmpty()|| qualification.getText().isEmpty()||
				 subject.getText().isEmpty()|| gender.getValue()==null || email.getText().isEmpty() ) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Please fill all The fields.");
			alert.showAndWait();
			return;
		}

		final String messageContent = "{\n" +
			    "\"name\"" + ":\"" + name.getText() + "\", \r\n" +
			    "\"mobileNo\"" + ":\"" + mobileNo.getText() + "\", \r\n" +
			    "\"address\"" + ":\"" + address.getText() + "\", \r\n" +
			    "\"qualification\"" + ":\"" + qualification.getText() + "\", \r\n" +
			    "\"subject\"" + ":\"" + subject.getText() + "\", \r\n" +
			    "\"gender\"" + ":\"" + gender.getValue() + "\", \r\n" +	  
			    "\"email\"" + ":\"" + email.getText() + "\" \r\n" +
			    "}";

		System.out.println(messageContent);

		HttpURLConnection Connection = HttpUtil.postJsonRequest(ApiEndPoint.ADDTEACHER, messageContent);

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
          	 showAlert(Alert.AlertType.INFORMATION, "Enrolled Teacher", "Teacher has been successfully enrolled!");


		} else {

			System.out.println("POST Request did not work.");
         	 showAlert(Alert.AlertType.ERROR, "Enrolled Teacher", "Failed To Enrolled");


		}
	}
	public void back(ActionEvent event) {
		new AdmissionDashBoard().show();
		
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
public void teacher(ActionEvent event) {
	
}



}
