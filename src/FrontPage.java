import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import io.github.cdimascio.dotenv.*;


public class FrontPage extends JPanel{
    JFrame frame;
    FrontPage self = this;
    public FrontPage (JFrame frame){
        super(new BorderLayout());

        this.setName("Welcome!");
        this.frame = frame;
        JLabel label = new JLabel("Welcome!", SwingConstants.CENTER);
        this.add(label, BorderLayout.PAGE_START);

        PieChart pie = new PieChart("Please Work");
        pie.addData("Yes", 20);
        pie.addData("No", 40);
        JPanel piePanel = pie.createPanel();
        //piePanel.setBounds(40, 40, 300, 200);
        piePanel.setBackground(Color.lightGray);

        PieChart pie2 = new PieChart("Please Work");
        pie2.addData("foo", 50);
        pie2.addData("bar", 40);
        JPanel piePanel2 = pie2.createPanel();

        JPanel chart1 = getChart1();
        JPanel chart2 = getChart2();

        PanelCycle cycle = new PanelCycle();
        //cycle.addPanel(piePanel);
        cycle.addPanel(chart2);
        //cycle.addPanel(piePanel2);
        cycle.addPanel(chart1);
        cycle.init();

        this.add(cycle, BorderLayout.CENTER);

        JButton tables = new JButton("Analyze");
        JButton addData = new JButton("Add Data");
        JPanel bottom = new JPanel(new FlowLayout());

        addData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(self);
                frame.repaint();
                frame.add(new DataPage(frame));
                frame.validate();
            }
        });

        tables.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(self);
                frame.repaint();
                frame.add(new AnalyzePage(frame));
                frame.validate();
            }
        });

        bottom.add(tables);
        bottom.add(addData);

        this.add(bottom, BorderLayout.PAGE_END);
    }

    private JPanel getChart1(){
        Dotenv dotenv = Dotenv.configure().load();
        BarChart bar = new BarChart("Average Steps", "Date", "Num Steps");
        DBConnection db = new DBConnection();
        db.query("use " + dotenv.get("USER") + ";");
        db.query("CREATE VIEW temp_chart1 AS\n" +
                "SELECT act_date, ROUND(AVG(total_active_hours), 2) as average_active_hours\n" +
                "FROM Activity\n" +
                "GROUP BY act_date\n" +
                "ORDER BY average_active_hours DESC;");
        ResultSet result = db.queryResp("SELECT * FROM temp_chart1 ORDER BY act_date limit 30;");
        db.query("DROP VIEW temp_chart1;");
        try{
            while(result.next()){
                bar.addData(result.getString("act_date"), Double.parseDouble(result.getString("average_active_hours")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bar.createPanel();
    }

    private JPanel getChart2(){
        Dotenv dotenv = Dotenv.configure().load();
        BarChart bar = new BarChart("Average Calories Burned", "Date", "Calories");
        DBConnection db = new DBConnection();
        db.query("use " + dotenv.get("USER") + ";");
        ResultSet result = db.queryResp("SELECT act_date, ROUND(AVG(calories), 2) as average_calories\n" +
                "FROM DailyActivity\n" +
                "GROUP BY act_date\n" +
                "ORDER BY act_date asc;");
        try{
            while(result.next()){
                bar.addData(result.getString("act_date"), Double.parseDouble(result.getString("average_calories")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bar.createPanel();
    }
}
