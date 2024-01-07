package data_model;


public class DataModel {
	private String studentFirstName;
	private String fatherFirstName;
	private String motherFirstName;
	private String lastName;
	private String mobileNo;
	private String address;
	
	public static String[] info = new String[6]; 

	public String getStudentFirstName() {
		return studentFirstName;
		
	}

	public void setStudentFirstName(String studentFirstName) {
		info[0] = studentFirstName;
		this.studentFirstName = studentFirstName;
	}

	public String getFatherFirstName() {
		return fatherFirstName;
	}

	public void setFatherFirstName(String fatherFirstName) {
		info[1] = fatherFirstName;
		this.fatherFirstName = fatherFirstName;
	}

	public String getMotherFirstName() {
		return motherFirstName;
	}

	public void setMotherFirstName(String motherFirstName) {
		info[2] = motherFirstName;
		this.motherFirstName = motherFirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		info[3] = lastName;
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		info[4] = mobileNo;
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		info[5] = address;
		this.address = address;
	}
	
	
	
	
}
