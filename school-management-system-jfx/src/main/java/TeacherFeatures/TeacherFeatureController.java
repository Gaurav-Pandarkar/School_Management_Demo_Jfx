package TeacherFeatures;

import Attendance.Attendance;
import addfees.AddFees;
import createnotice.CreateNotice;
import exam_schedule_dashboard_teacher.ExamScheduleDashBoard;
import fees_dashboard_teacher.FeesDashBoard;
import generalinfo.GeneralInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import login.Login;
import notice_dashboard_teacher.NoticeDashBoard;
import search_leave_teacher.SearchLeave;
import teacher_dashboard.TeacherDashBoard;
import time_table_dashboard_teacher.TimeTableDashBoard;

public class TeacherFeatureController {
	
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



	public void notification(ActionEvent event) {
		new NoticeDashBoard().show();

	}

	public void exams(ActionEvent event) {
         new ExamScheduleDashBoard().show();
	}

	public void leave(ActionEvent event) {
		new SearchLeave().show();

	}

	public void fees(ActionEvent event) {
		new FeesDashBoard().show();

	}

	public void diary(ActionEvent event) {

	}

	public void schedule(ActionEvent event) {
		//new TimeTable().show();
		new TimeTableDashBoard().show();

	}

	public void attendance(ActionEvent event) {
       new Attendance().show();
	}
	public void back(ActionEvent event) {

      new TeacherDashBoard().show();

	}
	public void logOut(ActionEvent event) {
		new Login().show();
		
	}

}
