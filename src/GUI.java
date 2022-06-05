import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI {
    public int numClicks;

    public GUI(){
        System.out.println("Launching ...");
        JFrame f = new JFrame("FitBitData");

        PieChart pie = new PieChart("Please Work");
        pie.addData("Yes", 20);
        pie.addData("No", 40);
        JPanel piePanel = pie.createPanel();
        //piePanel.setBounds(40, 40, 300, 200);
        piePanel.setBackground(Color.lightGray);

        f.add(piePanel);

        JButton b = new JButton("Click Me");

        JLabel l = new JLabel("Number of Clicks: " + numClicks);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numClicks++;
                l.setText("Number of Clicks: " + numClicks);
            }
        });

        f.add(b);
        f.add(l);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(500, 400);
        f.setLayout(new FlowLayout());
        f.setVisible(true);
    }

}
