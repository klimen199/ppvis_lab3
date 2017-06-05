package View;

import Controller.MyThread;
import Controller.PaintGraph;
import Model.InputData;
import Model.Scale;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame {

    static String[] columnNames = {
            "X",
            "Y"
    };
    public InputData inputData= new InputData();
    private static DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    private Scale scale = new Scale();
    private JLabel scaleLabel = new JLabel();
    PaintGraph graph;

    public JFrame createFrame(String name){
        JFrame frame = new JFrame(name);

        JTable tableOfValues = new JTable(tableModel);
        JScrollPane scrollPane1 = new JScrollPane(tableOfValues);
        scrollPane1.setSize(20,20);

        graph = new PaintGraph();
        graph.setModelData(scale,inputData);
        graph.setTable(tableModel,tableOfValues);
        JScrollPane scrollPane2 = new JScrollPane(graph);

        frame.add(scrollPane1, BorderLayout.WEST);
        frame.add(scrollPane2, BorderLayout.CENTER);
        frame.add(createSettingsBox(),BorderLayout.SOUTH);
        return frame;
    }


    private Box createSettingsBox(){
        Box settingsBox = Box.createHorizontalBox();
        JButton addSettings = new JButton("Add Settings");
        addSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog add = new AddSettings(inputData).getDialog();
                add.setSize(350,220);
                add.setVisible(true);
                add.setLocationRelativeTo(null);
            }
        });
        JButton createGraph = new JButton("Create Diagram");
        createGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new MyThread(graph, inputData);
                thread.start();
                graph.repaint();
            }
        });
        scaleLabel.setText(scale.getCurrentStringScale());
        settingsBox.add(Box.createHorizontalStrut(150));
        settingsBox.add(addSettings);
        settingsBox.add(Box.createHorizontalStrut(500));
        settingsBox.add(createGraph);
        settingsBox.add(Box.createHorizontalStrut(100));
        settingsBox.add(scaleLabel);
        return settingsBox;
    }
}
