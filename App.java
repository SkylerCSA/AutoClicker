import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class App extends JFrame implements ActionListener, KeyListener {
    JButton button;
    JTextField text;
    Scanner scanner;
    JLabel label;
    int tClicks;
    boolean stopAutoclicker;
    long lastKeyPressTime;

    App() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        button = new JButton("Enter");


        button.addActionListener(this);

        //make a label that displays the total number of clicks
        label = new JLabel("Clicks:  0", SwingConstants.RIGHT);

        //add a key lister for f in the frame
        text = new JTextField(10);

        text = new JTextField();
        text.setPreferredSize(new Dimension(250, 40));
        text.addKeyListener(this); // registers the KeyListener to the JTextField component
        this.add(button);
        this.add(text);
        this.add(label);
        this.pack();
        this.setVisible(true);

        scanner = new Scanner(System.in);
        stopAutoclicker = false;
        lastKeyPressTime = System.currentTimeMillis();
    }

    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button) {
                int clicks = Integer.parseInt(text.getText());
                autoclicker(clicks);
            }
        
    }


    public void autoclicker(int clicks) {
        
        try {
            Robot robot = new Robot();
            for (int i = 0; i < clicks; i+=2) {
                Thread.sleep(40);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(40); // stops for 40 miliseconds
                tClicks++;
                label.setText("Clicks: " + Integer.toString(tClicks));
            }
        } catch (AWTException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastKeyPressTime > 10000) { // checks if the 'f' key has been pressed in the last second
                stopAutoclicker = true;
                lastKeyPressTime = currentTime;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }
}
