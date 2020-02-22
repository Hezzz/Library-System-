package librarySystemUI;

public class NoInputException extends Exception{
	
	public NoInputException(){}
	
	@Override
	public String getMessage(){
		return "Some fields have no input. Please input all required fields.";
	}
}
