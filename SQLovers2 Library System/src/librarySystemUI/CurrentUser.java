package librarySystemUI;

public class CurrentUser {
	private String username;
	private String password;
	
	public CurrentUser() {}
	
	public void setUsername(String username, String password){
		this.username = username;
		this.password = password;
	}
	public String getUsername(){
		return username;
	}
}
