package life.kiyoshi;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: Kiyoshi
 * @Description: A GUI to run shell.
 */
public class GUI extends JFrame {
    private Container container;
    JButton on;
    JButton off;

    public GUI() {
        container = getContentPane();
        on = new JButton("ON");
        off = new JButton("OFF");
        this.setTitle("ASF Switch");

        container.setLayout(new FlowLayout());
        container.add(on);
        container.add(off);

        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
