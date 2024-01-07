package print_fees_teacher;

import java.net.URL;
import java.util.ResourceBundle;

import add_fees_teacher.AddFees;
import data_model.DataModel;
import data_model.FeesDataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import login.Login;
import javafx.print.PrinterJob;
import javafx.scene.Node;

public class PrintFeesRecieptController  implements Initializable {
	
	@FXML
	private TextField mobile;
	

	@FXML
	private TextField description;

	
	
	@FXML
	private ComboBox<String> payMeth;
	@FXML
	private TextField transaction;
	
	@FXML
	private Button genrate;

	@FXML
	private Button print;

	@FXML
	private Button reset;
	@FXML
	private Button back;

	@FXML
	private Button logOut;
	@FXML
	private Button fees;


	@FXML
	private TextArea reciept;

	@FXML
	private DatePicker date;

	@FXML
	private Label validationAlertLabel;
	private FeesDataModel dataModel1 = new FeesDataModel();

	
	public void printTextAreaContent(TextArea reciept) {
		PrinterJob printerJob = PrinterJob.createPrinterJob();
		if (printerJob != null && printerJob.showPrintDialog(reciept.getScene().getWindow())) {
			Node contentToPrint = reciept;
			boolean printed = printerJob.printPage(contentToPrint);
			if (printed) {
				printerJob.endJob();
			} else {
				showValidationAlert();
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<String> list = FXCollections.observableArrayList("Cash", "Online", "Cheque");
		payMeth.setItems(list);
		
	}


	public void genrate(ActionEvent event) {
		
		
		String grnFromAdd=dataModel1.feesInfo[0];
		String studentNameFromAdd=dataModel1.feesInfo[1];
		String totalfees=dataModel1.feesInfo[2];
		String feespaid=dataModel1.feesInfo[3];
		String remainingfees=dataModel1.feesInfo[4];
		
		reciept.setText("************************************************\n");
		reciept.setText(reciept.getText() + "                  Fees Reciept                 \n");
		reciept.setText(reciept.getText() + "************************************************\n");
		reciept.setText(reciept.getText() + " Date : " + date.getValue() + "\n");

		reciept.setText(reciept.getText() + " GRN No : " + grnFromAdd + "\n");

		reciept.setText(reciept.getText() + " Student Name : " + studentNameFromAdd + "\n");

		//reciept.setText(reciept.getText() + " Last Name : " + lname.getText() + "\n");
		reciept.setText(reciept.getText() + " Mobile No : " + mobile.getText() + "\n");

		reciept.setText(reciept.getText() + " Total Fees : " + totalfees + "\n");
		reciept.setText(reciept.getText() + " Fees Paid : " + feespaid + "\n");
		reciept.setText(reciept.getText() + " Remaining Fees : " + remainingfees + "\n");
		reciept.setText(reciept.getText() + " Payment Method : " + payMeth.getValue() + "\n");
		reciept.setText(reciept.getText() + " Transaction ID : " + transaction.getText() + "\n");

		reciept.setText(reciept.getText() + " Description : " + description.getText() + "\n");
		reciept.setText(reciept.getText() + "                                               Signature \n");

	}

	public void print(ActionEvent event) {
		printTextAreaContent(reciept);
	}

	public void reset(ActionEvent event) {
		
		reciept.clear();
		hideValidationAlert();
	}
	
	private void showValidationAlert() {
		validationAlertLabel.setText("Printing failed.");
	}

	private void hideValidationAlert() {
		validationAlertLabel.setText(null);
	}
	public void back(ActionEvent event) {
		new AddFees().show();
		
	}

	public void logOut(ActionEvent event) {
		new Login().show();
		
	
	}
	public void fees(ActionEvent event) {
		
		
	}
	

}
