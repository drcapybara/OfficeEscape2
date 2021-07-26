package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import static controller.PropertyChangeEnabledUserControls.*;

/**
 * Represents the console panel to the right of the game screen. Player
 * interacts with the console to answer trivia questions and see information
 * about the game state such as current room, etc.
 * @author Dustin Ray
 * @version Summer 2021
 */
public class ConsolePanel extends JPanel implements PropertyChangeListener {


    /** Area to display text output.  */
    JTextArea myConsoleScreenTextArea;
    /** Graphics to decorate text area. */
    BufferedImage myDisplayConsole;


    /**
     * Constructor for class.
     * @throws IOException If any resources cannot be loaded.
     * @throws FontFormatException if font cannot be loaded.
     */
    public ConsolePanel() throws IOException, FontFormatException {
        myDisplayConsole = ImageIO.read(new File("src/res/assets/console.png"));
        this.setLayout(null);
        setupText();
    }

    /**
     * Draws graphics to panel.
     * @param g the graphics to draw to the panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D displayConsole = (Graphics2D) g;
        displayConsole.drawImage(this.myDisplayConsole, 768, 0, null);
    }

    /**
     * Changes state of panel when property change fired.
     * @param theEvent is the event to listen for.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        switch (theEvent.getPropertyName()) {
            case PROPERTY_PROXIMITY_DOOR_A -> {
                myConsoleScreenTextArea.setText("Property change fired: " + "\n" + "next to door A");
            }
            case PROPERTY_PROXIMITY_DOOR_B -> {
                myConsoleScreenTextArea.setText("Property change fired: " + "\n" + "next to door B");
            }
            case PROPERTY_PROXIMITY_DOOR_C -> {
                myConsoleScreenTextArea.setText("Property change fired: " + "\n" + "next to door C");
            }
            case PROPERTY_PROXIMITY_DOOR_D -> {
                myConsoleScreenTextArea.setText("Property change fired: " + "\n" + "next to door D");
            }
            case PROPERTY_PROXIMITY_NO_DOOR -> {
                myConsoleScreenTextArea.setText("not near any door");
            }
        }
    }

    /**
     * Sets up text elements in panel.
     * @throws IOException if class resource cannot be loaded.
     * @throws FontFormatException if font cannot be loaded.
     */
    public void setupText() throws IOException, FontFormatException {

        Font fontTest = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/expansiva/Expansiva.otf"));
        fontTest = fontTest.deriveFont(Font.PLAIN, 18);
        myConsoleScreenTextArea = new JTextArea("this is a test...");
        myConsoleScreenTextArea.setVisible(true);
        myConsoleScreenTextArea.setForeground(Color.WHITE);
        myConsoleScreenTextArea.setBackground(Color.BLACK);
        myConsoleScreenTextArea.setBounds(830, 50, 360, 245);
        myConsoleScreenTextArea.setLayout(null);
        myConsoleScreenTextArea.setFont(fontTest);
        this.add(myConsoleScreenTextArea);

    }

}
