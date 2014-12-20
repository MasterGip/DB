import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by mg on 14.12.14.
 */
public class Subject extends JPanel {

    public T_FListener focusListener;
    public Subject subject;
    public JTextField exam;
    public JButton btn;
    public JButton btn_addToList;

    class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Add")){
                try {
                    String e_n = exam.getText();

                    WorkWithDB.insertIntoExams(e_n);
                    Subject subject = new Subject(e_n);


                    Main.mf.subjectsPanel.addSubject(subject);
                    exam.setText("");

                }catch(Exception exp){
                    //exp.printStackTrace();
                    JOptionPane.showMessageDialog(Main.mf, "Ошибка вставки!");
                }
                Main.mf.revalidate();
            }else{

                try {

                    String e_n = exam.getText();
                    WorkWithDB.deleteFromExams(e_n);


                    Main.mf.subjectsPanel.panel.remove(subject);

                }catch(Exception exp){
                    //exp.printStackTrace();
                    JOptionPane.showMessageDialog(Main.mf, "Ошибка удаления!");
                }
                Main.mf.revalidate();

            }
        }
    }


    class t_f_Action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ev) {
            T_FListener listener = focusListener;
            String value = listener.value;
            JTextField lastField = listener.lastField;
            try {

                String newValue = lastField.getText();
                if(!value.equals(newValue)) {


                        WorkWithDB.updateExams(value, newValue);

                }
            }catch(Exception exc){
                //exc.printStackTrace();

                JOptionPane.showMessageDialog(Main.mf, "Неудача");

                lastField.setText(value);
            }
        }
    }

    class T_FListener implements FocusListener {

        JTextField lastField;
        String value;

        @Override
        public void focusGained(FocusEvent e) {
            value = ((JTextField)(e.getComponent())).getText();
            lastField = (JTextField)(e.getComponent());
        }

        @Override
        public void focusLost(FocusEvent e) {
            //UPGRADE DATABASE

            try {
                String newValue = ((JTextField)e.getComponent()).getText();

                    WorkWithDB.updateExams(value, newValue);


            }catch(Exception exc){

                        JOptionPane.showMessageDialog(Main.mf, "Неудача");

                        ((JTextField)(e.getComponent())).setText(value);
            }
//            System.ou/t.println("fL");
        }
    }

    class AddToListListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(btn_addToList.getText().equals(">")){
                btn_addToList.setText("*");
//                System.out.println("!!!");
            }else{
                btn_addToList.setText(">");
            }
        }
    }

    public static Subject createSpecialSubject(){
        Subject subject1 = new Subject();
//        abiturient.isSpecial = true;
        return subject1;
    }


    private Subject(){
//        isSpecial = true;
        exam = new JTextField();

        JPanel panel = new JPanel(new GridLayout(1, 2));
        btn = new JButton("+");

        panel.add(new JLabel(" "));
        panel.add(btn);

        subject = this;

        btn.addActionListener(new ButtonActionListener());

        exam.setBackground(Color.RED);
        btn.setActionCommand("Add");

        this.setLayout(new GridLayout(1, 2));

        this.add(panel);
        this.add(exam);



    }

    Subject(String e_n){

        exam = new JTextField(e_n);
//        btn = new JButton("-");
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btn_addToList = new JButton(">");
        btn = new JButton("-");

        btn_addToList.addActionListener(new AddToListListener());
        panel.add(btn_addToList);
        panel.add(btn);


        subject = this;

//        this.setSize(100,10);
        focusListener = new T_FListener();
        t_f_Action action = new t_f_Action();
        exam.addActionListener(action);

        exam.addFocusListener(focusListener);
        btn.addActionListener(new ButtonActionListener());
        btn.setActionCommand("Remove");

        this.setLayout(new GridLayout(1, 6));

        this.add(panel);
        this.add(exam);
//        this.setPreferredSize(new Dimension(300, 33));
        //System.out.println(passport_number.getWidth() + " " + passport_number.getHeight());
    }
}
