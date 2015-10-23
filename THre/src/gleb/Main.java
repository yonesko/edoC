package gleb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator(), 2);
        System.out.printf("End of Main");
    }
}

