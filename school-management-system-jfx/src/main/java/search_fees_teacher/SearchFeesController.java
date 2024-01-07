package search_fees_teacher;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.dnyanyog.entity.FeesInfo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.ApiEndPoint;
import fees_dashboard_teacher.FeesDashBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import login.Login;

public class SearchFeesController {

	@FXML
	private TableView<FeesInfo> tableView;

	@FXML
	private TableColumn<FeesInfo, Long> grnNoColumn;

	@FXML
	private TableColumn<FeesInfo, String> studentNameColumn;

	@FXML
	private TableColumn<FeesInfo, String> appliedClassColumn;



	@FXML
	private TableColumn<FeesInfo, String> totalFeesColumn;

	@FXML
	private TableColumn<FeesInfo, String> feesPaidColumn;

	@FXML
	private TableColumn<FeesInfo, String> acadmicYearColumn;

	@FXML
	private TableColumn<FeesInfo, String> remainingFeesColumn;

	@FXML
	private TextField grnNo;

	@FXML
	private Button search;
	@FXML
	private Button back;
	@FXML
	private Button fees;
	@FXML
	private Button logOut;




	public void initialize() {
		// Set up the column mappings
		grnNoColumn.setCellValueFactory(new PropertyValueFactory<>("grnNo"));
		studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
		appliedClassColumn.setCellValueFactory(new PropertyValueFactory<>("appliedClass"));

	
		totalFeesColumn.setCellValueFactory(new PropertyValueFactory<>("totalFees"));
		feesPaidColumn.setCellValueFactory(new PropertyValueFactory<>("feesPaid"));
		acadmicYearColumn.setCellValueFactory(new PropertyValueFactory<>("acadmicYear"));
		remainingFeesColumn.setCellValueFactory(new PropertyValueFactory<>("remainingFees"));




	}

	private void fetchDataAndPopulateTableView() {

		
		
		String id = grnNo.getText();
		String apiUrl = ApiEndPoint.SEARCHFEES + id;

		try {
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				Scanner scanner = new Scanner(connection.getInputStream());
				StringBuilder response = new StringBuilder();
				while (scanner.hasNextLine()) {
					response.append(scanner.nextLine());
				}
				scanner.close();
				connection.disconnect();

				// Parse the JSON response and populate the TableView
				List<FeesInfo> feesInfoList = parseJsonResponse(response.toString());
				tableView.getItems().setAll(feesInfoList);
			} else {
				System.out.println("Failed to fetch data from the backend API");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<FeesInfo> parseJsonResponse(String jsonResponse) {
	    List<FeesInfo> feesInfoList = new ArrayList<>();
	    try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode root = objectMapper.readTree(jsonResponse);

	        if (root.isArray()) {
	            for (JsonNode node : root) {
	                long grnNo = node.get("grnNo").asLong();
	                String studentName = node.get("studentName").asText();
	                String appliedClass = node.get("appliedClass").asText();
	                String totalFees = node.get("totalFees").asText();
	                String feesPaid = node.get("feesPaid").asText();
	                String acadmicYear = node.get("acadmicYear").asText();
	                String remainingFees = node.get("remainingFees").asText();

	                

	                if (node.get("id") != null) {
	                    // Use the actual value if "id" field is present
	                    long id = node.get("id").asLong();
	                    // Create a new FeesInfo object with all the fields
	                    FeesInfo feesInfo = new FeesInfo(grnNo, studentName, appliedClass, id, totalFees, feesPaid, acadmicYear, remainingFees);
	                    feesInfoList.add(feesInfo);
	                } else {
	                    // Use a default value for "id" if it's missing in the JSON response
	                    long id = -1; // You can choose an appropriate default value here
	                    // Create a new FeesInfo object with all the fields except "id"
	                    FeesInfo feesInfo = new FeesInfo(grnNo, studentName, appliedClass, id, totalFees, feesPaid, acadmicYear, remainingFees);
	                    feesInfoList.add(feesInfo);
	                }
	            }
	            
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return feesInfoList;
	}

	@FXML
	private void search(ActionEvent event) {
	    fetchDataAndPopulateTableView();
	}
	public void back(ActionEvent event) {
		new FeesDashBoard().show();
	   
	}
	public void logOut(ActionEvent event) {
		new Login().show();
		
	   	}
	public void fees(ActionEvent event) {
	   
	}

}
