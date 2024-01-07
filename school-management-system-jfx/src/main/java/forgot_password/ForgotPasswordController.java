package forgot_password;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import common.ApiEndPoint;
import common.HttpUtil;
import data_model.DataModelUser;
import data_model.EmailDataModel;
import data_model.OtpModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import login.Login;
import verifyotp.VerifyOtp;

public class ForgotPasswordController {
	@FXML
	private  TextField email;
	@FXML
	private Button sendOtp;
	@FXML
	private Label validationForEmail;
	@FXML
	private Label emailNotExist;
	private String emailAddress;
	private String otp;
   private String users;
   private String otpnew;
   
	private	OtpModel data=new OtpModel();
	private EmailDataModel emailData=new EmailDataModel();
	public void sendOtp(ActionEvent event) throws IOException {
		if (email.getText().isEmpty()) {

			email.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
			emailNotExist.setText("Email Address Required");
		}

		else {
			//validationForEmail.setOpacity(0);
			email.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
		}
		
		
			
		String emialnew=email.getText();
		emailData.setEmail(emialnew);
		
          String url="http://localhost:8080/User/api/"+emialnew;
		HttpURLConnection postConnection = HttpUtil.getRequest(url);

		int respCode = postConnection.getResponseCode();
		System.out.println("Response from the server is: \n");
		System.out.println("The POST Request Response Code :  " + respCode);
		System.out.println("The POST Request Response Message : " + postConnection.getResponseMessage());
		if (respCode == HttpURLConnection.HTTP_OK) {

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
			emailAddress = stringBuffer.toString();
//			System.out.println(emailAddress);
			 users = parseEmail(emailAddress);
//			System.out.println("Email Address: " + users);
			System.out.println(users);
		
			if(users.equals(emialnew)) {
				//emailNotExist.setOpacity(0);
				emailNotExist.setText("OTP send successfully");
				email.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
				
				String subject = "Forgot Password";
				String message = "Verify OTP to change your password";

				final String messageContent2 = "{\n" + "\"to\"" + ":\"" + email.getText() + "\", \r\n" + "\"subject\""
						+ ":\"" + subject + "\", \r\n" + "\"message\"" + ":\"" + message + "\" \r\n" + "\n}";

				System.out.println(messageContent2);

				HttpURLConnection postConnection2 = HttpUtil.postJsonRequest(ApiEndPoint.OTPFORFORGOTPASSWORD,messageContent2);

				int respCode2 = postConnection2.getResponseCode();
				System.out.println("Response from the server is: \n");
				System.out.println("The POST Request Response Code :  " + respCode2);
				System.out.println("The POST Request Response Message : " + postConnection2.getResponseMessage());
				if (respCode2 != HttpURLConnection.HTTP_CREATED) {

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
					otp = stringBuffer2.toString();
//					System.out.println(emailAddress);
					 otpnew = parseOtp(otp);
					System.out.println(otpnew);
					data.setOtp(otpnew);
					new VerifyOtp().show();
					}
				
				
			}
			


				
	}
		
		else {	
			email.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
			emailNotExist.setText("User does not exist with this email !");
			}
		}
    
    
    
    
//			String subject = "Forgot Password";
//			String message = "Verify OTP to change your password";
//
//			final String messageContent2 = "{\n" + "\"to\"" + ":\"" + email.getText() + "\", \r\n" + "\"subject\""
//					+ ":\"" + subject + "\", \r\n" + "\"message\"" + ":\"" + message + "\" \r\n" + "\n}";
//
//			System.out.println(messageContent2);
//
//			HttpURLConnection postConnection2 = HttpUtil.postJsonRequest(ApiEndPoint.OTPFORFORGOTPASSWORD,messageContent2);
//
//			int respCode2 = postConnection2.getResponseCode();
//			System.out.println("Response from the server is: \n");
//			System.out.println("The POST Request Response Code :  " + respCode2);
//			System.out.println("The POST Request Response Message : " + postConnection2.getResponseMessage());
//			if (respCode2 == HttpURLConnection.HTTP_CREATED) {
//
//				InputStreamReader inputStreamObj2 = new InputStreamReader(postConnection2.getInputStream());
//				BufferedReader br2 = new BufferedReader(inputStreamObj2);
//				String input2 = null;
//				StringBuffer stringBuffer2 = new StringBuffer();
//				while ((input2 = br2.readLine()) != null) {
//					stringBuffer2.append(input2);
//				}
//				br2.close();
//				postConnection2.disconnect();
//
//				System.out.println(stringBuffer2.toString());
//			}
//		}
//

	private static String parseEmail(String response) {
		//String emailText = email.getText();

		JSONObject jsonObject = new JSONObject(response);
		String email = jsonObject.getJSONObject("data").getString("email");
		return String.valueOf(email);

	}
	private static String parseOtp(String response) {
		//String emailText = email.getText();

		JSONObject jsonObject = new JSONObject(response);
		String otp = jsonObject.getString("otp");
		return otp;

	}
	public void back(ActionEvent event) {
		new Login().show();
		
	}


}
