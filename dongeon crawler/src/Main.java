import javax.swing.*;

public class Main {
    JFrame displayZoneFrame;

    public Main() throws Exception{
        displayZoneFrame= new JFrame("Java Labs");
        displayZoneFrame.setSize(400,600);
        displayZoneFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        displayZoneFrame.setVisible(true);
    }
    public static void main (String[] args) throws Exception {
        Main main = new Main();
    }
}
