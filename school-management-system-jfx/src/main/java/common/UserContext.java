package common;


public class UserContext {
	private static String userId;


	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		UserContext.userId = userId;
	}
	

	public static void UserId() {
		System.out.println(UserContext.getUserId());
	}
	

}
