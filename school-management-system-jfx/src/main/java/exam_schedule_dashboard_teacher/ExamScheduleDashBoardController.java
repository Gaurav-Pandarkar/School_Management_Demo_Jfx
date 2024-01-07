package exam_schedule_dashboard_teacher;

import TeacherFeatures.TeacherFeature;
import create_exam_schedule_teacher.AddExamSchedule;
import delete_exam_schedule_teacher.DeleteSchedule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import login.Login;
import principle.Principle;
import search_exam_schedule_teacher.SearchExamSchedule;
import update_exam_schedule_teacher.UpdateExamSchedule;

public class ExamScheduleDashBoardController {
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button shedule;
	@FXML
	private Button create;

	private Button update;

	@FXML
	private Button delete;
	@FXML
	private Button search;
	public void back(ActionEvent event) {
		new TeacherFeature().show();
		
	}
public void logOut(ActionEvent event) {
	new Login().show();
		
	}
public void shedule(ActionEvent event) {
	
}
public void create(ActionEvent event) {
	new AddExamSchedule().show();
	
	
}
public void update(ActionEvent event) {
	new UpdateExamSchedule().show();
	
}
public void delete(ActionEvent event) {
	new DeleteSchedule().show();
	
}
public void search(ActionEvent event) {
	
	new SearchExamSchedule().show();
}

}
