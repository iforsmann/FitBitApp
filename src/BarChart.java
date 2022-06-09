import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;

public class BarChart extends ApplicationFrame{

    private DefaultCategoryDataset data = new DefaultCategoryDataset();
    private String title;
    private String xTitle;
    private String yTitle;

    public BarChart(String title, String xTitle, String yTitle) {
        super(title);
        this.title = title;
        this.xTitle = xTitle;
        this.yTitle = yTitle;
    }

    public void addData(String key, double value){
        this.data.addValue(value, this.yTitle, key);
    }

    public JPanel createPanel(){
        JFreeChart chart = ChartFactory.createBarChart(this.title, this.xTitle, this.yTitle, this.data, PlotOrientation.VERTICAL, false, false, false);
        JPanel panel =  new ChartPanel(chart);
        setContentPane(panel);
        return panel;
    }
}
