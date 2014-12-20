import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by mg on 15.12.14.
 */
public class ScoresPanel extends JScrollPane {
    private static JPanel help;
    GridBagConstraints c;
    GridBagLayout g_l;
    ArrayList<Score> listOfScores;
    JPanel panel;
    JButton btn_add;

    private static JPanel initialize(){
        help = new JPanel();
        return help;
    }

    class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean b = false;
            for(int i = 0; i < listOfScores.size(); i++){
                Score score = listOfScores.get(i);
                try {
                    if (score.wasInDatabase) {
                        WorkWithDB.updateScore(score.passport_number.getText(), score.exam.getText(),
                                new Integer(score.score.getText()));
                    } else {
                        WorkWithDB.insertScore(score.passport_number.getText(), score.exam.getText(),
                                new Integer(score.score.getText()));
                    }
                }catch (Exception ex){
                    b = true;
                }
            }
            if(b){
                JOptionPane.showMessageDialog(Main.mf.addScoreFrame, "Ошибка!");
            }

        }
    }

    ScoresPanel(){
        super(initialize());
        listOfScores = new ArrayList<Score>();
        panel = help;
        c = new GridBagConstraints();
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
        g_l = new GridBagLayout();
        panel.setLayout(g_l);
        JPanel panelForLabels = new JPanel(new GridLayout(1, 6));
        panelForLabels.add(new JLabel("Паспорт:"));
        panelForLabels.add(new JLabel("Фамилия"));
        panelForLabels.add(new JLabel("Имя:"));
        panelForLabels.add(new JLabel("Предмет"));
        panelForLabels.add(new JLabel("Число баллов"));
        panelForLabels.add(new JLabel(" "));
        panel.add(panelForLabels);
        c.gridy = GridBagConstraints.RELATIVE;
    }

    ScoresPanel(ArrayList<ArrayList<String>> passports, ArrayList<String> subjects){
        this();
        ArrayList<Score> scoreList = new ArrayList<Score>();
        for(int i = 0; i < passports.size(); i++){
            for(int j = 0; j < subjects.size(); j++){
                int scoreOfIAbiturient = -1;
                try{
                   scoreOfIAbiturient =  WorkWithDB.getScore(passports.get(i).get(0), subjects.get(j));
                }catch (Exception e){

                }

                if(scoreOfIAbiturient != -1){
                    Score score = new Score(passports.get(i).get(0),
                            passports.get(i).get(1), passports.get(i).get(2), subjects.get(j), scoreOfIAbiturient);
                    g_l.setConstraints(score, c);
                    listOfScores.add(score);
                    panel.add(score);
                }else{
                    Score score = new Score(passports.get(i).get(0),
                            passports.get(i).get(1), passports.get(i).get(2), subjects.get(j));
                    g_l.setConstraints(score, c);
                    listOfScores.add(score);
                    panel.add(score);
                }


            }
        }

        btn_add = new JButton("ADD");
        btn_add.addActionListener(new AddListener());
        g_l.setConstraints(btn_add, c);
        panel.add(btn_add);

    }



}
