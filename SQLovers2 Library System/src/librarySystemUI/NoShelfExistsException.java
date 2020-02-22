package librarySystemUI;

public class NoShelfExistsException extends Exception{
	public NoShelfExistsException() {}
	
	@Override
	public String getMessage(){
		return "No such shelf exists.";
	}
}
