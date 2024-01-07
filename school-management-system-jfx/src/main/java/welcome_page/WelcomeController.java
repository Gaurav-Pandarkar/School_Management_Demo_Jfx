package welcome_page;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import login.Login;
import registration1.Registration;

public class WelcomeController {
	@FXML
	private Button login;
	@FXML
	private Button registration;
	/*
	@FXML
	public void loginmouseentered() {
		// Apply the hover effect when the mouse enters the button
		//login.setStyle("-fx-background-color: #8e4db4"); // Darker green background color
		// Add other style changes as needed for the hover effect
	}

	@FXML
	public void loginmouseexited() {
		// Reset the style when the mouse exits the button (optional)
		//login.setStyle("-fx-background-color: #ff3e0d"); // Original green background color
		// Reset other style changes if any
	}
	@FXML
	public void registrationmouseentered() {
		// Apply the hover effect when the mouse enters the button
		login.setStyle("-fx-background-color: #8e4db4"); // Darker green background color
		// Add other style changes as needed for the hover effect
	}

	@FXML
	public void registrationmouseexited() {
		// Reset the style when the mouse exits the button (optional)
		login.setStyle("-fx-background-color: #ff3e0d"); // Original green background color
		// Reset other style changes if any
	}
	
	*/

	public void login(ActionEvent event) {

		new Login().show();

	}

	public void registration(ActionEvent event) {
		new Registration().show();

	}

}
