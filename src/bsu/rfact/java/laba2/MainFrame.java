package bsu.rfact.java.laba2;

import javax.swing.*;
import static java.lang.Math.*;
import javafx.scene.control.RadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final int WIDTH = 400,
            HEIGHT = 320;
    private JTextField textFieldResult,
            textFieldX,
            textFieldY;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;

    public Double calculate1(Double x, Double y, Double z){
        return pow( pow(log(1+x), 2) + cos(PI*pow(z, 3)) , sin(y)) +
                pow( exp(pow(x, 2)) + cos(exp(z)) + sqrt(1/y), 1/x);
    }
    public Double calculate2(Double x, Double y, Double z){
        return pow( cos(PI*pow(x, 3)) + pow(log(1+y), 2) , 1/4) *
                ( exp(pow(z, 2)) + sqrt(1/x) + cos(exp(y)));
    }

    private void addRadioButton(String buttonName, final int formulaId){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    public MainFrame(){
        super("Formula calculation...");

        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation( (kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);

    }

    public static void main(String[] args) {

    }
}
