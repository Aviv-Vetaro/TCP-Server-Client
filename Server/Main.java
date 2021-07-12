import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    final static int port = 8888;
    public static void main(String[] args)
    {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Listening @" + port + " for requests:");
            while (true)
            {
                //start a thread for each individual request (enables multithreading)
                Socket socket = serverSocket.accept();
                System.out.println("Established connection");
                UserRequestHandlerThread userRequestHandlerThread = new UserRequestHandlerThread(socket);
                userRequestHandlerThread.start();
            }
        } catch (java.io.IOException e)
        {
            System.out.println("connection failed");
        }
    }
}
