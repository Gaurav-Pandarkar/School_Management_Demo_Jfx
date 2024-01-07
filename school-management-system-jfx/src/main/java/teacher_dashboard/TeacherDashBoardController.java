package teacher_dashboard;

import java.awt.Desktop;
import java.io.IOException;

import TeacherFeatures.TeacherFeature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import login.Login;
import student_dashboard_teacher.StudentDashBoard;

public class TeacherDashBoardController {
	
	@FXML
	private Button teacher;
	@FXML
	private Button student;
	
	@FXML
	private Button logOut;
	@FXML
	private Hyperlink locationToVisit;
	
public void teacher(ActionEvent event) {
	new TeacherFeature().show();
		
	}
public void student(ActionEvent event) {
	new StudentDashBoard().show();
	
}

public void locationToVisit(ActionEvent event) throws IOException {
	Desktop desktop = Desktop.getDesktop();
	desktop.browse(java.net.URI.create("https://maps.app.goo.gl/V6B8jeyDTYW3vhNv8"));

	
	
}
public void logOut(ActionEvent event) {
	new Login().show();	
}
public void back(ActionEvent event) {
	new Login().show();
	
	
}



}
