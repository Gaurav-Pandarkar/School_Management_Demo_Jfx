package data_model;

public class EmailDataModel {
	private String email;
	
	public static String[] info = new String[1]; 

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		info[0] = email;
		this.email = email;
	}

}
