import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Wife w = new Wife();
        w.start();
        new Husb(w).start();
    }
}

class Wife extends Thread {
    public void run() {
        try {
            System.out.print("Жена: Собираюсь");
            for (int i = 0; i < 10; i++) {
                System.out.print(".");
                TimeUnit.MILLISECONDS.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println("Прервал Жену!!" + isInterrupted());
        } finally {
            System.out.println("Я все!");
        }
    }
}

class Husb extends Thread {
    private Thread waitFor;

    Husb (Thread thread) {
        waitFor = thread;
    }
    public void run() {
        try {
//            System.out.println("Муж: Поехали в АШАН?");
            waitFor.join();
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.printf("Муж: Наконец-то!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}