package application;

import Attendance.Attendance;
import academic_info.AcademicInformation;
import addexamSchedule.AddExamSchedule;
import addfees.AddFees;
import createnotice.CreateNotice;
import deleteExamSchedule.DeleteSchedule;
import deleteLeave.DeleteLeave;
import deleteNotice.DeleteNotice;
import deleteTimeTable.DeleteTimeTable;
import deletefees.DeleteFees;
import generalinfo.GeneralInformation;
import javafx.application.Application;
import javafx.stage.Stage;
import leaveRequest.LeaveRequest;
import leave_dashboard.LeaveDashBoard;
import login.Login;
import maindashboard.Dashboard;
import notice.Notice;
import notice_dashboard.NoticeDashBoard;
import principle.Principle;
import principle_package.PrincipalDashBoard;
import print_fees.PrintFeesReciept;
import registration1.Registration;
import registration2.Registration2;
import searchExamSchedule.SearchExamSchedule;
import searchFees.SearchFees;
import searchLeave.SearchLeave;
import searchLeaveStud.SearchLeaveStud;
import searchTimeTable.SearchTimeTable;
import searchnotice.SearchNotice;
import stage.StageMaster;
import timeTable.TimeTable;
import time_table_dashboard.TimeTableDashBoard;
import updateExamSchedule.UpdateExamSchedule;
import updateFees.UpdateFees;
import updateLeave.UpdateLeave;
import updateNotice.UpdateNotice;
import updateTimeTable.UpdateTimetable;
import updatepassword.NewPassword;
import verifyotp.VerifyOtp;
import welcome_page.Welcome;
import welcome_parent.WelcomeParent;
import welcome_student.WelcomeStudent;

public class ApplicationMain extends Application {
	public static void main(String args[]) {
		
		launch(args);
		
	}
	public void start(Stage primaryStage) {
		StageMaster.setStage(primaryStage);

	new Welcome().show();
		//new AcademicInformation().show();
		//new GeneralInformation().show();
		//new Login().show();
		//new Principle().show();
	//	new Registration().show();
		//new Registration2().show();
	//new WelcomeParent().show();
	//new WelcomeStudent().show();
		//new Attendance().show();	
		//new Dashboard().show();
		//new Principle().show();
	//new GeneralInformation().show();
		//new AddFees().show();
		//new DeleteFees().show();
		//new CreateNotice().show();
		//new SearchNotice().show();
		//new VerifyOtp().show();
		//new NewPassword().show();
		//new PrincipalDashBoard().show();
		//new AddFees().show();
		//new PrintFeesReciept().show();
		//new SearchFees().show();
		//new DeleteFees().show();
		//new UpdateFees().show();
		//new TimeTableDashBoard().show();
		//new TimeTable().show();
		//new DeleteTimeTable().show();
		//new SearchTimeTable().show();
		//new UpdateTimetable().show();
		//new Notice().show();
		//new DeleteNotice().show();
		//new UpdateNotice().show();
		//new NoticeDashBoard().show();
		//new AddExamSchedule().show();
		//new DeleteSchedule().show();
		//new SearchExamSchedule().show();
		//new UpdateExamSchedule().show();
		//new LeaveDashBoard().show();
	//	new LeaveRequest().show();
		//new DeleteLeave().show();
	//	new SearchLeaveStud().show();
		//new UpdateLeave().show();
		//new SearchLeave().show();
	} 

}
