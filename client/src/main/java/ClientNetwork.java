import common.Request;
import common.Response;
import common.SerializationUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientNetwork {
    private final String host;
    private final int port;
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private static int IDсounter = 1;

    public ClientNetwork(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException {
        this.socket = new Socket(host, port);
        this.outputStream = new DataOutputStream(socket.getOutputStream());
        this.inputStream = new DataInputStream(socket.getInputStream());
    }

    public void sendRequest(Request request) throws IOException {
        int id = IDсounter++;
        request.setRequestId(id);

        byte[] data = SerializationUtils.serialize(request);
        outputStream.writeInt(data.length);
        outputStream.writeInt(request.getRequestId());
        outputStream.write(data);
        outputStream.flush();
    }

    public Response receiveResponse() throws IOException, ClassNotFoundException {
        int length = inputStream.readInt();
        int responseId = inputStream.readInt();

        byte[] data = new byte[length];
        inputStream.readFully(data);

        Response response = (Response) SerializationUtils.deserialize(data);
        response.setRequestId(responseId);
        return response;
    }

    public void disconnect() {
        try {
            if (outputStream != null) outputStream.close();
            if (inputStream != null) inputStream.close();
            if (socket != null) socket.close();
        } catch (IOException ignored) {}
    }
}
