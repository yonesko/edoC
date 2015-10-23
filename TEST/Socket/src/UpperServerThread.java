import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Glebushka on 06.10.2015.
 */


public class UpperServerThread extends Thread {

    Socket cli;

    UpperServerThread(Socket cli) {
        this.cli = cli;
    }

    public void run() {
        try {
            PrintWriter toCli =
                    new PrintWriter(cli.getOutputStream(), true);

            BufferedReader fromCli = new BufferedReader(
                    new InputStreamReader(cli.getInputStream()));

            String inLine;

            while ((inLine = fromCli.readLine()) != null) {
                System.out.println("cli: " + inLine);
                toCli.println("Сам ты" + inLine.toUpperCase());
            }
        }
        catch (ConnectException e)
        {
            System.out.println("Кто-то ушел");
        } catch (IOException e) {
            System.out.println("Кто-то ушел");
        }
        finally {
            System.out.println("Ушел " + cli.getInetAddress().toString() + ":" + cli.getPort());
        }
    }
}
