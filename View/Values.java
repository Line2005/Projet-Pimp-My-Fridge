package View;

import javax.swing.*;
import java.awt.*;

public class Values extends JFrame {

    private JLabel humidityLabel;
    private JLabel indoorTemperatureLabel;
    private JLabel outdoorTemperatureLabel;
    private JLabel dewPointLabel;

    public Values() {
        setTitle("Valeurs récupérées depuis la carte Arduino");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Définir la couleur de fond
        getContentPane().setBackground(Color.CYAN);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.cyan);

        JLabel welcomeLabel = new JLabel("VOICI LES VALEURS RECUPERE DE L'ARDUINO");
        welcomeLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel dataPanel = new JPanel(new GridLayout(4, 1));

        humidityLabel = createLabel("Humidité intérieure : ");
        indoorTemperatureLabel = createLabel("Temperature intérieure : ");
        outdoorTemperatureLabel = createLabel("Temperature extérieure: ");
        dewPointLabel = createLabel("Point de rosée: ");

        dataPanel.add(humidityLabel);
        dataPanel.add(indoorTemperatureLabel);
        dataPanel.add(outdoorTemperatureLabel);
        dataPanel.add(dewPointLabel);

        mainPanel.add(dataPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JLabel createLabel(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        return label;
    }

    public void updateHumidityLabel(String value) {
        humidityLabel.setText("Humidité intérieure : " + value);
    }

    public void updateIndoorTemperatureLabel(String value) {
        indoorTemperatureLabel.setText("Temperature intérieure : " + value);
    }

    public void updateOutdoorTemperatureLabel(String value) {
        outdoorTemperatureLabel.setText("Temperature extérieure: " + value);
    }

    public void updateDewPointLabel(String value) {
        dewPointLabel.setText("Point de rosée: " + value);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Values app = new Values();
            app.setVisible(true);
        });
    }

    public void updateChartData(float humidityValue, float temperatureint, float teemperatureext, float point) {
    }


    //public void updateChartData(float humidityValue, float temperatureint, float teemperatureext, float point) {
    //}
}