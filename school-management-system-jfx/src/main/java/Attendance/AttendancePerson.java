package Attendance;

import javafx.beans.property.SimpleStringProperty;

public class AttendancePerson {
	public SimpleStringProperty rollNo=new SimpleStringProperty();
	public SimpleStringProperty name=new SimpleStringProperty();

	public SimpleStringProperty attendance=new SimpleStringProperty();

	public SimpleStringProperty getRollNo() {
		return rollNo;
	}

	public void setRollNo(SimpleStringProperty rollNo) {
		this.rollNo = rollNo;
	}

	public SimpleStringProperty getName() {
		return name;
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public SimpleStringProperty getAttendance() {
		return attendance;
	}

	public void setAttendance(SimpleStringProperty attendance) {
		this.attendance = attendance;
	}

	
}
