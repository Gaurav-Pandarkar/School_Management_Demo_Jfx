package data_model;

public class DataModelUser {
	private String mobileNo;
	private String userRole;
	private String email;
	public static String userInfo[]=new String[3];
	public String getMobileNo() {
		
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		userInfo[0]=mobileNo;
		this.mobileNo = mobileNo;
	}
	public String getUserRole() {
		return userRole;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		userInfo[2]=email;
		this.email = email;
	}
	public static String[] getUserInfo() {
		return userInfo;
	}
	public static void setUserInfo(String[] userInfo) {
		DataModelUser.userInfo = userInfo;
	}
	public void setUserRole(String userRole) {
		userInfo[1]=userRole;
		this.userRole = userRole;
	}
	

}
