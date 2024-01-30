import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

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

        addGuiComponents();
    }

    private void addGuiComponents(){
        //search field
        JTextField searchTextField = new JTextField();

        //set the location and size of our component
        searchTextField.setBounds(15,15,351,45);

        //change the font style and size
        searchTextField.setFont(new Font("Dialog",Font.PLAIN,24));

        add(searchTextField);

        //search button
        JButton searchButton = new JButton(loadImage("src/assets/search.png"));

        //change the cursor to a hand cursor when hovering over
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375,13,47,45);
        add(searchButton);
    }

    //used to create image in our GUI components
    private ImageIcon loadImage(String resourcePath){
        try {
            //read the image file from the path
            BufferedImage image = ImageIO.read(new File(resourcePath));

            //return an image icon to render
            return new ImageIcon(image);
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Could not find resource");
        return null;
    }
}
