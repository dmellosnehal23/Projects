//##############################################################
//##      @Author: PAYAL BARUAH
//##############################################################
package sjsu.cmpe239.ui;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import com.mongodb.DBObject;

import sjsu.cmpe239.util.DBWorker;

public class PieChart extends JFrame {

	private static final long serialVersionUID = 1L;
	private DBWorker db = null; 

	public PieChart(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		
		this.db = new DBWorker();
		
		// This will create the dataset
		PieDataset dataset = createDataset();
		// based on the dataset we create the chart
		JFreeChart chart = createChart(dataset, chartTitle);
		// we put the chart into a panel
		ChartPanel chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		// add it to our application
		setContentPane(chartPanel);		
	}

	/**
	 * Creates a sample dataset
	 */

	private PieDataset createDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
	
		DBObject record = this.db.getRecord();
		if(record == null){
			return null;
		}
		Long d = (Long)record.get("democrat");
		Long r = (Long)record.get("republican");
		
		result.setValue("Democrat", d);
		result.setValue("Republican", r);
			
		return result;

	}

	/**
	 * Creates a chart
	 */

	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart3D(title, // chart title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;

	}

	public static void main(String[] args) {
		PieChart demo = new PieChart("Comparison", "Democrat Vs Republican");
		demo.pack();
		demo.setVisible(true);
	}
}