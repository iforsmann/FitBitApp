import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;

public class PieChart extends ApplicationFrame{

    private DefaultPieDataset data = new DefaultPieDataset();
    String title;

    public PieChart(String title) {
        super(title);
        this.title = title;
    }

    public void addData(String key, Number value){
        this.data.setValue(key, value);
    }

    public JPanel createPanel(){
        JFreeChart chart = ChartFactory.createPieChart(
                this.title,    // chart title
                this.data,          // data
                true,             // include legend
                true,
                false);
        JPanel panel =  new ChartPanel(chart);
        setContentPane(panel);
        return panel;
    }
}
