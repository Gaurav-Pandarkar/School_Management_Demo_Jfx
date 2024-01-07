package registration1;

import data_model.DataModelUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import registration2.Registration2;
import welcome_page.Welcome;

public class RegistrationController {
	@FXML
	private TextField mobileNo;
	@FXML
	private TextField userRole;
	@FXML
	private TextField email;
	
	@FXML
	private Button next;
	@FXML
	private Button back;
	@FXML
	private Label validationForMobileNo;
	@FXML
	private Label validationForUserRole;
	
	@FXML
	private Label validationForEmail;
	private	DataModelUser dataModel=new DataModelUser();
	public void next(ActionEvent event) {
        dataModel.setMobileNo(mobileNo.getText());
        dataModel.setUserRole(userRole.getText());
        dataModel.setEmail(email.getText());

        if (mobileNo.getText().isEmpty()) {
            mobileNo.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
            validationForMobileNo.setText("Mobile no is required");
        } else {
            validationForMobileNo.setOpacity(0);
            mobileNo.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
        }

        if (userRole.getText().isEmpty()) {
            userRole.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
            validationForUserRole.setText("User role is required");
        } else {
            validationForUserRole.setOpacity(0);
            userRole.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
        }

        if (email.getText().isEmpty()) {
            email.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
            validationForEmail.setText("Email is required");
        } else {
            validationForEmail.setOpacity(0);
            email.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
        }

        if (!mobileNo.getText().isEmpty() && !userRole.getText().isEmpty() && !email.getText().isEmpty()) {
            new Registration2().show();
        }
    }
		
		
		//new Registration2().show();

	
	public void back(ActionEvent event) {
		new Welcome().show();
		
	}
	
	
	
	
	

}
