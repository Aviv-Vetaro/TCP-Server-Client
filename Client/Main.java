import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args)
    {
        JFrame mainFrame = new JFrame();
        MainPanel mainPanel = new MainPanel(new ServerAdapter());
        mainFrame.add(mainPanel);
        mainFrame.setPreferredSize(new Dimension(400, 400));
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainFrame.setSize(new Dimension(400, 400));
        mainPanel.setSize(new Dimension(400, 400));
        mainPanel.revalidate();
        mainPanel.repaint();
        mainPanel.setVisible(true);
        mainFrame.setVisible(true);
    }
}
