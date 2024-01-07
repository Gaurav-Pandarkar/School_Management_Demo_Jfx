package common;

public class ApiEndPoint {
	private final static String URL="http://localhost:8080";
	public final static String SIGNUP_STUDENT=URL +"/api/school/signup";
	public final static String LOGIN=URL +"/api/school/loginUser";
	public final static String SIGNUP_USER=URL +"/api/school/signupUser";
	public final static String ATTENDANCE=URL +"/api/persons";
	public final static String TIMETABLEENTRY=URL+"/Timetable/api/add";
	public final static  String NOTICEENTRY=URL+"/Notice/api/create"	;	
	public final static  String FEESENTRY=URL+"/Fees/api/add";
	public final static String DELETEFEES=URL+"/Fees/api/delete/";
	public final static String LEAVEREQUEST=URL+"/Leave/api/request";
	public final static String SEARCHFEES=URL+"/fees/api/";
	public final static String SEARCHNOTICE=URL+"/Notice/api/Notices/";
	public final static String UPDATEFEES=URL+"/Fees/api/update/";
	public final static String DELETENOTICE=URL+"/Notice/api/noticeById/";
	public final static String UPDATENOTICE=URL+"/Notice/api/update/";
	public final static String UPDATELEAVE=URL+"/Leave/api/update/";
	public final static String DELETELEAVE=URL+"/Leave/api/Delete/";
	public final static String EXAMSCHEDULE=URL+"/Exam/api/Add";
	public final static String EMAILTOUSERONLOGIN=URL+"/sendemail";
	public final static String OTPFORFORGOTPASSWORD=URL+"/sendotp";
	public final static String EMAILEXISTORNOT=URL+"/User/api/";
	public final static String NEWPASSWORD=URL+"/updatePassword";
	public final static String DELETETIMETABLE=URL+"/Timetable/Api/delete/";
	public final static String SEARCHTIMETABLE = URL + "/Timetable/api/";
	public final static String UPDATETIMETABLE = URL + "/Timetable/Api/update/";
	public final static String DELETESCHEDULE = URL + "/Exam/api/delete/";
	public final static String SEARCHEXAMSCHEDULE = URL + "/Exam/api/Find/";
	public final static String DIARYINFO = URL + "/Diary/Api/Add";
	public final static String DIARYINFOSEARCH = URL + "/Diary/Api/Find/";
	public final static String DELETEDIARY = URL + "/Diary/Api/Delete/";
	public final static String UPDATEDIARY = URL + "/Diary/Api/Update/";
    public final static String UPDATEEXAMSCHEDULE=URL+"/Exam/api/update/";
	public final static String UPDATELEAVESTATUS=URL+"/Leave/api/v1/";
	public final static String SEARCHLEAVEBYSTATUS = URL + "/Leave/api/show/";
	public final static String SEARCHLEAVESTUD=URL+"/Leave/api/v1/request/";
	public final static String ADDTEACHER=URL+"/Teacher/api/add";



	

}
