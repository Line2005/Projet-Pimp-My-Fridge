package Controller;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MyObserver implements Observer {
    private JLabel label;

    public MyObserver(JLabel label) {
        this.setLabel(label);
    }

    /**
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     *            method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Color) {
            Color newColor = (Color) arg;
            getLabel().setForeground(newColor);
            getLabel().setText("Color changed!");
        }
    }


    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
