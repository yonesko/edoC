import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Glebushka on 06.10.2015.
 */
public class Client {
    public static void main(String...args) throws IOException {
        String hostName = args[0];
        String line;
        int portNumber = Integer.parseInt(args[1]);

        Socket kkSocket = new Socket(hostName, portNumber);
        PrintWriter toServ = new PrintWriter(kkSocket.getOutputStream(), true);

        BufferedReader fromServ = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));

        BufferedReader fromUser =
                new BufferedReader(new InputStreamReader(System.in));

        String input;

        while((input=fromUser.readLine()) != null)
        {
//            System.out.println("you: " + input);
            toServ.println(input);
            System.out.println(fromServ.readLine());
            if(input.equals("exit"))
                break;
        }

    }
}
