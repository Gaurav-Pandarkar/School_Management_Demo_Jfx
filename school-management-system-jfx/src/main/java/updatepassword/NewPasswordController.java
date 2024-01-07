package updatepassword;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;

import common.ApiEndPoint;
import common.HttpUtil;
import data_model.EmailDataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import login.Login;
import verifyotp.VerifyOtp;

public class NewPasswordController {
	@FXML
	private PasswordField password;
	@FXML
	private Button changePassword;
	@FXML
	private Button back;
	@FXML
	private Label validationForNewPassword;
	@Autowired
	 EmailDataModel email;
	
	
	public void changePassword(ActionEvent event) throws IOException {
		
		String emailFromFrogotPassword=email.info[0];
		final String messageContent = "{\n" + "\"email\"" + ":\"" + emailFromFrogotPassword + "\", \r\n" + "\"newPassword\""
				+ ":\"" + password.getText() + "\" \r\n" + "\n}";

		System.out.println(messageContent);

		HttpURLConnection postConnection = HttpUtil.postJsonRequest(ApiEndPoint.NEWPASSWORD, messageContent);

		int respCode = postConnection.getResponseCode();
		System.out.println("Response from the server is: \n");
		System.out.println("The POST Request Response Code :  " + respCode);
		System.out.println("The POST Request Response Message : " + postConnection.getResponseMessage());
		if (respCode == HttpURLConnection.HTTP_CREATED) {

			InputStreamReader inputStreamObj = new InputStreamReader(postConnection.getInputStream());
			BufferedReader br = new BufferedReader(inputStreamObj);
			String input = null;
			StringBuffer stringBuffer = new StringBuffer();
			while ((input = br.readLine()) != null) {
				stringBuffer.append(input);
			}
			br.close();
			postConnection.disconnect();

			System.out.println(stringBuffer.toString());
			password.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
			validationForNewPassword.setText("Password Updated Successfully !");
			new Login().show();
			
           
		//}else {
		///	password.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
			validationForNewPassword.setText("Password Not Updated");
			
		
		
		}
		//validationForNewPassword.setText("Password Not Updated");
		new Login().show();
		
		
	}
	public void back(ActionEvent event) {
		new VerifyOtp().show();
	}
	

}
