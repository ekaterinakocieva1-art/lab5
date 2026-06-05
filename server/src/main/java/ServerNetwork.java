import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import common.MessageFramer;
import common.Request;
import common.Response;
import common.SerializationUtils;
import managers.CommandInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerNetwork {
    private static final Logger logger = LoggerFactory.getLogger(ServerNetwork.class);
    private final int port;
    private final CommandInvoker invoker;
    private final BufferedReader serverConsoleReader;
    private ServerSocketChannel ssc;

    public ServerNetwork(int port, CommandInvoker invoker) {
        this.port = port;
        this.invoker = invoker;
        this.serverConsoleReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() {
        try {
            Selector selector = Selector.open();
            this.ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(port));
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            logger.info("Сервер успешно запущен и слушает порт {}", port);
            while (true) {
                handleServerConsole();
                if (selector.select(50) == 0) continue;

                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();

                    if (!key.isValid()) continue;

                    if (key.isAcceptable()) {//Серверный сокет
                        accept(ssc, selector);
                    } else if (key.isReadable()) {
                        read((SocketChannel) key.channel(), key);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Критическая ошибка в работе сервера: ", e);
        }
    }

    private void handleServerConsole() throws IOException {
        if (System.in.available() > 0) {
            String line = serverConsoleReader.readLine();
            if (line != null && line.trim().equalsIgnoreCase("save")) {
                logger.info("Получена команда сохранения с консоли сервера.");
                invoker.execute("save");
            } else if (line != null && !line.trim().isEmpty()) {
                logger.warn("Сервер поддерживает только команду 'save' локально.");
            }
        }
    }

    private void accept(ServerSocketChannel ssc, Selector selector) {
        try {
            SocketChannel sc = ssc.accept();
            if (sc != null) {
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ, new ClientSession());
                logger.info("Клиент подключился: {}", sc.getRemoteAddress());
            }
        } catch (IOException e) {
            logger.error("Ошибка при принятии соединения: ", e);
        }
    }

    private void read(SocketChannel channel, SelectionKey key) {
        ClientSession session = (ClientSession) key.attachment();
        try {
            if (session.isReadingHeader()) {
                int bytes = channel.read(session.getHeaderBuffer());
                if (bytes == -1) {
                    disconnect(channel, key);
                    return;
                }
                if (session.getHeaderBuffer().hasRemaining()) {
                    return;
                }

                session.getHeaderBuffer().flip();
                int length = session.getHeaderBuffer().getInt();
                int requestId = session.getHeaderBuffer().getInt();

                session.setCurrentLength(length);
                session.setCurrentRequestId(requestId);
                session.setDataBuffer(ByteBuffer.allocate(length));
                session.setReadingHeader(false);
            }
            int bytes = channel.read(session.getDataBuffer());
            if (bytes == -1) {
                disconnect(channel, key);
                return;
            }
            if (session.getDataBuffer().hasRemaining()) {
                return;
            }
            byte[] data = session.getDataBuffer().array();
            Request request = (Request) SerializationUtils.deserialize(data);
            request.setRequestId(session.getCurrentRequestId());

            logger.info("Получен запрос [ID: {}]: Команда '{}'", request.getRequestId(), request.getCommandName());
            Response response = invoker.execute(request);
            response.setRequestId(request.getRequestId());
            sendResponse(channel, response);
            session.clear();

        } catch (IOException | ClassNotFoundException e) {
            logger.error("Ошибка при обработке данных от клиента: {}", e.getMessage());
            disconnect(channel, key);
        }
    }

    private void sendResponse(SocketChannel channel, Response response) throws IOException {
        byte[] data = SerializationUtils.serialize(response);
        ByteBuffer responseBuffer = MessageFramer.frameMessage(data, response.getRequestId());
        while (responseBuffer.hasRemaining()) {
            channel.write(responseBuffer);
        }
    }

    private void disconnect(SocketChannel channel, SelectionKey key) {
        try {
            logger.info("Клиент отключился.");
            key.cancel();
            channel.close();
        } catch (IOException e) {
            logger.error("Ошибка при закрытии канала клиента: ", e);
        }
    }
    public void stop(){
        try{
            if(ssc != null && ssc.isOpen()){
                logger.info("Закрытие серверного сокета");
                ssc.close();
            }
        }catch (IOException e){
            logger.error("Ошибка при закрытии серверного канала: ", e);
        }
    }
}
