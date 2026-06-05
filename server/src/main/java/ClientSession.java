import java.nio.ByteBuffer;

public class ClientSession {
    private final ByteBuffer headerBuffer = ByteBuffer.allocate(8); // 4 байта под длину, 4 под ID
    private ByteBuffer dataBuffer;
    private boolean readingHeader = true;
    private int currentRequestId = -1;
    private int currentLength = -1;

    public ByteBuffer getHeaderBuffer() { return headerBuffer; }
    public ByteBuffer getDataBuffer() { return dataBuffer; }
    public void setDataBuffer(ByteBuffer dataBuffer) { this.dataBuffer = dataBuffer; }
    public boolean isReadingHeader() { return readingHeader; }
    public void setReadingHeader(boolean readingHeader) { this.readingHeader = readingHeader; }
    public int getCurrentRequestId() { return currentRequestId; }
    public void setCurrentRequestId(int currentRequestId) { this.currentRequestId = currentRequestId; }
    public int getCurrentLength() { return currentLength; }
    public void setCurrentLength(int currentLength) { this.currentLength = currentLength; }

    public void clear() {
        headerBuffer.clear();
        dataBuffer = null;
        readingHeader = true;
        currentRequestId = -1;
        currentLength = -1;
    }
}
