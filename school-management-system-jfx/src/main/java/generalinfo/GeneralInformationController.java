package generalinfo;

import academic_info.AcademicInformation;
import data_model.DataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import principle.Principle;
import registration2.Registration2;

public class GeneralInformationController {
	@FXML
	private TextField studentFirstName;
	@FXML
	private TextField fatherFirstName;
	@FXML
	private TextField motherFirstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField parentMobileNo;
	@FXML
	private TextField address;
	@FXML
	private Button next;
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button admission;
	@FXML
	private Label validationForStudentFirstName;
	@FXML
	private Label validationForFatherFirstName;
	@FXML
	private Label validationForMotherFirstName;
	
	@FXML
	private Label validationForLastName;
	@FXML
	private Label validationForParentMobileNo;
	@FXML
	private Label validationForAddress;
	
	private DataModel dataModel = new DataModel();

	public void next(ActionEvent event) {
		  if (studentFirstName.getText().isEmpty()) {
			  studentFirstName.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
			  validationForStudentFirstName.setText("Student first name is required");
	        } else {
	        	validationForStudentFirstName.setOpacity(0);
	            studentFirstName.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
	        }

	        if (fatherFirstName.getText().isEmpty()) {
	        	fatherFirstName.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
	        	validationForFatherFirstName.setText("Father first name is required");
	        } else {
	        	validationForFatherFirstName.setOpacity(0);
	            fatherFirstName.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
	        }

	        if (motherFirstName.getText().isEmpty()) {
	        	motherFirstName.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
	        	validationForMotherFirstName.setText("Mother first name is required");
	        } else {
	        	validationForMotherFirstName.setOpacity(0);
	            motherFirstName.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
	        }
	        if (lastName.getText().isEmpty()) {
	        	lastName.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
	        	validationForLastName.setText("Last name is required");
		        } else {
		        	validationForLastName.setOpacity(0);
		            lastName.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
		        }

		        if (parentMobileNo.getText().isEmpty()) {
		        	parentMobileNo.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
		        	validationForParentMobileNo.setText("Parent mobile number is required");
		        } else {
		        	validationForParentMobileNo.setOpacity(0);
		            parentMobileNo.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
		        }

		        if (address.getText().isEmpty()) {
		        	address.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
		        	validationForAddress.setText("address is required");
		        } else {
		        	validationForAddress.setOpacity(0);
		            address.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
		        }

		        
		dataModel.setAddress(address.getText());
		dataModel.setFatherFirstName(fatherFirstName.getText());
		dataModel.setLastName(lastName.getText());
		dataModel.setMobileNo(parentMobileNo.getText());
		dataModel.setMotherFirstName(motherFirstName.getText());
		dataModel.setStudentFirstName(studentFirstName.getText());

        if (!studentFirstName.getText().isEmpty() && !fatherFirstName.getText().isEmpty() && !motherFirstName.getText().isEmpty() && !lastName.getText().isEmpty() && !parentMobileNo.getText().isEmpty() && !address.getText().isEmpty()) {
        	new AcademicInformation().show();
        }
		//new AcademicInformation().show();
		// System.out.println(dataModel.getFatherFirstName());
	}

	public void back(ActionEvent event) {
		new Principle().show();

	}
	public void logOut(ActionEvent event) {
		
	}
	public void admission(ActionEvent event) {
		

	}

}
