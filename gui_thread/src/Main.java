import javax.swing.*;

/**
 * Created by Glebushka on 10.10.2015.
 */
public class Main {
    public static void main(String...args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gogogo();
            }
        });
    }

    private static void gogogo() {
        JFrame f = new JFrame("Main");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        F1 f1 = new F1();
        f1.setOpaque(true);
        f.setContentPane(f1);

        f.pack();
        f.setVisible(true);

    }
}
