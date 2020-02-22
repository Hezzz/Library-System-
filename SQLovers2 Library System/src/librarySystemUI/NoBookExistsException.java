package librarySystemUI;

public class NoBookExistsException extends Exception{
	public NoBookExistsException() {}
	@Override
	public String getMessage() {
		return "No such book exists.";
	}
}
