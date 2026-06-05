package common;

import java.io.Serializable;

public class Request implements Serializable {
    private final String commandName;
    private final Object payload;
    private int requestId;
    private String clientRole;

    public Request(String commandName, Object payload) {
        this.commandName = commandName;
        this.payload = payload;
    }


    public String getCommandName() {
        return commandName;
    }

    public Object getPayload() {
        return payload;
    }
    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }

    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                ", payload=" + payload +
                '}';
    }

    public String getClientRole() {
        return clientRole;
    }

    public void setClientRole(String clientRole) {
        this.clientRole = clientRole;
    }
}
