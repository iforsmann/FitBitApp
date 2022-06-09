import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import io.github.cdimascio.dotenv.*;

public class AnalyzePage extends JPanel{
    JFrame frame;
    JPanel self = this;

    public AnalyzePage(JFrame frame){
        super(new BorderLayout());
        this.frame = frame;
        JPanel topBar = new JPanel(new FlowLayout());

        String[] infos = {};
        JLabel infoL = new JLabel("Information");
        JComboBox infoTF = new JComboBox(infos);
        JPanel infoBox = new JPanel();
        infoBox.setLayout(new BoxLayout(infoBox, BoxLayout.PAGE_AXIS));
        infoBox.add(infoL);
        infoBox.add(infoTF);
        topBar.add(infoBox);

        String[] orders = {};
        JLabel sortL = new JLabel("Information");
        JComboBox sortCB = new JComboBox(orders);
        JPanel sortB = new JPanel();
        sortB.setLayout(new BoxLayout(sortB, BoxLayout.PAGE_AXIS));
        sortB.add(sortL);
        sortB.add(sortCB);
        topBar.add(sortB);

        JLabel lim = new JLabel("Limit");
        JTextField limTF = new JTextField();
        JPanel limB = new JPanel();
        limB.setLayout(new BoxLayout(limB, BoxLayout.PAGE_AXIS));
        limB.add(lim);
        limB.add(limTF);
        topBar.add(limB);

        this.add(topBar, BorderLayout.PAGE_START);

        JButton back = new JButton("back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(self);
                frame.repaint();
                frame.add(new FrontPage(frame));
                frame.validate();
            }
        });
        this.add(back, BorderLayout.PAGE_END);
    }
}
