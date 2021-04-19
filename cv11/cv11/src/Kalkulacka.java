import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kalkulacka  extends JFrame implements ActionListener {

    static JTextField field;
    static JFrame frame;
    static String answear = "";

    public Kalkulacka(){

    }

    public static void main(String args[]) {
        //change UI to look like system
        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        Kalkulacka calculator = new Kalkulacka();

        frame = new JFrame("Kalkulacka");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        field = new JTextField(13);
        field.setEditable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //3 panels -Text-Numbers-Equals-
        JPanel panel1 = new JPanel(new GridLayout(1,1));
        JPanel panel2 = new JPanel(new GridLayout(4,4));
        JPanel panel3 = new JPanel(new GridLayout(1,1));

        // create number buttons
        JButton num0 = new JButton("0");
        JButton num1 = new JButton("1");
        JButton num2 = new JButton("2");
        JButton num3 = new JButton("3");
        JButton num4 = new JButton("4");
        JButton num5 = new JButton("5");
        JButton num6 = new JButton("6");
        JButton num7 = new JButton("7");
        JButton num8 = new JButton("8");
        JButton num9 = new JButton("9");

        //operations
        JButton buttonEq = new JButton("=");
        JButton buttonPls = new JButton("+");
        JButton buttonMins = new JButton("-");

        //actions
        num0.addActionListener(calculator);
        num1.addActionListener(calculator);
        num2.addActionListener(calculator);
        num3.addActionListener(calculator);
        num4.addActionListener(calculator);
        num5.addActionListener(calculator);
        num6.addActionListener(calculator);
        num7.addActionListener(calculator);
        num8.addActionListener(calculator);
        num9.addActionListener(calculator);
        buttonPls.addActionListener(calculator);
        buttonMins.addActionListener(calculator);
        buttonEq.addActionListener(calculator);

        //text
        panel1.add(field);
        //numbers
        panel2.add(num0);
        panel2.add(num1);
        panel2.add(num2);
        panel2.add(num3);
        panel2.add(num4);
        panel2.add(num5);
        panel2.add(num6);
        panel2.add(num7);
        panel2.add(num8);
        panel2.add(num9);
        //operators
        panel2.add(buttonPls);
        panel2.add(buttonMins);
        //equals
        panel3.add(buttonEq);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        frame.add(mainPanel);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            String calc = e.getActionCommand();
            //if already answered, clear answer and start with new computatin
            if (!answear.isEmpty()){
                answear = "";
                field.setText(answear);
            }
            if (calc.charAt(0) == '=')
            {
                answear = calculate(field.getText());
                field.setText(answear);
            }
            else
            {
                field.setText(field.getText() + " "+calc);
            }

    }
    public static String calculate(String fieldString)
    {
        //get calculator text to chars
        char[] fieldArray = fieldString.toCharArray();

        String num1 = "";
        String num2 = "";
        char operator = ' ';
        
        double tmpAnswear = 0;
        
        for (int i = 0; i < fieldArray.length; i++)
        {
            if (fieldArray[i] >= '0' && fieldArray[i] <= '9')
            {
                if(operator == ' ')
                {
                    num1 += fieldArray[i];
                }
                else
                {
                    num2 += fieldArray[i];
                }
            }
            if(fieldArray[i] == '+' || fieldArray[i] == '-' || fieldArray[i] == '/' || fieldArray[i] == '*')
            {
                operator = fieldArray[i];
            }
        }
        
        switch (operator){
            case '+': tmpAnswear = (Double.parseDouble(num1) + Double.parseDouble(num2)); break;
            case '-': tmpAnswear = (Double.parseDouble(num1) - Double.parseDouble(num2)); break;
            case '*': tmpAnswear = (Double.parseDouble(num1) * Double.parseDouble(num2)); break;
            case '/': tmpAnswear = (Double.parseDouble(num1) / Double.parseDouble(num2)); break;
        }
        return num1 +" "+ operator +" "+ num2 +" "+ "=" +" "+ tmpAnswear;
    }
}
