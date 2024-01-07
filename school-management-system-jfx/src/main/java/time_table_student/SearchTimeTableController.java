package time_table_student;

import java.io.IOException;
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
import org.dnyanyog.entity.Notice;
import org.dnyanyog.entity.TimeTable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.ApiEndPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import login.Login;
import student_dashboard.StudentDashBoard;
import time_table_dashboard.TimeTableDashBoard;

public class SearchTimeTableController {

	@FXML
	private TableView<TimeTable> timetableview;

	@FXML
	private TableColumn<TimeTable, Long> idColumn;

	@FXML
	private TableColumn<TimeTable, String> timeslotColumn;

	@FXML
	private TableColumn<TimeTable, String> monDayColumn;

	@FXML
	private TableColumn<TimeTable, String> tuesDayColumn;

	@FXML
	private TableColumn<TimeTable, String> wednesDayColumn;

	@FXML
	private TableColumn<TimeTable, String> thursDayColumn;
	
	@FXML	
	private TableColumn<TimeTable, String> fridayDayColumn;
	
	@FXML
	private TableColumn<TimeTable, String> saturDayColumn;
	
	@FXML
	private ComboBox classnumber;
	
	@FXML
	private Button search;

	@FXML
	private Button back;

	@FXML
	private Button logOut;

	@FXML
	private Button timeTable;
	
	private static final Logger LOGGER = Logger.getLogger(SearchTimeTableController.class.getName());

	public void initialize() {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		timeslotColumn.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
		monDayColumn.setCellValueFactory(new PropertyValueFactory<>("monDay"));
		tuesDayColumn.setCellValueFactory(new PropertyValueFactory<>("tuesDay"));
		wednesDayColumn.setCellValueFactory(new PropertyValueFactory<>("wednesDay"));
		thursDayColumn.setCellValueFactory(new PropertyValueFactory<>("thursDay"));
		fridayDayColumn.setCellValueFactory(new PropertyValueFactory<>("friDay"));
		saturDayColumn.setCellValueFactory(new PropertyValueFactory<>("saturDay"));

		
	}

	private void fetchDataAndPopulateTableView() {

		Object selectclass = classnumber.getValue();
		if (selectclass == null) {
			LOGGER.log(Level.WARNING, "Please select a Status.");
			return;
		}

		String classnum = selectclass.toString();

		String apiUrl = ApiEndPoint.SEARCHTIMETABLE+ classnum;

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
				List<TimeTable> timeTableApplications = parseJsonResponse(response.toString());
				timetableview.getItems().setAll(timeTableApplications);
			} else {
				LOGGER.log(Level.WARNING, "Failed to fetch data from the backend API");
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while fetching data from the backend API", e);
		}
	}

	private List<TimeTable> parseJsonResponse(String jsonResponse) {
	    List<TimeTable> timeTableApplications = new ArrayList<>();
	    try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode root = objectMapper.readTree(jsonResponse);

	        if (root.isArray()) {
	            for (JsonNode node : root) {
	                long id = node.get("id").asLong();
	                String timeSlot = node.get("timeSlot").asText();
	                String monday = node.get("monDay").asText();
	                String tuesday = node.get("tuesDay").asText();
	                String wednesday = node.get("wednesDay").asText();
	                String thursday = node.get("thursDay").asText();
	                String friday = node.get("friDay").asText();
	                String saturday = node.get("saturDay").asText();

	                TimeTable timeTable = new TimeTable();
	                timeTable.setId(id);
	                timeTable.setTimeSlot(timeSlot);
	                timeTable.setMonDay(monday);
	                timeTable.setTuesDay(tuesday);
	                timeTable.setWednesDay(wednesday);
	                timeTable.setThursDay(thursday);
	                timeTable.setFriDay(friday);
	                timeTable.setSaturDay(saturday);
	                

	                timeTableApplications.add(timeTable);
	            }
	        }
	    } catch (Exception e) {
	        LOGGER.log(Level.SEVERE, "An error occurred while parsing JSON response", e);
	    }

	    return timeTableApplications;
	}

	
	
	

	@FXML
	private void search() {
		fetchDataAndPopulateTableView();
	}
	public void back(ActionEvent event) {
		new StudentDashBoard().show();
		
	}
public void logOut(ActionEvent event) {
	new Login().show();
		
	}
public void timeTable(ActionEvent event) {
	
}

}
