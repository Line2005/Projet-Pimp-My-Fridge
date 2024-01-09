package Controller;
import  model.Model;
import View.Values;
import View.GraphFrame;

import javax.swing.*;

public class Controller {

    public void initializeAndShowApp() {
        SwingUtilities.invokeLater(() -> {
            Model app = new Model();
            app.valuesInstance = new Values(); // Initialiser valuesInstance ici
            //app.valuesInstance.setVisible(true);
            app.graphFrame = new GraphFrame();
            app.graphFrame.setVisible(true);
            app.setVisible(true);
        });
    }}

