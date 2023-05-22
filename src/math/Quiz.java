package math;

import javax.swing.*;

public class Quiz {
    static JFrame frame = new JFrame("Quiz");

    public static void main (String[] args) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(300, 500);

        MainPanel main = new MainPanel();
        frame.getContentPane().add(main);

        frame.pack();
        frame.setVisible(true);

    }
}
