import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Glebushka on 10.10.2015.
 */
public class F2 implements Runnable {
    JFrame f2;
    JLabel l;

    private static F2 dis;

    public static F2 getF2() {
        if (dis == null) {
            dis = new F2();
        }
        return dis;
    }

    private F2() {

    }

    public void run() {
        f2 = new JFrame("Второй");
        f2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        l = new JLabel("World", SwingConstants.CENTER);

        f2.getContentPane().add(l);

        f2.pack();
        f2.setVisible(true);
    }

    private void actionPerformed(ActionEvent evt) {

    }
}
