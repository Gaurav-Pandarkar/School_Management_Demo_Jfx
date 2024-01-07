package verifyotp;

import data_model.OtpModel;
import forgot_password.ForgotPassword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import updatepassword.NewPassword;

public class VerifyOtpController {

	@FXML
	private TextField otp;
	@FXML
	private Button verifyOtp;
	@FXML
	private Label validationForVerify;

	private OtpModel data = new OtpModel();

	public void verifyOtp(ActionEvent event) {
		String otpByTextField = otp.getText();
		String oldOtp = data.info[0];

		if (oldOtp.equals(otpByTextField)) {

			otp.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
			validationForVerify.setText("OTP verify Successfuly !");
			otp.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
             new NewPassword().show();
		}

		else {
			// validationForMobileNo.setOpacity(0);
			otp.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
			validationForVerify.setText("Wrong OTP !");
		}
	}
	public void back(ActionEvent event) {
		new ForgotPassword().show();
		
	}

}
