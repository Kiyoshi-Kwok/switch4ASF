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
    private JButton check;
    private JPanel switches;
    private JScrollPane infoContainer;
    private JTextArea info;
    private JLabel label;

    public GUI(Operate operate) {
        container = getContentPane();
        switches = new JPanel(new FlowLayout());
        on = new JButton("On");
        off = new JButton("Off");
        check = new JButton("Check");
        label = new JLabel("Click check first.", SwingConstants.CENTER);

        setLocationRelativeTo(null);
        info = new JTextArea(9,32);
        infoContainer = new JScrollPane(info);

        container.setLayout(new BorderLayout());
        info.setEditable(false);
        switches.add(on);
        switches.add(check);
        switches.add(off);
        container.add(label, BorderLayout.NORTH);
        container.add(switches, BorderLayout.CENTER);
        container.add(infoContainer, BorderLayout.SOUTH);

        setResizable(false);
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

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append(operate.check());
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
