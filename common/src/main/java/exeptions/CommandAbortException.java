package exeptions;

public class CommandAbortException extends RuntimeException{
    public CommandAbortException(String message){
        super(message);
    }
}
