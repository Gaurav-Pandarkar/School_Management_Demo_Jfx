package academic_info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import common.ApiEndPoint;
import common.HttpUtil;
import data_model.DataModel;
import generalinfo.GeneralInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AcademicInformationController implements Initializable {
	@FXML
	private TextField studentBirthDate;
	@FXML
	private ComboBox gender;
	@FXML
	private ComboBox classToApply;
	@FXML
	private TextField schoolName;
	@FXML
	private TextField studentMobileNo;
	@FXML
	private TextField email;
	@FXML
	private Button save;
	@FXML
	private Button back;
	@FXML
	private Label validationForStudentBirthDate;
	@FXML
	private Label validationForSchoolName;
	@FXML
	private Label validationForStudentMobileNo;
	@FXML
	private Label validationForStudentEmail;
	@FXML
	private Label validationForInformation;
	@FXML
	private Button logOut;
	@FXML
	private Button admission;
	
	
	// private DatePicker datePicker;
	private DataModel dataModel1 = new DataModel();

	public void save(ActionEvent event) throws IOException {
		
		
		//DatePicker datePicker = new DatePicker();
		//String studentFirstName = dataModel1.getStudentFirstName();
		//String fatherFirstName = dataModel1.getFatherFirstName();
		//String motherFirstName = dataModel1.getMotherFirstName();
		//String lastName = dataModel1.getLastName();
	//	String parentMobileNo = dataModel1.getMobileNo();
		//String address = dataModel1.getAddress();
		//LocalDate date = datePicker.getValue();
		if (studentBirthDate.getText().isEmpty()) {
			studentBirthDate.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
			validationForStudentBirthDate.setText("Student birth date is required");
	        } else {
	        	validationForStudentBirthDate.setOpacity(0);
	        	studentBirthDate.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
	        }

	        if (schoolName.getText().isEmpty()) {
	        	schoolName.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
	        	validationForSchoolName.setText("School name is required");
	        } else {
	        	validationForSchoolName.setOpacity(0);
	        	schoolName.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
	        }
	        if (studentMobileNo.getText().isEmpty()) {
	        	studentMobileNo.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
	        	validationForStudentMobileNo.setText("Mobile no is required");
		        } else {
		        	validationForStudentMobileNo.setOpacity(0);
		        	studentMobileNo.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
		        }

		        if (email.getText().isEmpty()) {
		        	email.setStyle("-fx-border-color : red ; -fx-border-width:2px ;");
		        	validationForStudentEmail.setText("Email is required");
		        } else {
		        	validationForStudentEmail.setOpacity(0);
		        	email.setStyle("-fx-border-color : green ; -fx-border-width:2px ;");
		        }

		      
		
		
		
		String studentFirstName=dataModel1.info[0];
		String fatherFirstName= dataModel1.info[1];
		String motherFirstName=dataModel1.info[2];
		String lastName=dataModel1.info[3];
		String parentMobileNo=dataModel1.info[4];
		String address=dataModel1.info[5];
		
		
		System.out.println(dataModel1.info[0]);
		//System.out.println(dataModel1.getStudentFirstName());
		final String messageContent = "{\n" + "  \"studentFirstName\": \"" + studentFirstName + "\",\n"
				+ "  \"fatherFirstName\": \"" + fatherFirstName + "\",\n" + "  \"motherFirstName\": \""
				+ motherFirstName + "\",\n" + "  \"lastName\": \"" + lastName + "\",\n" + "  \"parentMobileNo\": \""
				+ parentMobileNo + "\",\n" + "  \"address\": \"" + address + "\",\n" + "  \"studentBirthDate\": \""
				+ studentBirthDate.getText() + "\",\n" + "  \"gender\": \"" + gender.getValue() + "\",\n" + "  \"classToApply\": \""
				+ classToApply.getValue() + "\",\n" + "  \"previousSchoolName\": \"" + schoolName.getText() + "\",\n"
				+ "  \"studentMobileNo\": \"" + studentMobileNo.getText() + "\",\n" + "  \"studentEmail\": \""
				+ email.getText() + "\"\n" + "}";

		System.out.println(messageContent);

		HttpURLConnection postConnection = HttpUtil.postJsonRequest(ApiEndPoint.SIGNUP_STUDENT, messageContent);
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
			new GeneralInformation().show();

		} else {
			System.out.println("POST Request did not work.Please check information");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("POST Request did not work.Please check information");
			alert.showAndWait();
			return;
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<String> list = FXCollections.observableArrayList("Male", "Female", "Other");
		gender.setItems(list);
		ObservableList<String> list1 = FXCollections.observableArrayList("first", "second", "third", "fourth", "fifth",
				"sixth", "seventh", "eight", "nineth", "tenth");
		classToApply.setItems(list1);

	}
	public void back(ActionEvent event) {
		new GeneralInformation().show();
	}
	public void logOut(ActionEvent event) {
		new GeneralInformation().show();
	}
	public void admission(ActionEvent event) {
		new GeneralInformation().show();
	}

}
