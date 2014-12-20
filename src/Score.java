import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mg on 15.12.14.
 */
public class Score extends JPanel {
        public boolean wasInDatabase;
        Score this_score;
        JLabel passport_number;
        JLabel first_name;
        JLabel last_name;
        JLabel exam;
        JTextField score;
        JButton btn_delete;

        class DeleteFromForm implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = true;
                if(wasInDatabase) {
                    try {
                        WorkWithDB.deleteScore(passport_number.getText(), exam.getText());
                    }catch(Exception ex) {
                        JOptionPane.showMessageDialog(Main.mf.addScoreFrame, "Ошибка удаления");
                        b = false;
                    }
                }
                if(b) {
                    getParent().remove(this_score);
                    Main.mf.addScoreFrame.revalidate();
                }
            }
        }


        Score(String passport, String f_n, String l_n, String exam_name) {
            wasInDatabase = false;
            passport_number = new JLabel(passport);
            first_name = new JLabel(f_n);
            last_name = new JLabel(l_n);
            score = new JTextField();
            btn_delete = new JButton("-");
            btn_delete.addActionListener(new DeleteFromForm());
            exam = new JLabel(exam_name);
            this_score = this;
            this.setLayout(new GridLayout(1, 6));
            this.add(passport_number);
            this.add(last_name);
            this.add(first_name);
            this.add(exam);
            this.add(score);
            this.add(btn_delete);

        }

        Score(String passport, String f_n, String l_n, String exam_name, int s) {
            wasInDatabase = true;
            passport_number = new JLabel(passport);
            first_name = new JLabel(f_n);
            last_name = new JLabel(l_n);
            score = new JTextField(s + "");
            btn_delete = new JButton("-");
            btn_delete.addActionListener(new DeleteFromForm());
            exam = new JLabel(exam_name);
            this_score = this;
            this.setLayout(new GridLayout(1, 6));
            this.add(passport_number);
            this.add(last_name);
            this.add(first_name);
            this.add(exam);
            this.add(score);
            this.add(btn_delete);
        }




}
