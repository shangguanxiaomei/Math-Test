package math;

import javax.swing.*;
import java.awt.*;

import static math.CST.*;

public class InformationPanel extends JPanel {

    private JLabel mark, rightNum, wrongNum, time, score;
    private JPanel p1;
    private int r = 0;
    private int w = 0;
    private final Font QUESTION_FONT = new Font("Italic",Font.PLAIN,90);
    private final Font TOTAL_FONT = new Font("Italic",Font.PLAIN,60);


    public InformationPanel(){
        time = new JLabel("", JLabel.CENTER);
        time.setFont(TOTAL_FONT);
        score = new JLabel("0", JLabel.CENTER);
        score.setFont(TOTAL_FONT);
        mark = new JLabel("", JLabel.CENTER);
        mark.setFont(QUESTION_FONT);
        rightNum = new JLabel("total correct: " + r , JLabel.CENTER);
        rightNum.setFont(TOTAL_FONT);
        wrongNum = new JLabel("total Wrong: " + w , JLabel.CENTER);
        wrongNum.setFont(TOTAL_FONT);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(5,1));
        p1.setPreferredSize(new Dimension(450, 850));
        p1.add(mark);
        p1.add(time);
        p1.add(score);
        p1.add(rightNum);
        p1.add(wrongNum);
        add(p1);
    }

    public void setTimer(String s){
        time.setText("Time:  " + s);
    }

    public void setScore(String s){
        score.setText("Score:   " + s);
    }

    public void setMark(boolean isCorrect){
        if(isCorrect){
            mark.setText("Correct");
            mark.setForeground(Color.GREEN);
            r++;
            rightNum.setText("total correct: " + r );
        }
        else {
            mark.setText("Wrong");
            mark.setForeground(Color.RED);
            w++;
            wrongNum.setText("total Wrong: " + w );
        }
    }

    public void resetMark(){
        mark.setText("");
        r = 0;
        w = 0;
        rightNum.setText("total correct: " + r );
        wrongNum.setText("total Wrong: " + w );
    }
}
