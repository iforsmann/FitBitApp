import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        System.out.println("Launching ...");
        JFrame f = new JFrame("FitBitData");

        PieChart pie = new PieChart("Please Work");
        pie.addData("Yes", 20);
        pie.addData("No", 40);
        JPanel piePanel = pie.createPanel();
        piePanel.setBounds(40, 40, 300, 200);
        piePanel.setBackground(Color.lightGray);

        f.add(piePanel);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(500, 400);
        f.setLayout(null);
        f.setVisible(true);


    }
}
