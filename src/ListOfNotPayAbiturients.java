import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mg on 16.12.14.
 */
public class ListOfNotPayAbiturients extends JFrame {

    ListOfNotPayAbiturients(ArrayList<ArrayList<String>> listOfAbiturients){
        super();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        if(listOfAbiturients!=null){
            JPanel panel = new JPanel();
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 0;
            c.insets = new Insets(0, 0, 0, 70);
            c.gridy = 0;
            c.ipadx = 0;
            c.ipady = 0;
            c.weightx = 0.0;
            c.weighty = 0.0;
            GridBagLayout g_l = new GridBagLayout();
            panel.setLayout(g_l);

            JPanel panelOfAbiturient = new JPanel(new GridLayout(1, 4));
            panelOfAbiturient.add(new JLabel("Паспорт:"));
            panelOfAbiturient.add(new JLabel("Фамилия:"));
            panelOfAbiturient.add(new JLabel("Имя:"));
            panelOfAbiturient.add(new JLabel("Сумма баллов:"));

            g_l.setConstraints(panelOfAbiturient, c);
            panel.add(panelOfAbiturient);
            c.gridy = GridBagConstraints.RELATIVE;
            for(int i = 0; i < listOfAbiturients.size(); i++){
                panelOfAbiturient = new JPanel(new GridLayout(1, 4));
                panelOfAbiturient.add(new JLabel(listOfAbiturients.get(i).get(0)));
                panelOfAbiturient.add(new JLabel(listOfAbiturients.get(i).get(1)));
                panelOfAbiturient.add(new JLabel(listOfAbiturients.get(i).get(2)));
                panelOfAbiturient.add(new JLabel(listOfAbiturients.get(i).get(3)));

                g_l.setConstraints(panelOfAbiturient, c);
                panel.add(panelOfAbiturient);
            }
            JOptionPane pane = new JOptionPane(panel);
            this.add(pane, BorderLayout.CENTER);

        }
        this.setVisible(true);
    }
}
