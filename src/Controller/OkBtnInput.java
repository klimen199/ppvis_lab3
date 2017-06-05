package Controller;

import Model.InputData;

import javax.swing.*;

/**
 * Created by user on 23.05.2017.
 */
public class OkBtnInput {
    public InputData inputData = new InputData();


    public OkBtnInput(InputData inputData){
        this.inputData = inputData;
    }

    public void processingInput(String elementsStepString, String elemNumString){
        if (elementsStepString.equals("") || elemNumString.equals("")){
            JOptionPane.showMessageDialog(null, "Fill settings.");
            return;
        }

        int arrNum;
        int elemNum;

        try{ arrNum = Integer.parseInt(elementsStepString); }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error in (int) NUMBER of arrays.");
            return;
        }
        try{ elemNum = Integer.parseInt(elemNumString); }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error in (int) NUMBER of elements.");
            return;
        }

        inputData.setArraysNum(arrNum);
        inputData.setElementsNum(elemNum);

    }
}
