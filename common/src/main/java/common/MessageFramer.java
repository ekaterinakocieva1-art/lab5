package common;

import java.nio.ByteBuffer;
// добавляет к сообщению служебную информацию
public class MessageFramer {
    public static ByteBuffer frameMessage(byte[] data, int requestId) {
        ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + data.length);
        buffer.putInt(data.length);
        buffer.putInt(requestId);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
