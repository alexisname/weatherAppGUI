import javax.swing.*;

public class WeatherAppGUI extends JFrame {
    public WeatherAppGUI(){
        //add title
        super("Weather App");

        //configure GUI to end the program
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //set the size of our GUI
        setSize(450,650);

        //set GUI at center
        setLocationRelativeTo(null);

        //make layout manager null to manually position our components within the GUI
        setLayout(null);

        //prevent resize
        setResizable(false);
    }
}
