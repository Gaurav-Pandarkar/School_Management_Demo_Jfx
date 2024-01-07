package data_model;

public class FeesDataModel {
	
	private String grnNo;
	private String studentName;
	private String totalFees;
	private String feesPaid;
	private String remainingFees;
	public static String feesInfo[]=new String[5];

	public String getGrnNo() {
		return grnNo;
	}
	public void setGrnNo(String grnNo) {
		feesInfo[0]=grnNo;
		this.grnNo = grnNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		feesInfo[1]=studentName;
		this.studentName = studentName;
	}
	
	public String getTotalFees() {
		return totalFees;
	}
	public void setTotalFees(String totalFees) {
		feesInfo[2]=totalFees;

		this.totalFees = totalFees;
	}
	public String getFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(String feesPaid) {
		feesInfo[3]=feesPaid;

		this.feesPaid = feesPaid;
	}
	public String getRemainingFees() {
		return remainingFees;
	}
	public void setRemainingFees(String remainingFees) {
		feesInfo[4]=remainingFees;

		this.remainingFees = remainingFees;
	}
	
}
