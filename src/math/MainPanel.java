package math;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private QuestionPanel questionPanel;
    private JPanel framePanel;
    public static final Color backgroundcolor = new Color(176,218,255);

    public MainPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        framePanel = new JPanel();
        questionPanel = new QuestionPanel();
        framePanel.add(questionPanel);
        framePanel.setBackground(backgroundcolor);
        add(framePanel);
        setFocusable(true);

    }
}
