package exeptions;

public class ScriptStopException extends RuntimeException{
    public ScriptStopException(String message) {
        super(message);
    }
}
