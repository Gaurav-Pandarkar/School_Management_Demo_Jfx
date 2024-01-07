package leave_dashboard;

import deleteLeave.DeleteLeave;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import leave.Leave;
import leaveRequest.LeaveRequest;
import login.Login;
import principle.Principle;
import searchLeaveStud.SearchLeaveStud;
import student_dashboard.StudentDashBoard;
import updateLeave.UpdateLeave;

public class LeaveDashBoardController {
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button leave;
	@FXML
	private Button add;
	@FXML
	private Button update;
	@FXML
	private Button search;
	@FXML
	private Button delete;
	public void back(ActionEvent event) {
		new StudentDashBoard().show();
		
	}
public void logOut(ActionEvent event) {
		new Login().show();
		}
public void leave(ActionEvent event) {
	
}
public void add(ActionEvent event) {
	new LeaveRequest().show();
	
}
public void update(ActionEvent event) {
	new UpdateLeave().show();
	
}
public void delete(ActionEvent event) {
	new DeleteLeave().show();
	
}
public void search(ActionEvent event) {
	new SearchLeaveStud().show();
	
}


}
