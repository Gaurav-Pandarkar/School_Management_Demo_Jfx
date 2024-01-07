package searchLeave;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dnyanyog.entity.LeaveApplication;
import org.dnyanyog.entity.LeaveStatus;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.ApiEndPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import leave_dashboard.LeaveDashBoard;
import login.Login;
import principle.Principle;

public class SearchLeaveController {

	@FXML
	private TableView<LeaveApplication> leaveRequestTableView;

	@FXML
	private TableColumn<LeaveApplication, Long> idColumn;

	@FXML
	private TableColumn<LeaveApplication, Long> grnNoColumn;

	@FXML
	private TableColumn<LeaveApplication, String> studentNameColumn;

	@FXML
	private TableColumn<LeaveApplication, String> startDateColumn;

	@FXML
	private TableColumn<LeaveApplication, String> endDateColumn;

	@FXML
	private TableColumn<LeaveApplication, String> reasonColumn;

	@FXML
	private TableColumn<LeaveApplication, String> statusColumn;

	@FXML
	private TableColumn<LeaveApplication, String> responseColumn;

	@FXML
	private ComboBox status;

	@FXML
	private Button search;
	
	@FXML
	private TextField idnumber;
	
	@FXML
	private ComboBox leavestatus;
	
	@FXML
	private Button update;
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button leave;

	private static final Logger LOGGER = Logger.getLogger(SearchLeaveController.class.getName());

	public void initialize() {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		grnNoColumn.setCellValueFactory(new PropertyValueFactory<>("grnNO"));
		studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
		startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		responseColumn.setCellValueFactory(new PropertyValueFactory<>("response"));

		
	}

	private void fetchDataAndPopulateTableView() {

		Object selectStatus = status.getValue();
		if (selectStatus == null) {
			LOGGER.log(Level.WARNING, "Please select a Status.");
			return;
		}

		String Status = selectStatus.toString();

		String apiUrl = ApiEndPoint.SEARCHLEAVEBYSTATUS + Status;

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
				List<LeaveApplication> leaveApplications = parseJsonResponse(response.toString());
				leaveRequestTableView.getItems().setAll(leaveApplications);
			} else {
				LOGGER.log(Level.WARNING, "Failed to fetch data from the backend API");
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while fetching data from the backend API", e);
		}
	}

	private List<LeaveApplication> parseJsonResponse(String jsonResponse) {
		List<LeaveApplication> leaveApplications = new ArrayList<>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode root = objectMapper.readTree(jsonResponse);

			if (root.isArray()) {
				for (JsonNode node : root) {
					long id = node.get("id").asLong();
					long grnNo = node.get("grnNO").asLong();
					String studentName = node.get("studentName").asText();
					String startDate = node.get("startDate").asText();
					String endDate = node.get("endDate").asText();
					String reason = node.get("reason").asText();
					String status = node.get("status").asText();
					String response = node.get("response").asText();

					LeaveApplication leaveApplication = new LeaveApplication();
					leaveApplication.setId(id);
					leaveApplication.setGrnNO(grnNo);
					leaveApplication.setStudentName(studentName);
					leaveApplication.setStartDate(LocalDate.parse(startDate));
					leaveApplication.setEndDate(LocalDate.parse(endDate));
					leaveApplication.setReason(reason);
					leaveApplication.setStatus(LeaveStatus.valueOf(status));
					leaveApplication.setResponse(response);

					leaveApplications.add(leaveApplication);
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "An error occurred while parsing JSON response", e);
		}

		return leaveApplications;
	}
	
	public void search(ActionEvent event) {
		fetchDataAndPopulateTableView();
	}
	
	private void updateData() {
        String leaveValue = leavestatus.getValue().toString();
        String IDnumber = idnumber.getText();

        String apiUrl = ApiEndPoint.UPDATELEAVESTATUS + IDnumber + "/" + leaveValue;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String payload = "{}"; 

           
            
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(payload);
                outputStream.flush();
                
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = reader.readLine();
                reader.close();
                showAlert(Alert.AlertType.INFORMATION, "Update Successful", "The data has been successfully updated!");
                System.out.println("Update Successful: " + response);
            } else {
                showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update the data. Please try again later.");
                System.out.println("Update Failed: " + responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	 @FXML
	    private void addUpdatedData(ActionEvent event) {
	        updateData();
	    }

	    private void showAlert(Alert.AlertType type, String title, String content) {
	        Alert alert = new Alert(type);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	    public void back(ActionEvent event) {
	    	//new LeaveDashBoard().show();
	    	new Principle().show();
	    	
	    }
public void logOut(ActionEvent event) {
	new Login().show();
	    	
	    }
public void leave(ActionEvent event) {
	
}
	
}
