package librarySystemUI;

public class CurrentUser {
	private static String username;
	private static String password;
	
	public CurrentUser() {}
	
	public static void setUsername(String username, String password){
		CurrentUser.username = username;
		CurrentUser.password = password;
	}
	public static String getUsername(){
		return username;
	}
}
