package math;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.*;
import static math.CST.*;
import static math.Fraction.*;

public class QuestionPanel extends JPanel {

    private JLabel question;
    private JPanel p1, p2, p3, rightPanel, functionPanel;
    private InformationPanel informationPanel;
    private JButton answer1, answer2, answer3, answer4, function, restart;
    private ButtonListener buttonListener;
    private int rightAnswer,score;
    private Boolean started, stopAnswerListener;
    private Timer timer;
    private final int totalTime = CST.TIME;
    private int currentTime;
    Random random = new Random();

    private int scorePenalty = CST.ADD_PENALTY;
    private QuestionType qType = QuestionType.ADDITION;


    private final Font QUESTION_FONT = new Font("Italic",Font.PLAIN,QUESTION_FONT_SIZE);
    private final Font ANSWER_FONT = new Font("Italic",Font.BOLD,ANSWER_FONT_SIZE);
    private final Font FUNCTION_FONT = new Font("Italic",Font.BOLD,FUNCTION_FONT_SIZE);
    private final Color BACKGROUND = new Color(192,192,192);

    public QuestionPanel() {
        game();
    }

    private void game(){

        buttonListener = new ButtonListener();
        question = new JLabel();
        question.setFont(QUESTION_FONT);

        p1 = new JPanel();
        p1.setPreferredSize(new Dimension(900, 900));
        p1.add(question);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(2,2));

        answer1 = new JButton();
        answer1.setFont(ANSWER_FONT);
        answer1.addActionListener(buttonListener);
        p2.add(answer1);
        answer2 = new JButton();
        answer2.setFont(ANSWER_FONT);
        answer2.addActionListener(buttonListener);
        p2.add(answer2);
        answer3 = new JButton();
        answer3.setFont(ANSWER_FONT);
        answer3.addActionListener(buttonListener);
        p2.add(answer3);
        answer4 = new JButton();
        answer4.setFont(ANSWER_FONT);
        answer4.addActionListener(buttonListener);
        p2.add(answer4);

        p3 =new JPanel();
        p3.setPreferredSize(new Dimension(1800, 900));
        p3.setLayout(new GridLayout(2,1));
        p3.add(p1);
        p3.add(p2);
        add(p3);

        functionPanel = new JPanel();
        functionPanel.setPreferredSize(new Dimension(450, 50));
        functionPanel.setLayout(new GridLayout(1,2));
        functionPanel.setBackground(Color.WHITE);
        function = new JButton();
        restart = new JButton();
        function.addActionListener(buttonListener);
        restart.addActionListener(buttonListener);
        function.setFont(FUNCTION_FONT);
        restart.setFont(FUNCTION_FONT);
        function.setText("+/-");
        restart.setText("Restart");
        functionPanel.add(function);
        functionPanel.add(restart);
        informationPanel = new InformationPanel();
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(450, 900));
        rightPanel.add(functionPanel);
        rightPanel.add(informationPanel);
        add(rightPanel);

        restart();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(started) informationPanel.setTimer(String.valueOf(currentTime--));
                if(currentTime < 0) {
                    started = false;
                    stopAnswerListener = true;
//                    timer.cancel();
                }
            }
        },0, CST.TIMER_PERIOD);
    }

    private void newQuestion(){
        if (qType == QuestionType.ADDITION) {
            if (random.nextBoolean())add();
            else minus();
        }
        if (qType == QuestionType.MULTIPLICATION){
            if (random.nextBoolean())multiple();
            else division();
        }
        if (qType == QuestionType.FRACTION){
            fractionAdd();
        }
    }

    private void add (){
        int sum = random.nextInt(CST.ADD_LIMIT - 200) + 201;
        int num1 = random.nextInt(sum - 99) + 100;
        int num2 = sum - num1;
        Set<Integer> set=new HashSet();
        set.add(sum);
        while (set.size() < 4){
            int i;
            if (sum <= 10)i = sum + 2 + random.nextInt(6);
            else {
                if (random.nextBoolean()) i = sum + 200 * random.nextInt(2) - 100;
                else if (random.nextBoolean()) i = sum + 20 * random.nextInt(2) - 10;
                else i = sum - 2 + random.nextInt(5);
            }
            if (i >= 0 && i <= CST.ADD_LIMIT)set.add(i);
        }
        ArrayList<Integer> list = new ArrayList(set);
        int a1 = list.get(random.nextInt(4));
        set.remove(a1);
        list = new ArrayList(set);
        int a2 = list.get(random.nextInt(3));
        set.remove(a2);
        list = new ArrayList(set);
        int a3 = list.get(random.nextInt(2));
        set.remove(a3);
        list = new ArrayList(set);
        int a4 = list.get(0);
        if (sum == a1)rightAnswer = 1;
        if (sum == a2)rightAnswer = 2;
        if (sum == a3)rightAnswer = 3;
        if (sum == a4)rightAnswer = 4;

        String questionString = num1 + " + " + num2 + " = ?";
        question.setText(questionString);
        answer1.setText(String.valueOf(a1));
        answer2.setText(String.valueOf(a2));
        answer3.setText(String.valueOf(a3));
        answer4.setText(String.valueOf(a4));
    }

    private void minus(){
        int num1 = random.nextInt(CST.MINUS_LIMIT - 200) + 201;
        int num2 = random.nextInt(num1 - 99) + 100;
        int result = num1 - num2;
        Set<Integer> set=new HashSet();
        set.add(result);
        while (set.size() < 4){
            int i;
            if (result >= 100 && random.nextBoolean()) i = result + 200 * random.nextInt(2) - 100;
            else if (result >= 10 && random.nextBoolean()) i = result + 20 * random.nextInt(2) - 10;
            else i = result + 2 + random.nextInt(6);
            if (i >= 0)set.add(i);
        }
        ArrayList<Integer> list = new ArrayList(set);
        int a1 = list.get(random.nextInt(4));
        set.remove(a1);
        list = new ArrayList(set);
        int a2 = list.get(random.nextInt(3));
        set.remove(a2);
        list = new ArrayList(set);
        int a3 = list.get(random.nextInt(2));
        set.remove(a3);
        list = new ArrayList(set);
        int a4 = list.get(0);
        if (result == a1)rightAnswer = 1;
        if (result == a2)rightAnswer = 2;
        if (result == a3)rightAnswer = 3;
        if (result == a4)rightAnswer = 4;

        String questionString = num1 + " - " + num2 + " = ?";
        question.setText(questionString);
        answer1.setText(String.valueOf(a1));
        answer2.setText(String.valueOf(a2));
        answer3.setText(String.valueOf(a3));
        answer4.setText(String.valueOf(a4));
    }

    private void multiple(){
        scorePenalty = CST.MULTIPLE_PENALTY;
        int num1 = random.nextInt(CST.MULTIPLE_LIMIT) + 1;
        int num2 = random.nextInt(CST.MULTIPLE_LIMIT) + 1;
        int product = num1 * num2;
        Set<Integer> set=new HashSet();
        set.add(product);
        while (set.size() < 4){
            int i =0;
            if(random.nextBoolean())i = product - 10 + random.nextInt(21);
            else {
                if(random.nextBoolean()) i = (num1 - 1 + random.nextInt(2)) * num2;
                else i = (num2 - 1 + random.nextInt(2)) * num1;
            }
            if (i >= 0)set.add(i);
        }
        ArrayList<Integer> list = new ArrayList(set);
        int a1 = list.get(random.nextInt(4));
        set.remove(a1);
        list = new ArrayList(set);
        int a2 = list.get(random.nextInt(3));
        set.remove(a2);
        list = new ArrayList(set);
        int a3 = list.get(random.nextInt(2));
        set.remove(a3);
        list = new ArrayList(set);
        int a4 = list.get(0);
        if (product == a1)rightAnswer = 1;
        if (product == a2)rightAnswer = 2;
        if (product == a3)rightAnswer = 3;
        if (product == a4)rightAnswer = 4;

        String questionString = num1 + " x " + num2 + " = ?";
        question.setText(questionString);
        answer1.setText(String.valueOf(a1));
        answer2.setText(String.valueOf(a2));
        answer3.setText(String.valueOf(a3));
        answer4.setText(String.valueOf(a4));
    }

    private void division(){
        scorePenalty = CST.MULTIPLE_PENALTY;
        int ans = random.nextInt(CST.MULTIPLE_LIMIT) + 1;
        int num = random.nextInt(CST.MULTIPLE_LIMIT) + 1;
        int product = ans * num;
        Set<Integer> set=new HashSet();
        set.add(ans);
        while (set.size() < 4){
            int i =0;
            if(random.nextBoolean())i = ans - 3 + random.nextInt(7);
            if (i > 0)set.add(i);
        }
        ArrayList<Integer> list = new ArrayList(set);
        int a1 = list.get(random.nextInt(4));
        set.remove(a1);
        list = new ArrayList(set);
        int a2 = list.get(random.nextInt(3));
        set.remove(a2);
        list = new ArrayList(set);
        int a3 = list.get(random.nextInt(2));
        set.remove(a3);
        list = new ArrayList(set);
        int a4 = list.get(0);
        if (ans == a1)rightAnswer = 1;
        if (ans == a2)rightAnswer = 2;
        if (ans == a3)rightAnswer = 3;
        if (ans == a4)rightAnswer = 4;

        String questionString = product + " ÷ " + num + " = ?";
        question.setText(questionString);
        answer1.setText(String.valueOf(a1));
        answer2.setText(String.valueOf(a2));
        answer3.setText(String.valueOf(a3));
        answer4.setText(String.valueOf(a4));
    }

    private void fractionAdd (){
        scorePenalty = FRACTION_PENALTY;
        int limit = FRACTION_LIMIT;
        boolean p = PROPER_FRACTION;
        Fraction num1 =  fractionGenerate(limit, p);
        Fraction num2 =  fractionGenerate(limit, p);
        Fraction sum = fAdd(num1, num2);
        while (sum.d > limit
                || (sum.n > sum.d && p)){
            num1 =  fractionGenerate(limit, p);
            num2 =  fractionGenerate(limit, p);
            sum = fAdd(num1, num2);
        }
        HashSet<Fraction> set=new HashSet();
        if (random.nextBoolean())set.add(sum);
        set.add(simplify(sum));
        while (set.size() < 4){
            int n = sum.n;
            int d = sum.d;
            n = n + random.nextInt(7) - 3;
            if (n > 0)set.add(simplify(new Fraction(n, d)));
        }
        ArrayList<Fraction> list = new ArrayList(set);
        Fraction a1 = list.get(random.nextInt(4));
        set.remove(a1);
        list = new ArrayList(set);
        Fraction a2 = list.get(random.nextInt(3));
        set.remove(a2);
        list = new ArrayList(set);
        Fraction a3 = list.get(random.nextInt(2));
        set.remove(a3);
        list = new ArrayList(set);
        Fraction a4 = list.get(0);
        sum = simplify(sum);
        if (sum.equals(a1))rightAnswer = 1;
        if (sum.equals(a2))rightAnswer = 2;
        if (sum.equals(a3))rightAnswer = 3;
        if (sum.equals(a4))rightAnswer = 4;

        String questionString = num1 + " + " + num2 + " = ?";
        question.setText(questionString);
        answer1.setText(a1.toString());
        answer2.setText(a2.toString());
        answer3.setText(a3.toString());
        answer4.setText(a4.toString());
    }


    private class ButtonListener implements ActionListener {

        public void actionPerformed (ActionEvent event) {
            if (event.getSource() == function) {
                if (started == true) return;
                if (qType == QuestionType.ADDITION){
                    qType = QuestionType.MULTIPLICATION;
                    function.setText("×/÷");
                    restart();
                    return;
                }
                if (qType == QuestionType.MULTIPLICATION){
                    qType = QuestionType.FRACTION;
                    function.setText("a/b");
                    restart();
                    return;
                }
                if (qType == QuestionType.FRACTION){
                    qType = QuestionType.ADDITION;
                    function.setText("+/-");
                    restart();
                    return;
                }
            }
            if (event.getSource() == restart)
                {
//                    if (currentTime < totalTime / 2)restart();
                    if (currentTime < 0)restart();
                    return;
                }
            if(stopAnswerListener) return;
            started = true;
            Boolean isCorrect = false;
            if (event.getSource() == answer1 && rightAnswer == 1)isCorrect = true;
            if (event.getSource() == answer2 && rightAnswer == 2)isCorrect = true;
            if (event.getSource() == answer3 && rightAnswer == 3)isCorrect = true;
            if (event.getSource() == answer4 && rightAnswer == 4)isCorrect = true;
            informationPanel.setMark(isCorrect);
            if (isCorrect){
                score++;
                if (qType == QuestionType.ADDITION && score == ADD_GOAL
                || qType == QuestionType.MULTIPLICATION && score == MULTIPLE_GOAL)Music.winMusic();
                else Music.rightMusic();
                newQuestion();
            }
            else {
                score = score - scorePenalty;
                Music.wrongMusic();
            }
            if (score < 0) score = 0;
            informationPanel.setScore(String.valueOf(score));
        }
    }
    
    private void restart(){
        newQuestion();
        score = 0;
        started = false;
        stopAnswerListener = false;
        informationPanel.setScore(String.valueOf(score));
        informationPanel.setTimer(String.valueOf(totalTime));
        informationPanel.resetMark();
        currentTime = totalTime;
    }

    enum QuestionType
    {
        ADDITION, MULTIPLICATION, FRACTION;
    }
}