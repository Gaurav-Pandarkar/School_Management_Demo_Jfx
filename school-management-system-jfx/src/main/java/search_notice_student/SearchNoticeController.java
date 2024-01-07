package search_notice_student;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dnyanyog.entity.Notice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.ApiEndPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import login.Login;
import notice_dashboard.NoticeDashBoard;
import student_dashboard.StudentDashBoard;



public class SearchNoticeController {
	@FXML
	private TableView<Notice> noticeTableView;

	@FXML
	private TableColumn<Notice, Long> idColumn;

	@FXML
	private TableColumn<Notice, LocalDate> dateColumn;

	@FXML
	private TableColumn<Notice, String> noticeColumn;

	@FXML
	private TableColumn<Notice, String> createdByColumn;

	@FXML
	private DatePicker datePicker;

	@FXML
	private Button search;
	@FXML
	private Button back;
	@FXML
	private Button logOut;
	@FXML
	private Button notification;

	private static final Logger LOGGER = Logger.getLogger(SearchNoticeController.class.getName());

	public void initialize() {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("noticeDate"));
		noticeColumn.setCellValueFactory(new PropertyValueFactory<>("notice"));
		createdByColumn.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
	}

	private void fetchDataAndDisplayNotice() {
		LocalDate selectedDate = datePicker.getValue();
		if (selectedDate == null) {
			LOGGER.log(Level.WARNING, "Please select a date.");
			return;
		}

		String formattedDate = selectedDate.toString();
		String apiUrl = ApiEndPoint.SEARCHNOTICE + formattedDate;

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

				// Parse the JSON response and display the notice
				List<Notice> notices = parseJsonResponse(response.toString());
				displayNotices(notices);
			} else {
				LOGGER.log(Level.WARNING, "Failed to fetch data from the backend API");
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while fetching data from the backend API", e);
		}
	}

	private List<Notice> parseJsonResponse(String jsonResponse) {
		List<Notice> notices = new ArrayList<>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode root = objectMapper.readTree(jsonResponse);

			if (root.isArray()) {
				for (JsonNode node : root) {
					long id = node.get("id").asLong();
					LocalDate noticeDate = LocalDate.parse(node.get("noticeDate").asText());
					String noticeText = node.get("notice").asText();
					String createdBy = node.get("createdBy").asText();

					Notice notice = new Notice();
					notice.setId(id);
					notice.setNoticeDate(noticeDate);
					notice.setNotice(noticeText);
					notice.setCreatedBy(createdBy);
					notices.add(notice);
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "An error occurred while parsing JSON response", e);
		}

		return notices;
	}

	private void displayNotices(List<Notice> notices) {
		noticeTableView.getItems().clear();
		noticeTableView.getItems().addAll(notices);
	}

	@FXML
	private void search() {
		fetchDataAndDisplayNotice();
	}
	
	
public void logOut(ActionEvent event) {
	new Login().show();
		
	}
public void notification(ActionEvent event) {
	
}
public void back(ActionEvent event) {
	new StudentDashBoard().show();
	
}


}
