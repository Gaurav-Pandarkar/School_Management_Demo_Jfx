package Attendance;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	 private  StringProperty name;
	    private  StringProperty attendance;

	    public Person(String name) {
	        this.name = new SimpleStringProperty(name);
	        this.attendance = new SimpleStringProperty("Absent");
	    }

	    public String getName() {
	        return name.get();
	    }

	    public StringProperty nameProperty() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name.set(name);
	    }

	    public String getAttendance() {
	        return attendance.get();
	    }

	    public StringProperty attendanceProperty() {
	        return attendance;
	    }

	    public void setAttendance(String attendance) {
	        this.attendance.set(attendance);
	    }

}
