package registration2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import common.ApiEndPoint;
import common.HttpUtil;
import data_model.DataModelUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import registration1.Registration;
import welcome_page.Welcome;

public class RegistrationController {
	@FXML
	private PasswordField passcode;
	@FXML
	private PasswordField confirmPasscode;
	@FXML
	private Button next;
	@FXML
	private Button back;
	@FXML
	private Label validationForPasscode;
	@FXML
	private Label validationForConfirmPassword;
	@FXML
	private Label validationForSignUp;
	
	private	DataModelUser dataModel=new DataModelUser();
	public void next(ActionEvent event) throws IOException {
		if (passcode.getText().isEmpty()) {

			passcode.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
			validationForPasscode.setText("Passcodde is required");
		}

		else {
			validationForPasscode.setOpacity(0);
			passcode.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
		}
		if (confirmPasscode.getText().isEmpty()) {

			confirmPasscode.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
			validationForConfirmPassword.setText("Confirm Passcode is required");
		}

		
		else {
                
			validationForConfirmPassword.setOpacity(0);
			confirmPasscode.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");

		}
		if(passcode.getText().equals(confirmPasscode.getText())) {
		//String mobileNo=dataModel.getMobileNo();
		//String userRole=dataModel.getUserRole();
	//	System.out.println(userRole);if (confirmPasscode.getText().isEmpty()) {

			//confirmPasscode.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
			if(passcode.getText().isEmpty() || confirmPasscode.getText().isEmpty()) {
				validationForSignUp.setText("please fill all the fields");
			}else {
			validationForSignUp.setText("sign up successfully");
		
			}
		
			
		String mobileNo=dataModel.userInfo[0];
		String userRole=dataModel.userInfo[1];
		String email=dataModel.userInfo[2];
		final String messageContent = "{\n" + "\"mobileNo\"" + ":\"" + mobileNo + "\", \r\n" + "\"userRole\""
				+ ":\"" +userRole + "\", \r\n" + "\"passcode\"" + ":\"" + passcode.getText() +"\",\r\n" +  "\"email\"" + ":\"" + email + "\" \r\n" + "\n}";

		System.out.println(messageContent);

		
	HttpURLConnection	postConnection=	HttpUtil.postJsonRequest(ApiEndPoint.SIGNUP_USER, messageContent);
		int respCode = postConnection.getResponseCode();
		System.out.println("Response from the server is: \n");
		System.out.println("The POST Request Response Code :  " + respCode);
		System.out.println("The POST Request Response Message : " + postConnection.getResponseMessage());
		if (respCode == HttpURLConnection.HTTP_CREATED) {
			
			InputStreamReader inputStreamobj = new InputStreamReader(postConnection.getInputStream());
			BufferedReader br = new BufferedReader(inputStreamobj);
			String input = null;
			StringBuffer stringBuffer = new StringBuffer();
			while ((input = br.readLine()) != null) {
				stringBuffer.append(input);
			}
			br.close();
			postConnection.disconnect();
			System.out.println(stringBuffer.toString());
			new Registration().show();

		} else {
			System.out.println("POST Request did not work.");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("POST Request did not work.");
			alert.showAndWait();
			return;
		}
		}
		else {
			validationForSignUp.setText("Check passcode and confirm passcode");
			
		}


	}
	public void back(ActionEvent event) {
		new registration1.Registration().show();
		
	}
	

}
