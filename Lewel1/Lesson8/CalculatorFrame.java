package ru.geekbrains.HW1_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame {
    JTextField textField;
    public CalculatorFrame() throws HeadlessException {
        setTitle("Calculator");
        setBounds(100, 100, 300, 400);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
//        top
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BorderLayout());
        add(pnTop, BorderLayout.NORTH);
        textField = new JTextField();
        Font font = textField.getFont().deriveFont(18f);
        textField.setFont(font);
        textField.setEditable(false);
        pnTop.add(textField, BorderLayout.CENTER);

//        bottom
        JPanel pnBottom = new JPanel();
        pnBottom.setLayout(new GridLayout(4, 4));
        char[] chars = {
                '1', '2', '3', '+',
                '4', '5', '6', '-',
                '7', '8', '9', '*',
                '0', 'C', '=', '/'};
        for (char c: chars) {
            String title = "" + c;
            JButton btn = new JButton(title);
            switch (c){
                case '+', '-', '*', '/':
                    btn.addActionListener(cmdActions);
                    break;
                case 'C':
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            textField.setText("");
                        }
                    });
                    break;
                case '=':
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            doCalc();
                        }
                    });
                    break;
                default: btn.addActionListener(numActions);
            }
            pnBottom.add(btn);
        }
        add(pnBottom, BorderLayout.CENTER);
        setVisible(true);
    }
    private ActionListener numActions = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            textField.setText(textField.getText() + btn.getText());
        }
    };
    private ActionListener cmdActions = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            char cmd = ((JButton) e.getSource()).getText().charAt(0);
            appendCmd(cmd);
        }
    };
    private void appendCmd(char cmd){
        StringBuilder sb = new StringBuilder(textField.getText());
        if (sb.length() == 0) return;
        char last = sb.charAt(sb.length() - 1);
        if (last == '+' || last == '-' || last == '*' || last == '/')
            sb.setCharAt(sb.length() - 1, cmd);
        else sb.append(cmd);
        textField.setText(sb.toString());
    }
    private void doCalc(){
        CalculatorExpression expr = new CalculatorExpression(textField.getText());
        textField.setText(String.valueOf(expr.evaluate()));
    }
}
