package life.kiyoshi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Kiyoshi
 * A GUI to run shell.
 */
public class GUI extends JFrame {
    private Container container;
    private JButton on;
    private JButton off;
    private JPanel switches;
    private JScrollPane infoContainer;
    private JTextArea info;

    public GUI(Operate operate) {
        container = getContentPane();
        switches = new JPanel(new FlowLayout());
        on = new JButton("ON");
        off = new JButton("OFF");
        info = new JTextArea(8,30);
        infoContainer = new JScrollPane(info);

        container.setLayout(new BorderLayout());
        switches.add(on);
        switches.add(off);
        container.add(switches, BorderLayout.NORTH);
        container.add(infoContainer, BorderLayout.SOUTH);

        setTitle("ASF Switch");
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {;
                append(operate.on());
            }
        });

        off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append(operate.off());
            }
        });
    }

    public void append(String line) {
        Date d = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        info.append(sdf.format(d));
        info.append("   ");
        info.append(line);
        info.append("\n");
    }
}
