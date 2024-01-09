package model;

import View.GraphFrame;
import View.Values;
import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model extends JFrame {
    private List<Float> humidityValues = new ArrayList<>();
    private List<Float> temperatureintvalues = new ArrayList<>();
    private List<Float> temperatureextValues = new ArrayList<>();
    private List<Float> pointValues = new ArrayList<>();
    private JComboBox<String> portComboBox;
    private JButton refreshButton;
    private JButton validerButton;
    public Values valuesInstance;
    private JButton connectButton;
    private JTextField temperatureField;
    private JLabel sentValueLabel;
    private SerialPort selectedPort;
    private JLabel humidityLabel;
    private JLabel indoorTemperatureLabel;
    private JLabel outdoorTemperatureLabel;
    private JLabel dewPointLabel;
    public GraphFrame graphFrame;
    private boolean isAlertShown = false;
    private boolean eleve = false;
    private JButton valueButton;
    float humidityValue = 0;
    float temperatureint=0;
    float teemperatureext = 0;
    float point=0;

    //private GraphFrame graphFrame;

    public Model() {
        setTitle("Application pour la gestion d'un Mini-frigo");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.lightGray);

        JLabel welcomeLabel = new JLabel("WELCOME ON OUR APPLICATION");
        welcomeLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.lightGray);

        JPanel portPanel = new JPanel(new GridBagLayout());
        portPanel.setBackground(Color.lightGray);

        JLabel selectPortLabel = new JLabel("Select a port:");
        selectPortLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        portComboBox = new JComboBox<>();
        portComboBox.setPreferredSize(new Dimension(200, 40));
        refreshButton = new JButton("Actualiser");
        refreshButton.setPreferredSize(new Dimension(100, 40));
        connectButton = new JButton("Connecter");
        connectButton.setPreferredSize(new Dimension(100, 40));
        // Ajouter un label pour afficher les valeurs envoyées
        sentValueLabel = new JLabel("Consigne: ");
        sentValueLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        // Ajouter un champ de texte pour la température
        temperatureField = new JTextField(10);
        temperatureField.setPreferredSize(new Dimension(100,40));
        validerButton = new JButton("Valider");
        validerButton.setPreferredSize(new Dimension(100, 40));


        GridBagConstraints gbcSelectPortLabel = new GridBagConstraints();
        gbcSelectPortLabel.gridx = 0;
        gbcSelectPortLabel.gridy = 0;
        gbcSelectPortLabel.weighty = 1;
        gbcSelectPortLabel.anchor = GridBagConstraints.EAST;
        gbcSelectPortLabel.insets = new Insets(0, 0, 10, 10);
        portPanel.add(selectPortLabel, gbcSelectPortLabel);

        GridBagConstraints gbcPortComboBox = new GridBagConstraints();
        gbcPortComboBox.gridx = 1;
        gbcPortComboBox.gridy = 0;
        gbcPortComboBox.weighty = 1;
        gbcPortComboBox.anchor = GridBagConstraints.WEST;
        gbcPortComboBox.insets = new Insets(0, 0, 10, 10);
        portPanel.add(portComboBox, gbcPortComboBox);

        GridBagConstraints gbcRefreshButton = new GridBagConstraints();
        gbcRefreshButton.gridx = 2;
        gbcRefreshButton.gridy = 0;
        gbcRefreshButton.anchor = GridBagConstraints.EAST;
        gbcRefreshButton.insets = new Insets(0, 0, 10, 10);
        portPanel.add(refreshButton, gbcRefreshButton);

        GridBagConstraints gbcConnectButton = new GridBagConstraints();
        gbcConnectButton.gridx = 3;
        gbcConnectButton.gridy = 0;
        gbcConnectButton.anchor = GridBagConstraints.WEST;
        gbcConnectButton.insets = new Insets(0, 0, 10, 10);
        portPanel.add(connectButton, gbcConnectButton);

        GridBagConstraints gbcSentValueLabel = new GridBagConstraints();
        gbcSentValueLabel.gridx = 0;
        gbcSentValueLabel.gridy = 1;
        gbcSentValueLabel.anchor = GridBagConstraints.EAST;
        gbcSentValueLabel.insets = new Insets(0, 0, 10, 10);
        portPanel.add(sentValueLabel, gbcSentValueLabel);

        GridBagConstraints gbcTemperatureField = new GridBagConstraints();
        gbcTemperatureField .gridx = 1;
        gbcTemperatureField .gridy = 2;
        gbcTemperatureField .anchor = GridBagConstraints.WEST;
        gbcTemperatureField .insets = new Insets(0, 0, 10, 10);
        portPanel.add(temperatureField, gbcTemperatureField );

        GridBagConstraints gbcValiderButton = new GridBagConstraints();
        gbcValiderButton.gridx = 2;
        gbcValiderButton.gridy = 2;
        gbcValiderButton.anchor = GridBagConstraints.WEST;
        gbcValiderButton.insets = new Insets(0, 0, 10, 10);
        portPanel.add(validerButton, gbcValiderButton);

        GridBagConstraints gbcPortPanel = new GridBagConstraints();
        gbcPortPanel.gridx = 0;
        gbcPortPanel.gridy = 0;
        gbcPortPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPortPanel.weightx = 1.0;
        gbcPortPanel.insets = new Insets(0, 0, 10, 10);
        contentPanel.add(portPanel, gbcPortPanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        ImageIcon leftImageIcon = new ImageIcon("imagesminifrigo.jpg");
        JLabel leftImageLabel = new JLabel(leftImageIcon);

        ImageIcon leftdownImageIcon = new ImageIcon("variation.png");
        JLabel leftdownImageLabel = new JLabel(leftdownImageIcon);

        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setBackground(Color.lightGray);

        GridBagConstraints gbcLeftImageLabel = new GridBagConstraints();
        gbcLeftImageLabel.gridx = 0;
        gbcLeftImageLabel.gridy = 0;
        gbcLeftImageLabel.anchor = GridBagConstraints.NORTHWEST;
        gbcLeftImageLabel.weighty = 1;
        imagePanel.add(leftImageLabel, gbcLeftImageLabel);

        GridBagConstraints gbcLeftdownImageLabel = new GridBagConstraints();
        gbcLeftdownImageLabel.gridx = 0;
        gbcLeftdownImageLabel.gridy = 1;
        gbcLeftdownImageLabel.anchor = GridBagConstraints.NORTHWEST;
        gbcLeftdownImageLabel.weighty = 1;
        imagePanel.add(leftdownImageLabel, gbcLeftdownImageLabel);

        mainPanel.add(imagePanel, BorderLayout.WEST);

        valueButton = new JButton("Values");
        valueButton.setPreferredSize(new Dimension(200, 50));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        buttonPanel.add(valueButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);


        add(mainPanel);


        refreshButton.addActionListener(e -> refreshPortList());

        connectButton.addActionListener(e -> connectToArduino());

        validerButton.addActionListener(e -> Validate());

        model.Model.SerialReader serialReader = new model.Model.SerialReader();
        Thread readerThread = new Thread(serialReader);
        readerThread.start();
        humidityLabel = new JLabel("Humidité intérieure: ");
        indoorTemperatureLabel = new JLabel("Température intérieure: ");
        outdoorTemperatureLabel = new JLabel("Température extérieure: ");
        dewPointLabel = new JLabel("Point de rosée: ");

        JPanel dataPanel = new JPanel(new GridLayout(4, 2));
        dataPanel.add(humidityLabel);
        dataPanel.add(indoorTemperatureLabel);
        dataPanel.add(outdoorTemperatureLabel);
        dataPanel.add(dewPointLabel);

        valueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valuesInstance = new Values();
                valuesInstance.updateChartData(humidityValue, temperatureint, teemperatureext, point);
                valuesInstance.setVisible(true);
                // Close the current interface
                //dispose();
            }
        });


        // mainPanel.add(dataPanel, BorderLayout.CENTER);
    }

    private void refreshPortList() {
        portComboBox.removeAllItems();
        SerialPort[] ports = SerialPort.getCommPorts();
        for (SerialPort port : ports) {
            portComboBox.addItem(port.getSystemPortName());
        }
    }

    private void connectToArduino() {
        String selectedPortName = (String) portComboBox.getSelectedItem();
        selectedPort = SerialPort.getCommPort(selectedPortName);

        if (!temperatureField.getText().isEmpty()) {
            try {
                double temperatureValue = Double.parseDouble(temperatureField.getText());

                selectedPort.setComPortParameters(9600, 8, 1, 0);
                selectedPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

                if (selectedPort.openPort()) {
                    //JOptionPane.showMessageDialog(this, "Connexion établie avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    // Send the temperature setpoint to Arduino
                    try (OutputStream outputStream = selectedPort.getOutputStream();
                         OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
                        String temperatureString = String.valueOf(temperatureValue);
                        // Send the setpoint followed by a newline character
                        writer.write(temperatureString + "\n");
                        writer.flush();
                        System.out.println(temperatureString);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Impossible de se connecter au port série.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer une valeur numérique valide pour la température.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer une valeur pour la température.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        if (selectedPort.openPort()) {
            // Debug: Print information about the successful connection
            System.out.println("Connection established successfully!");

            // Debug: Print information about starting the SerialReader
            System.out.println("Starting SerialReader thread...");

            //double temperatureValue = Double.parseDouble(temperatureField.getText());
            JOptionPane.showMessageDialog(this, "Connexion établie avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            model.Model.SerialReader serialReader = new model.Model.SerialReader();
            new Thread(serialReader).start();
        } else {
            JOptionPane.showMessageDialog(this, "Impossible de se connecter au port série.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
    }

    private void Validate() {
        if (!temperatureField.getText().isEmpty()) {
            try {
                double temperatureValue = Double.parseDouble(temperatureField.getText());

                selectedPort.setComPortParameters(9600, 8, 1, 0);
                selectedPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

                if (selectedPort.openPort()) {
                    //JOptionPane.showMessageDialog(this, "Connexion établie avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    // Send the temperature setpoint to Arduino
                    try (OutputStream outputStream = selectedPort.getOutputStream();
                         OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
                        String temperatureString = String.valueOf(temperatureValue);
                        // Send the setpoint followed by a newline character
                        writer.write(temperatureString + "\n");
                        writer.flush();
                        System.out.println(temperatureString);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Impossible de se connecter au port série.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer une valeur numérique valide pour la température.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer une valeur pour la température.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    private class SerialReader implements Runnable {
        @Override
        public void run() {
            if (selectedPort == null) {
                System.out.println("Le port sélectionné a été initialisé.");
                return;
            }

            try (InputStream inputStream = selectedPort.getInputStream();
                 Scanner scanner = new Scanner(inputStream)) {

                while (scanner.hasNextLine()) {
                    String receivedData = scanner.nextLine();
                    SwingUtilities.invokeLater(() -> processArduinoData(receivedData));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void showAlert(String message) {
        SwingUtilities.invokeLater(() -> {
            int result = JOptionPane.showConfirmDialog(this, message, "Alerte", JOptionPane.DEFAULT_OPTION);

            //if OK is pressed the application is closed
            if(result == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });
    }

    private void high(String message) {
        SwingUtilities.invokeLater(() -> {
            int result = JOptionPane.showConfirmDialog(this, message, "Alerte", JOptionPane.DEFAULT_OPTION);

            //if OK is pressed the application is closed
            if(result == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });
    }


    private void processArduinoData(String data) {
        String[] values = data.split(",");
        //float humidityValue = 0;
        //float temperatureint=0;
        //float teemperatureext = 0;
        //float point=0;
        float temperatureValue = (float) Double.parseDouble(temperatureField.getText());
        if (values.length >= 2) {
            try {
                humidityValue = Float.parseFloat(values[0]);
                temperatureint = Float.parseFloat(values[1]);
                teemperatureext = Float.parseFloat(values[2]);
                point = Float.parseFloat(values[3]);

                humidityValues.add(humidityValue);
                System.out.println(humidityValues);
                temperatureintvalues.add(temperatureint);
                System.out.println(temperatureintvalues);
                temperatureextValues.add(teemperatureext);
                System.out.println(temperatureextValues);
                pointValues.add(point);
                System.out.println(pointValues);

                valuesInstance.updateHumidityLabel(String.valueOf(humidityValue));
                valuesInstance.updateIndoorTemperatureLabel(String.valueOf(temperatureint));
                valuesInstance.updateOutdoorTemperatureLabel(String.valueOf(teemperatureext));
                valuesInstance.updateDewPointLabel(String.valueOf(point));

                graphFrame.updateChartData(humidityValue, temperatureint, teemperatureext, point, temperatureValue);

            } catch (NumberFormatException e) {
                System.err.println("Erreur de conversion de la valeur : " + data);
            }
        }
        // valuesInstance.updateHumidityLabel(String.valueOf(humidityValue));
        // valuesInstance.updateIndoorTemperatureLabel(String.valueOf(temperatureint));
        //valuesInstance.updateOutdoorTemperatureLabel(String.valueOf(teemperatureext));
        //valuesInstance.updateDewPointLabel(String.valueOf(point));
        valuesInstance.updateChartData(humidityValue, temperatureint, teemperatureext, point);
        // ...

        if (temperatureint <= point && !isAlertShown) {
            showAlert("ALERTE RISQUE DE CONDENSATION !");
            isAlertShown = true; // l'alerte a été affichée
        } else if (temperatureint > point) {
            isAlertShown = false;
        }
        //counter ++;

        if(temperatureint > 25 && !eleve) {
            high("ALERTE TEMPERATURE ELEVE !");
            eleve = true; // l'alerte a été affichée
        } else if (temperatureint < 25) {
            eleve = false;
        }
    }}
