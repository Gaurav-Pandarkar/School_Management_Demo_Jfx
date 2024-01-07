package student_dashboard;

import java.awt.Desktop;
import java.io.IOException;

import Attendance.Attendance;
import exam_schedule_student.SearchExamSchedule;
import exams_shedule_dashboard.ExamScheduleDashBoard;
import fees_dashboard.FeesDashBoard;
import generalinfo.GeneralInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import leave_dashboard.LeaveDashBoard;
import login.Login;
import notice_dashboard.NoticeDashBoard;
import principle.Principle;
import searchLeave.SearchLeave;
import search_notice_student.SearchNotice;
import time_table_dashboard.TimeTableDashBoard;
import time_table_student.SearchTimeTable;

public class StudentDashBoardController {
	
	@FXML
	private Button notification;
	@FXML
	private Button exams;
	@FXML
	private Button leave;
	
	@FXML
	private Button diary;
	@FXML
	private Button schedule;
	
	@FXML
	private Button back;
	@FXML
	private Hyperlink locationToVisit;
	public void locationToVisit(ActionEvent event) throws IOException {
		Desktop desktop = Desktop.getDesktop();
		desktop.browse(java.net.URI.create("https://maps.app.goo.gl/V6B8jeyDTYW3vhNv8"));

		
		
	}
	

	public void notification(ActionEvent event) {
		new SearchNotice().show();

	}

	public void exams(ActionEvent event) {
		new SearchExamSchedule().show();
         
	}

	public void leave(ActionEvent event) {
		new LeaveDashBoard().show();
		//new SearchLeave().show();

	}

	

	public void diary(ActionEvent event) {
		

	}

	public void schedule(ActionEvent event) {
		new SearchTimeTable().show();
		//new TimeTable().show();

	}

	
	public void back(ActionEvent event) {
	//	new Principle().show();
		new Login().show();
		

	}
	public void logOut(ActionEvent event) {
		new Login().show();
		
	}

}
