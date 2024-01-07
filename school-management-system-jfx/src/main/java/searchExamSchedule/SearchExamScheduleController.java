package searchExamSchedule;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dnyanyog.entity.ExamSchedule;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.ApiEndPoint;
import exams_shedule_dashboard.ExamScheduleDashBoard;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import login.Login;

public class SearchExamScheduleController {
	@FXML
	private TableView<ExamSchedule> examTableView;

	@FXML
	private TableColumn<ExamSchedule, Long> idColumn;

	@FXML
	private TableColumn<ExamSchedule, String> subjectName;

	@FXML
	private TableColumn<ExamSchedule, LocalDate> date;

	@FXML
	private TableColumn<ExamSchedule, String> time;

	@FXML
	private ComboBox className;

	@FXML
	private Button show;
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button examShedule;

	private static final Logger LOGGER = Logger.getLogger(SearchExamScheduleController.class.getName());

	public void initialize() {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		subjectName.setCellValueFactory(new PropertyValueFactory<>("subject"));
		date.setCellValueFactory(new PropertyValueFactory<>("examDate"));
		time.setCellValueFactory(new PropertyValueFactory<>("examTime"));
	}

	private void fetchDataAndDisplaySchedule() {

		String selectClass = (String) className.getValue(); // Cast to String, as ComboBox items are of type String
		if (selectClass == null || selectClass.isEmpty()) {
			LOGGER.log(Level.WARNING, "Please enter a class name.");
			return;
		}

		String apiUrl = ApiEndPoint.SEARCHEXAMSCHEDULE + selectClass;

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

				// Parse the JSON response and display the schedule
				List<ExamSchedule> schedule = parseJsonResponse(response.toString());
				displaySchedule(schedule);
			} else {
				LOGGER.log(Level.WARNING, "Failed to fetch data from the backend API");
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while fetching data from the backend API", e);
		}
	}

	private List<ExamSchedule> parseJsonResponse(String jsonResponse) {
		List<ExamSchedule> schedule = new ArrayList<>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode root = objectMapper.readTree(jsonResponse);

			if (root.isArray()) {
				for (JsonNode node : root) {
					long id = node.get("id").asLong();
					String subject = node.get("subject").asText();
					LocalDate examDate = LocalDate.parse(node.get("examDate").asText());
					String examTime = node.get("examTime").asText();

					ExamSchedule exam = new ExamSchedule();
					exam.setId(id);
					exam.setSubject(subject);
					exam.setExamDate(examDate);
					exam.setExamTime(examTime);
					schedule.add(exam);
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "An error occurred while parsing JSON response", e);
		}

		return schedule;
	}

	private void displaySchedule(List<ExamSchedule> schedule) {
		examTableView.getItems().clear();
		examTableView.getItems().addAll(schedule);
	}

	@FXML
	private void search() {
		fetchDataAndDisplaySchedule();
	}
	public void back(ActionEvent event) {
		new ExamScheduleDashBoard().show();
		
	}
public void logOut(ActionEvent event) {
	new Login().show();
		
	}
public void examShedule(ActionEvent event) {
	
}
	
}
