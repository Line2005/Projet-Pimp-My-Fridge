package View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class GraphFrame extends JFrame {

    private XYSeries humiditySeries = new XYSeries("Humidity");
    private XYSeries indoorTemperatureSeries = new XYSeries("Indoor Temperature");
    private XYSeries outdoorTemperatureSeries = new XYSeries("Outdoor Temperature");
    private XYSeries dewPointSeries = new XYSeries("Dew Point");
    private XYSeries consigne = new XYSeries("Set Point");

    public GraphFrame() {
        setTitle("Graph Frame");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créez des séries et ajoutez-les à la collection
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(humiditySeries);
        dataset.addSeries(indoorTemperatureSeries);
        dataset.addSeries(outdoorTemperatureSeries);
        dataset.addSeries(dewPointSeries);
        dataset.addSeries(consigne);

        // Créez un graphique en fonction du dataset
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graphique en temps réel",
                "Temps",
                "Valeur",
                dataset,  // Utilisez XYSeriesCollection ici
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Créez un panneau de graphique et ajoutez-le à l'interface utilisateur
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphFrame graphFrame = new GraphFrame();
            graphFrame.setVisible(true);
        });
    }
    public void updateChartData(float humidity, float indoorTemperature, float outdoorTemperature, float dewPoint, float temperatureValue) {
        humiditySeries.addOrUpdate(System.currentTimeMillis(), humidity);
        indoorTemperatureSeries.addOrUpdate(System.currentTimeMillis(), indoorTemperature);
        outdoorTemperatureSeries.addOrUpdate(System.currentTimeMillis(), outdoorTemperature);
        dewPointSeries.addOrUpdate(System.currentTimeMillis(), dewPoint);
        consigne.addOrUpdate(System.currentTimeMillis(), temperatureValue);


    }}

