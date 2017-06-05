import View.MainFrame;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args){
        JFrame mainWindow = new MainFrame().createFrame("PPViS - 3");
        mainWindow.setSize(1250, 600);
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
