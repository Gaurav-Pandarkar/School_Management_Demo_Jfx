package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.json.JSONObject;

import common.ApiEndPoint;
import common.HttpUtil;
import forgot_password.ForgotPassword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import maindashboard.Dashboard;
import principle.Principle;
import principle_package.PrincipalDashBoard;
import student_dashboard.StudentDashBoard;
import teacher_dashboard.TeacherDashBoard;
import welcome_page.Welcome;

public class LoginController {
	@FXML
	private TextField mobileNo;
	@FXML
	private PasswordField passcode;
	@FXML
	private Button next;
	@FXML
	private Label validationForMobileNo;
	@FXML
	private Label validationForPasscode;

	private String userRole;
	private String email;
	/*
	 * @FXML public void onMouseEntered() { // Apply the hover effect when the mouse
	 * enters the button next.setStyle("-fx-background-color: #8c1a42"); // Darker
	 * green background color // Add other style changes as needed for the hover
	 * effect }
	 * 
	 * @FXML public void onMouseExited() { // Reset the style when the mouse exits
	 * the button (optional) next.setStyle("-fx-background-color: #8c1a42"); //
	 * Original green background color // Reset other style changes if any }
	 */

	public void next(ActionEvent event) throws IOException {
//		if(mobileNo.getText().length()==0) {
//			mobileNo.setStyle("-fx-border-color:red; -fx-border-width:2px ;");
//		}
//		
//		if(passcode.getText().length()==0) {
//			passcode.setStyle("-fx-border-color:red; -fx-border-width:2px ;");
//
//		}
		if (mobileNo.getText().isEmpty()) {

			mobileNo.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
			validationForMobileNo.setText("Mobile no is required");
		}

		else {
			validationForMobileNo.setOpacity(0);
			mobileNo.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
		}
		if (passcode.getText().isEmpty()) {

			passcode.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
			validationForPasscode.setText("Passcode is required");
		}

		else {

			validationForPasscode.setOpacity(0);
			passcode.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");

		}

		final String messageContent = "{\n" + "\"mobileNo\"" + ":\"" + mobileNo.getText() + "\", \r\n" + "\"passcode\""
				+ ":\"" + passcode.getText() + "\" \r\n" + "\n}";

		System.out.println(messageContent);

		HttpURLConnection postConnection = HttpUtil.postJsonRequest(ApiEndPoint.LOGIN, messageContent);

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
			userRole = stringBuffer.toString();
			System.out.println(userRole);
			String userRoleLoginResponse = parseUserRole(userRole);
			System.out.println("User Role: " + userRoleLoginResponse);

			if (userRoleLoginResponse.equals("principal")) {
				new PrincipalDashBoard().show();
			}

			if (userRoleLoginResponse.equals("student")) {

				new StudentDashBoard().show();
			}
			if (userRoleLoginResponse.equals("parent")) {
				new Dashboard().show();

			}
			if (userRoleLoginResponse.equals("teacher")) {
				new TeacherDashBoard().show();
			}

			String subject = "You login in school management";
			String message = "Thank you";
			email = stringBuffer.toString();
			// System.out.println(email);
			String emailResponse = parseEmail(email);
			System.out.println("Email: " + emailResponse);

			final String messageContent2 = "{\n" + "\"to\"" + ":\"" + emailResponse + "\", \r\n" + "\"subject\"" + ":\""
					+ subject + "\", \r\n" + "\"message\"" + ":\"" + message + "\" \r\n" + "\n}";

			System.out.println(messageContent2);

			HttpURLConnection postConnection2 = HttpUtil.postJsonRequest(ApiEndPoint.EMAILTOUSERONLOGIN,
					messageContent2);

			int respCode2 = postConnection2.getResponseCode();
			System.out.println("Response from the server is: \n");
			System.out.println("The POST Request Response Code :  " + respCode2);
			System.out.println("The POST Request Response Message : " + postConnection2.getResponseMessage());
			if (respCode2 == HttpURLConnection.HTTP_CREATED) {

				InputStreamReader inputStreamObj2 = new InputStreamReader(postConnection2.getInputStream());
				BufferedReader br2 = new BufferedReader(inputStreamObj2);
				String input2 = null;
				StringBuffer stringBuffer2 = new StringBuffer();
				while ((input2 = br2.readLine()) != null) {
					stringBuffer2.append(input2);
				}
				br2.close();
				postConnection2.disconnect();

				System.out.println(stringBuffer2.toString());

			}

		} else {
			System.out.println("POST Request did not work.");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Login Failed. Please check entries");
			alert.showAndWait();
			return;
		}

	}

	private static String parseUserRole(String response) {
		JSONObject jsonObject = new JSONObject(response);
		String userRole = jsonObject.getJSONObject("data").getString("userRole");
		return String.valueOf(userRole);

	}

	private static String parseEmail(String response) {
		JSONObject jsonObject = new JSONObject(response);
		String email = jsonObject.getJSONObject("data").getString("email");
		return String.valueOf(email);

	}

	public void forgotPassword(ActionEvent event) {
		new ForgotPassword().show();
	}

	public void back(ActionEvent event) {
		new Welcome().show();

	}

}
