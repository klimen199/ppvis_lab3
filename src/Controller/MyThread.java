package Controller;

import Model.InputData;
import Model.Scale;

import java.awt.*;


public class MyThread extends Thread {
    /// ПЕРЕДАТЬ SCALE в PAINTGRAPH как-то
    PaintGraph paintGraph = new PaintGraph();
    InputData inputData = new InputData();
    SortFunction sFunc = new SortFunction();

    public MyThread(PaintGraph paintGraph, InputData inputData){
        this.paintGraph = paintGraph;
        this.inputData = inputData;
    }

    public void run(){
        if(paintGraph.tableModel.getRowCount() > 0){
            for (int i = paintGraph.tableModel.getRowCount() - 1; i > -1; i--) {
                paintGraph.tableModel.removeRow(i);
            }
        }
        Graphics2D g = (Graphics2D) paintGraph.getGraphics();
        paintGraph.drawCoordinatePlane(g);

//        for(double x = paintGraph.minX; x <= paintGraph.maxX; x += inputData.elementsStep) {
//            if(currentX == paintGraph.minX) {
//                paintGraph.prevX = currentX;
//                paintGraph.prevY = sFunc.getY((int)x);
//            }
//            try {
//                Thread.sleep(700);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            paintGraph.addAndDraw(currentX, sFunc.getY((int)x), g);
//            Object[] row = {currentX, sFunc.getY((int)x)};
//            paintGraph.tableModel.addRow(row);
//        }
        for(int currElemNum = 0; currElemNum < inputData.elementsNum; currElemNum += inputData.elementsStep) {
            if(currElemNum == 0) {
                paintGraph.prevX = currElemNum;
                paintGraph.prevY = 0;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double microSec = sFunc.getY(currElemNum);
            System.out.println("time 2 " + microSec);
            paintGraph.addAndDraw(currElemNum, microSec, g);
            Object[] row = {currElemNum, microSec};
            paintGraph.tableModel.addRow(row);
        }
    }
}