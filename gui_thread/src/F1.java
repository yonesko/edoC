import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Glebushka on 10.10.2015.
 */
public class F1 extends JPanel
        implements ActionListener {
    private JButton b1;

    int i = 0;
    String[] txts = {
            "Да ты псих",
            "Нажми мне",
            "Пшол вон"
    };

    F1() {
        b1 = new JButton(txts[i]);
        b1.addActionListener(this);
        b1.setActionCommand("F2");
        add(b1);
    }

    int next() {
        return i == txts.length - 1 ?  i = 0 : ++i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("F2".equals(e.getActionCommand()))
            F2.getF2().run();
    }
}
