import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Glebushka on 07.10.2015.
 */
public class UpperServer {
    public static void main(String...args) throws IOException
    {
        try {
            ServerSocket sckt = new ServerSocket(5000);
            while(true) {
                Socket cli = sckt.accept();
                System.out.println("Новый " + cli.getInetAddress().toString() + ":" + cli.getPort());
                new UpperServerThread(cli).start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
