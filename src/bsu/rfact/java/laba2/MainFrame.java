package bsu.rfact.java.laba2;

import javax.swing.*;
import static java.lang.Math.*;
import javafx.scene.control.RadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    private static final int WIDTH = 1000,
            HEIGHT = 500;
    private JTextField textFieldResult,
            textFieldX,
            textFieldY,
            textFieldZ,
            textFieldMem1,
            textFieldMem2,
            textFieldMem3;
    private ButtonGroup radioFormulaButtons = new ButtonGroup(),
            radioMemoryButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox(),
            hboxMemoryType = Box.createHorizontalBox();
    private int formulaId = 1,
            memoryId = 1;

    private BufferedImage image = null;
    private JLabel labelImage = new JLabel();

    public Double calculate1(Double x, Double y, Double z){
        return pow( log(pow(1+x, 2))+ cos(PI*pow(z, 3)) , sin(y)) +
                pow( exp(pow(x, 2)) + cos(exp(z)) + sqrt(1/y), 1/x);
    }
    public Double calculate2(Double x, Double y, Double z){
        return pow(cos(PI*pow(x, 3)) + log(pow(1+y, 2)), 1/4) *
                (exp(pow(z, 2)) + sqrt(1/x) + cos(exp(y)));
    }

    private void addRadioFormulaButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.formulaId = formulaId;
                try {
                    if (formulaId == 1) {
                        image = ImageIO.read(new File("src/bsu/frct/java/lab2/Formula 1.jpg"));
                    } else {
                        image = ImageIO.read(new File("src/bsu/frct/java/lab2/Formula 2.jpg"));
                    }
                    labelImage.setIcon(new ImageIcon(image));
                } catch (IOException ex) {
                    System.out.println("!!!Image not found!!!");
                }
            }
        });
        radioFormulaButtons.add(button);
        hboxFormulaType.add(button);
    }
        private void addRadioMemoryButton(String buttonName, final int memoryId){
            JRadioButton button = new JRadioButton(buttonName);
            button.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e) {
                    MainFrame.this.memoryId = memoryId;
                }
            });
            radioMemoryButtons.add(button);
            hboxMemoryType.add(button);
        }


    public MainFrame(){
        super("Formula calculation...");

        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation( (kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioFormulaButton("Formula 1", 1);
        addRadioFormulaButton("Formula 2", 2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        radioFormulaButtons.setSelected(radioFormulaButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        Box hboxFormulaImage = Box.createHorizontalBox();
        hboxFormulaImage.add(Box.createHorizontalGlue());
        hboxFormulaImage.add(labelImage);
        hboxFormulaImage.add(Box.createHorizontalGlue());
        hboxFormulaImage.setBorder(BorderFactory.createLineBorder(Color.RED));

        Box hboxVariables = Box.createHorizontalBox();
        JLabel labelForX = new JLabel("X =");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("y =");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z =");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

        Box hboxResult = Box.createHorizontalBox();
        JLabel labelForResult = new JLabel("Result =");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

        Box hboxButtons = Box.createHorizontalBox();
        JButton buttonCalc = new JButton("Calculate");
        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Double result = Double.parseDouble(textFieldResult.getText());
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    if (formulaId == 1){
                        result = calculate1(x, y, z);
                    }   else{
                        result = calculate2(x, y, z);
                    }
                    textFieldResult.setText(result.toString());
                }   catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Floating point format error", "Wrong number format",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Reset");
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldResult.setText("0");
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
            }
        });
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        hboxMemoryType.add(Box.createHorizontalGlue());
        addRadioMemoryButton("Mem1", 1);
        addRadioMemoryButton("Mem2", 2);
        addRadioMemoryButton("Mem3", 3);
        hboxMemoryType.add(Box.createHorizontalGlue());
        radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true);
        hboxMemoryType.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxFormulaImage);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxMemoryType);
        contentBox.add(Box.createVerticalGlue());
        contentBox.setBorder(BorderFactory.createLineBorder(Color.PINK));
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }



    public static void main(String[] args) {

        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
