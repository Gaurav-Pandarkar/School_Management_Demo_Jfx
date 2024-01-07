package maindashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import login.Login;
import principle.Principle;

public class DashboardController {
	@FXML
	private Button parent;
	@FXML
	private Button principal;
	@FXML
	private Button student;
	@FXML
	private Button teacher;
	@FXML
	private Button back;

	public void parent(ActionEvent event) {

	}

	public void principal(ActionEvent event) {
		new Principle().show();

	}

	public void student(ActionEvent event) {

	}

	public void teacher(ActionEvent event) {

	}

	public void back(ActionEvent event) {
		new Login().show();

	}

}
