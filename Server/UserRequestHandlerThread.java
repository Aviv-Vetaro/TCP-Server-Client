import java.io.*;
import java.net.Socket;
import java.util.stream.Collectors;

public class UserRequestHandlerThread extends Thread
{
    private Socket userSocket;
    private final static int bufferSize = 256;
    public UserRequestHandlerThread(Socket userSocket)
    {
        this.userSocket = userSocket;
    }
    @Override
    public void run() {
        //read user request
        ForexRate forexRate = new ForexRate();
        InputStream input = null;
        try {
            input = userSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String requestString = null;
        //read buffered client request string
        char[] buffer = new char[bufferSize];
        try {
            reader.read(buffer);
            requestString = new String(buffer);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        //convert it to request object
        Request request = Request.fromString(requestString);
        //consult the exchange rate entries
        double exchangeResult = forexRate.rate(request.getFrom(), request.getTo()) * request.getAmount();
        OutputStream outputStream = null;
        try {
            outputStream = userSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        ///send result
        PrintWriter streamWriter = new PrintWriter(outputStream, true);
        streamWriter.write(exchangeResult + "");
        streamWriter.flush();
        try {
            userSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
