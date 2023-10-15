import javax.swing.*;

public class GameFrame extends JFrame {
     GameFrame(){
        GamePanel panel = new GamePanel();
         ImageIcon logo = new ImageIcon("SnakeLogo.png");

        this.setIconImage(logo.getImage());
        this.add(panel); // adding panel inside frame
        this.setTitle(" A legend of Snake");  // setting title of the Application
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // close button on frame

        this.setResizable(true); // default no to resizing the frame
        this.setVisible(true); // makes the frame visible
        this.pack();
        this.setLocationRelativeTo(null); // opens the screen in the middle of the display if "null"

     }
}

