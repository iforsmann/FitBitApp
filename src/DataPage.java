import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.concurrent.Flow;

public class DataPage extends JPanel {
    ArrayList<String> tableNames = new ArrayList();
    JFrame frame;
    JPanel disp;
    JButton sub;
    ArrayList<JTextField> inputs = new ArrayList();
    JLabel warn;
    DBConnection db;
    JPanel self = this;

    public DataPage(JFrame frame){
        super(new BorderLayout());
        this.frame = frame;
        Dotenv dotenv = Dotenv.configure().load();
        BarChart bar = new BarChart("Average Calories Burned", "Date", "Calories");
        this.db = new DBConnection();
        db.query("use " + dotenv.get("USER") + ";");
        ResultSet tNamesResp = db.queryResp("show tables;");
        try{
            while(tNamesResp.next()){
                tableNames.add(tNamesResp.getString("Tables_in_jgcavana"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JComboBox combo = new JComboBox(tableNames.toArray());
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBoxes(combo.getSelectedIndex(), db);
            }
        });
        JPanel combobox = new JPanel();
        combobox.add(combo);
        this.add(combobox, BorderLayout.PAGE_START);
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
        //this.disp = new JLabel("Foo Bar");
        //this.add(this.disp);
    }
    private void updateBoxes(int index, DBConnection db){
        Dotenv dotenv = Dotenv.configure().load();
        db.query("use " + dotenv.get("USER") + ";");
        ResultSet resp = db.queryResp("select * from " + tableNames.get(index) + " limit 5;");
        try{
            ResultSetMetaData meta = resp.getMetaData();
            int numCols = meta.getColumnCount();
            System.out.println(numCols);
            JPanel boxes = new JPanel();
            boxes.setLayout(new BoxLayout(boxes, BoxLayout.PAGE_AXIS));
            for(int i = 1; i <= numCols; i++){
                System.out.println(meta.getColumnName(i));
                JPanel box = new JPanel();
                box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
                box.add(new JLabel(meta.getColumnName(i)));
                JTextField field = new JTextField();
                this.inputs.add(field);
                box.add(new JTextField());
                boxes.add(box);
            }
            JButton submit = new JButton("Submit");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(checkForm()){
                        ArrayList<String> inputStrs = new ArrayList<>();
                        for(JTextField f : inputs){
                            inputStrs.add(f.getText());
                        }
                        String strs = String.join(",", inputStrs);
                        db.query("insert into " + tableNames.get(index) + " values(" + strs + ");");
                    }else{
                        JDialog d = new JDialog(frame, "Error");
                        JLabel l = new JLabel("Please fill out all input boxes.");
                        l.setHorizontalAlignment(JLabel.CENTER);
                        d.add(l);
                        d.setVisible(true);
                    }
                }
            });
            boxes.add(submit);
            this.sub = submit;
            this.disp = boxes;
            this.add(boxes, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean checkForm(){
        for(JTextField f : this.inputs){
            if(f.getText().equals("")){
                return false;
            }
        }
        return true;
    }

}
