package fees_dashboard;

import addfees.AddFees;
import deletefees.DeleteFees;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import login.Login;
import principle.Principle;
import searchFees.SearchFees;
import updateFees.UpdateFees;

public class FeesDashBoardController {
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button addFees;
	@FXML
	private Button updateFees;
	@FXML
	private Button deleteFees;
	@FXML
	private Button fees;
	@FXML
	private Button searchFees;
	public void back(ActionEvent event) {
		new Principle().show();
		
	}
public void logOut(ActionEvent event) {
	new Login().show();
		
	}
public void addFees(ActionEvent event) {
	new AddFees().show();
	
}public void updateFees(ActionEvent event) {
	new UpdateFees().show();
	
}
public void deleteFees(ActionEvent event) {
	new DeleteFees().show();
	
}
public void fees(ActionEvent event) {
	
}
public void searchFees(ActionEvent event) {
	new SearchFees().show();
	
}

	

}
