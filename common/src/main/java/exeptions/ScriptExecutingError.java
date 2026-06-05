package exeptions;

public class ScriptExecutingError extends RuntimeException {
    public ScriptExecutingError(String message) {
        super(message);
    }
}
