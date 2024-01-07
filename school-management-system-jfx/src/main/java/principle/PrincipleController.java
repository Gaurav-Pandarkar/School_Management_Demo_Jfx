package principle;


import Attendance.Attendance;
import addfees.AddFees;
import admission_dashboard_principle.AdmissionDashBoard;
import createnotice.CreateNotice;
import exams_shedule_dashboard.ExamScheduleDashBoard;
import fees_dashboard.FeesDashBoard;
import generalinfo.GeneralInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import leave_dashboard.LeaveDashBoard;
import login.Login;
import notice_dashboard.NoticeDashBoard;
import principle_package.PrincipalDashBoard;
import searchLeave.SearchLeave;
import time_table_dashboard.TimeTableDashBoard;

public class PrincipleController {
	@FXML
	private Button admission;
	@FXML
	private Button notification;
	@FXML
	private Button exams;
	@FXML
	private Button leave;
	@FXML
	private Button fees;
	@FXML
	private Button diary;
	@FXML
	private Button schedule;
	@FXML
	private Button attendance;
	@FXML
	private Button back;

	public void admission(ActionEvent event) {
		//new GeneralInformation().show();
		new AdmissionDashBoard().show();
	}

	public void notification(ActionEvent event) {
		new NoticeDashBoard().show();

	}

	public void exams(ActionEvent event) {
		new ExamScheduleDashBoard().show();
         
	}

	public void leave(ActionEvent event) {
		//new LeaveDashBoard().show();
		new SearchLeave().show();

	}

	public void fees(ActionEvent event) {
		new FeesDashBoard().show();

	}

	public void diary(ActionEvent event) {
		

	}

	public void schedule(ActionEvent event) {
		new TimeTableDashBoard().show();
		//new TimeTable().show();

	}

	public void attendance(ActionEvent event) {
       new Attendance().show();
	}
	public void back(ActionEvent event) {
		new PrincipalDashBoard().show();

	}
	public void logOut(ActionEvent event) {
		new Login().show();
		
	}

}
