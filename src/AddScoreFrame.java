import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mg on 15.12.14.
 */
public class AddScoreFrame extends JFrame {

    ScoresPanel panel;

    AddScoreFrame(ArrayList<ArrayList<String>> passports, ArrayList<String> subjects){
        super();
        this.setSize(700, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel = new ScoresPanel(passports, subjects);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

//    AddScoreFrame()
}
