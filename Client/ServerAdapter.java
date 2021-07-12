import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerAdapter
{
    //request and reply length cannot acceded 256 (even the longest) so its a safe amount to assign  to the buffer
    private static final int bufferSize = 256;
    private static final int serverPort = 8888;
    private static final String host = "localhost";
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    //initializes connection
    private void startConnection(String host, int port) throws IOException {
        clientSocket = new Socket(host, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    //send request to server and get response
    private String sendMessage(String msg) throws IOException {
        out.println(msg);
        char[] buffer = new char[bufferSize];
        in.read(buffer);
        return new String(buffer);
    }
    //close the connection
    private void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
    public double sendServerForexRequest(Request clientRequest) throws IOException {
        startConnection(host, serverPort);
        String result = sendMessage(clientRequest.toString());
        stopConnection();
        return Double.parseDouble(result);
    }
}
