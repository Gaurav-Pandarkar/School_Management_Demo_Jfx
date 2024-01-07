package time_table_dashboard;

import deleteTimeTable.DeleteTimeTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import login.Login;
import principle.Principle;
import searchTimeTable.SearchTimeTable;
import timeTable.TimeTable;
import updateTimeTable.UpdateTimetable;

public class TimeTableDashBoardController {
	@FXML
	private Button back;
	@FXML
	private Button create;
	@FXML
	private Button update;
	@FXML
	private Button delete;
	@FXML
	private Button search;
	@FXML
	private Button logOut;
	@FXML
	private Button timeTable;

	public void back(ActionEvent event) {
		new Principle().show();

	}

	public void create(ActionEvent event) {
		new TimeTable().show();
	}

	public void update(ActionEvent event) {
		new UpdateTimetable().show();

	}

	public void delete(ActionEvent event) {
		new DeleteTimeTable().show();

	}

	public void search(ActionEvent event) {
		new SearchTimeTable().show();

	}

	public void logOut(ActionEvent event) {
		new Login().show();

	}

	public void timeTable(ActionEvent event) {

	}

}
