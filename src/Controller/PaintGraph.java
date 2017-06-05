package Controller;

import Model.InputData;
import Model.Scale;
import javafx.scene.shape.Line;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PaintGraph extends JPanel{
    DefaultTableModel tableModel;
    JTable table;
    Scale scale = new Scale();
    InputData inputData = new InputData();

    private int canvasSizeX = 630;
    private int canvasSizeY = 455;
    public int minX = -1;
    public int maxX = 100;
    private int minY = -1;
    private int maxY = 50;

    double coefX, coefY;
    double prevX, prevY;
    int centerX, centerY;

    public PaintGraph(){
        setSize(new Dimension(canvasSizeX, canvasSizeY));
        addMouseWheelListener(e -> {
            if (e.isControlDown()){
                if (e.getWheelRotation() < 0){
                    canvasSizeX *= 1.2;
                    canvasSizeY *= 1.2;
                    scale.zoomPlus();
                    System.out.println(scale.getCurrentStringScale());
                }
                else if (scale.getScaleProcent() > 50 && e.getWheelRotation() > 0){
                    canvasSizeX /= 1.2;
                    canvasSizeY /= 1.2;
                    scale.zoomMinus();
                    System.out.println(scale.getCurrentStringScale());
                }
                setPreferredSize(new Dimension(canvasSizeX, canvasSizeY));
                setSize(canvasSizeX, canvasSizeY);
                repaint();
            }
        });
    }

    public void setModelData(Scale scale, InputData inputData){
        this.scale = scale;
        this.inputData = inputData;
    }

    public void setTable(DefaultTableModel tableModel, JTable table) {
        this.tableModel = tableModel;
        this.table = table;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 =(Graphics2D) g;
        g2.setColor(Color.BLACK);

        drawCoordinatePlane(g2);

        for(int i = 0; i <tableModel.getRowCount()-1; i++){
            int x1 = (int) ((double) tableModel.getValueAt(i, 0) * coefX);
            int y1 = (int) ((double) tableModel.getValueAt(i, 1) * coefY);
            int x2 = (int) ((double) tableModel.getValueAt(i + 1, 0) * coefX);
            int y2 = (int) ((double) tableModel.getValueAt(i + 1, 1) * coefY);

            g2.drawLine(centerX + x1, centerY - y1, centerX + x2, centerY - y2);
        }
    }

    public void drawCoordinatePlane(Graphics g){
        coefX = getWidth()/(maxX - minX);
        coefY = getHeight()/(maxY - minY);

        if(Math.signum(minX) == Math.signum(maxX)) {
            g.drawLine(5, 0, 5, getHeight());
            g.drawLine(5, 0, 1, 10);
            g.drawLine(5, 0, 9, 10);
            centerX =  - (int) (minX * coefX);
        } else {
            g.drawLine((int) (Math.abs(minX) * getWidth() / (maxX - minX)), 0,
                    (int) (Math.abs(minX) * getWidth() / (maxX - minX)), getHeight());
            g.drawLine((int) (Math.abs(minX) * getWidth() / (maxX - minX)), 0,
                    (int) (Math.abs(minX) * getWidth() / (maxX - minX))-4, 10);
            g.drawLine((int) (Math.abs(minX) * getWidth() / (maxX - minX)), 0,
                    (int) (Math.abs(minX) * getWidth() / (maxX - minX))+4, 10);
            centerX = (int) (coefX * Math.abs(minX));
        }

        if(Math.signum(minY) == Math.signum(maxY)) {
            centerY = getHeight() - 5;
            g.drawLine(0, centerY, getWidth(), centerY);
            g.drawLine(getWidth(), centerY, getWidth()-10, centerY+4);
            g.drawLine(getWidth(), centerY, getWidth()-10, centerY-4);
        } else {
            g.drawLine(0, (int) (maxY * getHeight() / (maxY - minY)),
                    getWidth(), (int) (maxY * getHeight() / (maxY - minY)));
            g.drawLine(getWidth(), (int) (maxY * getHeight() / (maxY - minY)),
                    getWidth()-10, (int) (maxY * getHeight() / (maxY - minY))+4);
            g.drawLine(getWidth(), (int) (maxY * getHeight() / (maxY - minY)),
                    getWidth()-10, (int) (maxY * getHeight() / (maxY - minY))-4);
            centerY = (int) (coefY * maxY);
        }
        for (int i = 0; i <= 10; i++){
            int x = (int) (minX + i * getWidth() / 10 / coefX);
            int y = (int) (minY + i * getHeight() / 10 / coefY);

            g.drawString(String.valueOf(x), i * getWidth() / 10, centerY);
            g.drawLine(i * getWidth() / 10, centerY-5, i * getWidth() / 10, centerY+5);
            g.drawString(String.valueOf(y), centerX, getHeight() - i * getHeight() / 10);
            g.drawLine(centerX-5, getHeight() - i * getHeight() / 10, centerX+5, getHeight() - i * getHeight() / 10);
        }
    }

    public void addAndDraw(double x, double y, Graphics g) {
        g.drawLine(centerX + (int) (prevX * coefX), centerY - (int) (prevY * coefY),
                centerX + (int)(x * coefX), centerY - (int)(y * coefY));
        prevX = x;
        prevY = y;
    }
}
