package Controller;
import java.io.StringWriter;
import java.util.Observable;
import java.util.Observer;

public class DataObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            String receivedData = (String) arg;
            StringWriter dataTextArea = new StringWriter();
            dataTextArea.append(receivedData + "\n");
        }
    }
}
