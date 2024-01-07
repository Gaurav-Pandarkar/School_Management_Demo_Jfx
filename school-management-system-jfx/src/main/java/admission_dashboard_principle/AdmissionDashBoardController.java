package admission_dashboard_principle;

import addTeacher.AddTeacher;
import generalinfo.GeneralInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import login.Login;
import principle.Principle;

public class AdmissionDashBoardController {
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button admission;
	@FXML
	private Button student;
	@FXML
	private Button teacher;
	public void back(ActionEvent event) {
		new Principle().show();
		
	}
public void logOut(ActionEvent event) {
	new Login().show();
		
	}
public void admission(ActionEvent event) {
	
}
public void student(ActionEvent event) {
	new GeneralInformation().show();
	
}
public void teacher(ActionEvent event) {
	new AddTeacher().show();
}

}
