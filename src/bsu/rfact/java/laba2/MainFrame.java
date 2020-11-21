package bsu.rfact.java.laba2;

import javax.swing.*;
import static java.lang.Math.*;

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

    public static void main(String[] args) {

    }
}
