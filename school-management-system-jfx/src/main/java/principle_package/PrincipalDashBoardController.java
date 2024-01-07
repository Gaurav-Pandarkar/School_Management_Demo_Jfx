package principle_package;

import java.awt.Desktop;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import login.Login;
import principle.Principle;
import student_dashboard_principal.StudentDashBoard;

public class PrincipalDashBoardController {
	@FXML
	private Button principal;
	@FXML
	private Button teacher;
	@FXML
	private Button student;
	@FXML
	private Button parent;
	@FXML
	private Button logOut;
	@FXML
	private Hyperlink locationToVisit;
	public void principal(ActionEvent event) {
		new Principle().show();
		
	}
public void teacher(ActionEvent event) {
		
	}
public void student(ActionEvent event) {
	new StudentDashBoard().show();
	
}
public void parent(ActionEvent event) {
	
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
