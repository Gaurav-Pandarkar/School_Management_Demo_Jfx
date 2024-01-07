package notice_dashboard;

import deleteNotice.DeleteNotice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import login.Login;
import notice.Notice;
import principle.Principle;
import searchnotice.SearchNotice;
import updateNotice.UpdateNotice;

public class NoticeDashBoardController {
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button notification;
	@FXML
	private Button create;
	@FXML
	private Button delete;
	@FXML
	private Button update;
	@FXML
	private Button search;
	public void back(ActionEvent event) {
		new Principle().show();
		
	}
public void logOut(ActionEvent event) {
	new Login().show();
		
	}
public void notification(ActionEvent event) {
	
}
public void create(ActionEvent event) {
	new Notice().show();
	
}
public void update(ActionEvent event) {
	new UpdateNotice().show();
	
}
public void delete(ActionEvent event) {
	new DeleteNotice().show();
	
}
public void search(ActionEvent event) {
	new SearchNotice().show();
	
}
	

}
