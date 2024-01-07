package Attendance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;

import common.ApiEndPoint;
import common.HttpUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import principle.Principle;
import registration1.Registration;
import com.fasterxml.jackson.databind.ObjectMapper;




public class AttendanceController implements Initializable{
	@FXML
    private TableView<AttendancePerson> tableView;
    @FXML
    private TableColumn<AttendancePerson, String> nameColumn;
    @FXML
    private TableColumn<AttendancePerson, String> attendanceColumn;
    @FXML
    private TableColumn<AttendancePerson, String> rollNoColumn;
    //TableView<Person> tableView1 = new TableView<>();
   
	private ObservableList<AttendancePerson> data;


    //public void initialize() {
//        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
//        attendanceColumn.setCellValueFactory(data -> data.getValue().attendanceProperty());
//        
//        tableView.getItems().addAll(
//                new Person("John"),
//                new Person("Jane"),
//                new Person("Alice")
//        );
//} 
    @Override
	public void initialize(URL url, ResourceBundle rb) {
		
   	rollNoColumn.setCellValueFactory(new PropertyValueFactory<AttendancePerson, String>("rollNo"));
  	nameColumn.setCellValueFactory(new PropertyValueFactory<AttendancePerson, String>("name"));
 	attendanceColumn.setCellValueFactory(new PropertyValueFactory<AttendancePerson, String>("attendance"));
	
	buildData();

	}
    public void buildData() {
        try {
            String url = "http://localhost:8080/api/persons";

            HttpURLConnection getConnection = HttpUtil.getRequest(url);
            int respCode = getConnection.getResponseCode();
            System.out.println("Response from the server is:\n");
            System.out.println("The GET Request Response Code: " + respCode);
            System.out.println("The GET Request Response Message: " + getConnection.getResponseMessage());
         /*   if (respCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(getConnection.getInputStream());
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Validate the JSON response
                if (response.length() > 0 && response.charAt(0) == '{') {
                    // Process the response data as needed
                    System.out.println(response.toString());

                    // Example: Convert JSON response to Person array
                    ObjectMapper objectMapper = new ObjectMapper();
                    AttendancePerson[] persons = objectMapper.readValue(response.toString(), AttendancePerson[].class);
                    
                    // Populate TableView with the retrieved data
                    tableView.getItems().addAll(Arrays.asList(persons));
                } else {
                    // Handle invalid JSON response
                }
            } else {
                // Handle error response
            
        }*/


            if (respCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(getConnection.getInputStream());
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Process the response data as needed
               System.out.println(response.toString());
               ObjectMapper objectMapper = new ObjectMapper();

               try {
                   // Parse the JSON response into an object
                   PersonNew[] persons = objectMapper.readValue(response.toString(), PersonNew[].class);
                		 

                   // Process the parsed data
                   for (PersonNew person : persons) {
                       // Access the properties of the Person object
                       String name = person.getName();
                       String attendance = person.getAttendance();
      System.out.println(attendance);
    data = FXCollections.observableArrayList();
              AttendancePerson at=new AttendancePerson();
            
              at.attendance.set(person.getAttendance());
              at.name.set(name);
              data.addAll(at);
              System.out.println(at);
             
              
              
                       // Perform any further processing or store the data as needed
                   }
                   //tableView.setItems(data);
                   tableView.getItems().addAll(data);
               } catch (IOException e) {
                   e.printStackTrace();
               }

//               name=response.toString();
//               System.out.println(name);
//               String userRoleLoginResponse = parseName("{"+name);
//   			System.out.println("User Role: " + userRoleLoginResponse);

                // Example: Convert JSON response to Person array
//                ObjectMapper objectMapper = new ObjectMapper();
//                AttendancePerson[] persons = objectMapper.readValue(response.toString(), AttendancePerson[].class);
//               System.out.println(persons);
//                // Populate TableView with the retrieved data
//               tableView.getItems().addAll(Arrays.asList(persons));
            } else {
                // Handle error response
            }

            getConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
//    @FXML
//    private void markPresent() {
//        Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
//        if (selectedPerson != null) {
//            selectedPerson.setAttendance("Present");
//        }
//    }
//
//    @FXML
//    private void markAbsent() {
//        Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
//        if (selectedPerson != null) {
//            selectedPerson.setAttendance("Absent");
//        }
//    }
    
      public void back(ActionEvent event) {
    	  new Principle().show();
    	  
      }
 public void next(ActionEvent event) {
    	  
      }
 public void markPresent(ActionEvent event) {
	  
 }
 public void markAbsent(ActionEvent event) {
	  
 }
 private static String parseName(String response) {
		JSONObject jsonObject = new JSONObject(response);
		String name = jsonObject.getJSONObject("data").getString("name");
		return String.valueOf(name);

	}

}
