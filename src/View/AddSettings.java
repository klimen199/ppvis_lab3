package View;

import Controller.OkBtnInput;
import Model.InputData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSettings {
    InputData inputData = new InputData();

    private JLabel label1;
    private JLabel label2;
    private JTextField elementsInStep;
    private JTextField numOfElements;
    private JDialog dialog;


    public AddSettings(InputData inputData){
        this.inputData = inputData;

        dialog = new JDialog();

        Box main = Box.createVerticalBox();
        label1 = new JLabel("Step (elements): ");
        label2 = new JLabel("Max number of elements: ");
        elementsInStep = new JTextField(7);
        numOfElements = new JTextField(10);
        JButton okBtn = new JButton("OK");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OkBtnInput temp = new OkBtnInput(inputData);
                temp.processingInput(elementsInStep.getText(),numOfElements.getText());
//                System.out.println(inputData.elementsStep + " " + inputData.elementsNum);
            }
        });

        main.add(Box.createVerticalStrut(20));
        main.add(fHorizontal());
        main.add(Box.createVerticalStrut(20));
        main.add(sHorizontal());
        main.add(Box.createVerticalStrut(20));
        main.add(okBtn);
        main.add(Box.createVerticalStrut(20));

        dialog.add(main);
    }

    public JDialog getDialog(){
        return dialog;
    }


    private Box fHorizontal(){
        Box horizontal1 = Box.createHorizontalBox();
        horizontal1.add(Box.createHorizontalStrut(10));
        horizontal1.add(label2);
        horizontal1.add(numOfElements);
        horizontal1.add(Box.createHorizontalStrut(10));
        return horizontal1;
    }

    private Box sHorizontal(){
        Box horizontal2 = Box.createHorizontalBox();
        horizontal2.add(Box.createHorizontalStrut(15));
        horizontal2.add(label1);
        horizontal2.add(Box.createHorizontalStrut(15));
        horizontal2.add(elementsInStep);
        horizontal2.add(Box.createHorizontalStrut(10));
        return horizontal2;
    }
}
