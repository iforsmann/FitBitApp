import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PanelCycle extends JPanel {

    private List<JPanel> panels = new ArrayList();
    private int current = 0;
    private JPanel empty = new JPanel(new FlowLayout());
    private JPanel center;
    JButton left = new JButton("left");
    JButton right = new JButton("right");

    JPanel bLeft;
    JPanel bRight;

    public PanelCycle(){
        super(new BorderLayout());

        JLabel mt = new JLabel("No Charts", SwingConstants.CENTER);
        empty.add(mt, SwingConstants.CENTER);

        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previous();
            }
        });

        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });

        bLeft = new JPanel();
        bLeft.setLayout(new BoxLayout(bLeft, BoxLayout.PAGE_AXIS));
        bLeft.add(left);

        bRight = new JPanel();
        bRight.setLayout(new BoxLayout(bRight, BoxLayout.PAGE_AXIS));
        bRight.add(right, BorderLayout.CENTER);

        this.add(bLeft, BorderLayout.LINE_START);
        this.add(bRight, BorderLayout.LINE_END);
        this.center = empty;
    }

    private void update(JPanel p){
        this.removeAll();
        this.add(p, BorderLayout.CENTER);
        this.add(bLeft, BorderLayout.LINE_START);
        this.add(bRight, BorderLayout.LINE_END);
        this.center = p;
        this.revalidate();
        this.repaint();
    }

    public void init(){
        if(this.panels.size() > 0){
            update(this.panels.get(0));
        }else{
            update(this.empty);
        }
    }

    public void addPanel(JPanel p){
        this.panels.add(p);
    }

    private void next(){
        JPanel newPanel;
        if(this.panels.size() > 0){
            this.current = (this.current + 1) % this.panels.size();
            newPanel =  this.panels.get(this.current);
        }else{
            newPanel = this.empty;
        }
        this.update(newPanel);
    }

    private void previous(){
        JPanel newPanel;
        if(this.panels.size() > 0) {
            if (this.current == 0) {
                this.current = this.panels.size() - 1;
            } else {
                this.current--;
            }
            newPanel = this.panels.get(this.current);
        }else{
            newPanel = this.empty;
        }
        this.update(newPanel);
    }
}
