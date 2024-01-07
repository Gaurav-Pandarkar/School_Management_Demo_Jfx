package data_model;

public class OtpModel {
  private String otp;
  
  public static String[] info = new String[1]; 

public String getOtp() {
	return otp;
}

public void setOtp(String otp) {
	info[0] = otp;
	this.otp = otp;
}

}
