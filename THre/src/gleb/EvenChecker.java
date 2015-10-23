package gleb;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Glebushka on 11.10.2015.
 */
public class EvenChecker implements Runnable {
    private IntGenerator ig;
    private final int id;

    private EvenChecker(IntGenerator ig, int id) {
        this.ig = ig;
        this.id = id;
    }

    @Override
    public void run() {
        int val;
        while(!ig.isCanceled()) {
            if((val = ig.next()) % 2 != 0) {
                System.out.println(val + "не чоток");
                ig.setCanceled(true);
            } else {
                System.out.println(Thread.currentThread().getName() + " : " + val + " чоток");
            }
        }
        System.out.print("End of");
        System.out.println("EvenChecker.run");
    }

    public static void test(IntGenerator ig, int count) {

        for (int i = 0; i < count; i++) {
            new Thread(new EvenChecker(ig, i)).start();
        }
        System.out.print("End of ");
        System.out.println("EvenChecker.test");
    }
}
