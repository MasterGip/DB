import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mg on 14.12.14.
 */
public class MenuPanel extends JPanel {

    JButton btn_refresh;
    JButton btn_addScores;
    JButton btn_removeScores;

    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Refresh")){
                Main.mf = new MainFrame();
            }else{

            }
        }
    }


    MenuPanel(){
        super();

    }
}
