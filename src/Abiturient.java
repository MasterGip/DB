import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

/**
 * Created by mg on 11.12.14.
 */
public class Abiturient extends JPanel {
//    boolean isSpecial;
    Abiturient ab;
    private T_FListener focusListener;
    public JTextField first_name;
    public JTextField last_name;
    public JTextField patronymic;
    public JTextField birthdate;
    public JTextField passport_number;
    public JButton btn;
    public JButton btn_addToList;
    public JCheckBox payment;

    class ButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Add")){
                try {
                    String f_n = first_name.getText();
                    String l_n = last_name.getText();
                    String patr = patronymic.getText();
                    String b_d = birthdate.getText();
                    String p_n = passport_number.getText();

                    WorkWithDB.insertIntoAbiturients(f_n, l_n, patr, b_d, p_n, payment.isSelected());
                    Abiturient abiturient = new Abiturient(f_n, l_n, patr, b_d, p_n, payment.isSelected());


                    Main.mf.abiturientsPanel.addAbiturient(abiturient);
                    first_name.setText("");
                    last_name.setText("");
                    patronymic.setText("");
                    birthdate.setText("");
                    passport_number.setText("");
                    payment.setSelected(false);

                }catch(Exception exp){
                    //exp.printStackTrace();
                    JOptionPane.showMessageDialog(Main.mf, "Ошибка вставки!");
                }
                Main.mf.revalidate();
            }else{

                try {

                    String p_n = passport_number.getText();
                    WorkWithDB.deleteFromAbiturient(p_n);


                    Main.mf.abiturientsPanel.panel.remove(ab);

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
//                System.out.println("in");
                if(!value.equals(newValue)) {
//                    System.out.println("m");
                    String p_n = passport_number.getText();
                    if (lastField.equals(first_name)) {
                        WorkWithDB.updateAbiturient(p_n, newValue, WorkWithDB.FIRST_NAME);
                    } else {
                        if (lastField.equals(last_name)) {
                            WorkWithDB.updateAbiturient(p_n, newValue, WorkWithDB.LAST_NAME);
                        } else {
                            if (lastField.equals(patronymic)) {
                                WorkWithDB.updateAbiturient(p_n, newValue, WorkWithDB.PATRONYMIC);
                            } else {
                                if (lastField.equals(birthdate)) {
                                    WorkWithDB.updateAbiturient(p_n, newValue, WorkWithDB.BIRTHDATE);
                                } else {
                                    if (lastField.equals(passport_number)) {
//                                        System.out.println("mmm");
                                        WorkWithDB.updateAbiturient(value, newValue, WorkWithDB.PASSPORT_NUMBER);
                                    }
                                }
                            }
                        }
                    }
                }
            }catch(Exception exc){
                exc.printStackTrace();
                if(lastField.equals(birthdate)){
                    JOptionPane.showMessageDialog(Main.mf, "Дата должна быть в формате ГГГГ-ММ-ЧЧ");
                }else{
                    if(lastField.equals(passport_number)){
                        JOptionPane.showMessageDialog(Main.mf, "Номер паспорта должен быть уникальным и состоять из " +
                                "10 цифр");
                    }else{
                        JOptionPane.showMessageDialog(Main.mf, "Неудача");
                    }
                }
                lastField.setText(value);
            }
        }
    }

    class T_FListener implements FocusListener{

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
            String v = value;

            try {

                String newValue = ((JTextField)e.getComponent()).getText();
                if(!value.equals(newValue)) {
                    String p_n = passport_number.getText();
                    if (lastField.equals(first_name)) {
                        WorkWithDB.updateAbiturient(p_n, newValue, WorkWithDB.FIRST_NAME);
                    } else {
                        if (lastField.equals(last_name)) {
                            WorkWithDB.updateAbiturient(p_n, newValue, WorkWithDB.LAST_NAME);
                        } else {
                            if (lastField.equals(patronymic)) {
                                WorkWithDB.updateAbiturient(p_n, newValue, WorkWithDB.PATRONYMIC);
                            } else {
                                if (lastField.equals(birthdate)) {
                                    WorkWithDB.updateAbiturient(p_n, newValue, WorkWithDB.BIRTHDATE);
                                } else {
                                    if (lastField.equals(passport_number)) {
                                        WorkWithDB.updateAbiturient(value, newValue, WorkWithDB.PASSPORT_NUMBER);
                                    }
                                }
                            }
                        }
                    }
                }
            }catch(Exception exc){
                //exc.printStackTrace();
                ((JTextField)(e.getComponent())).setText(value);
                if(e.getComponent().equals(birthdate)){
                    JOptionPane.showMessageDialog(Main.mf, "Дата должна быть в формате ГГГГ-ММ-ЧЧ");
                }else{
                    if(e.getComponent().equals(passport_number)){
                        JOptionPane.showMessageDialog(Main.mf, "Номер паспорта должен быть уникальным и состоять из " +
                                "10 цифр");
                    }else{
                        JOptionPane.showMessageDialog(Main.mf, "Неудача");
                    }
                }
                ((JTextField)(e.getComponent())).setText(v);
            }
//            System.ou/t.println("fL");
        }
    }

    class AddToListListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(btn_addToList.getText().equals("<")){
                btn_addToList.setText("*");
//                System.out.println("!!!");
            }else{
                btn_addToList.setText("<");
            }
        }
    }

    public static Abiturient createSpecialAbiturient(){
        Abiturient abiturient = new Abiturient();
//        abiturient.isSpecial = true;
        return abiturient;
    }


    private Abiturient(){
//        isSpecial = true;
        first_name = new JTextField();
        last_name = new JTextField();
        patronymic = new JTextField();
        birthdate = new JTextField();
        passport_number = new JTextField();
        payment = new JCheckBox();
        payment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WorkWithDB.updateAbiturient(passport_number.getText(),payment.isSelected() + "", WorkWithDB.PAYMENT
                    );
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(Main.mf, "Изменение не удалось");
                    payment.setSelected(!payment.isSelected());
                }
            }
        });
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btn = new JButton("+");
        panel.add(btn);
        panel.add(new JLabel(" "));


        ab = this;

        btn.addActionListener(new ButtonActionListener());
        first_name.setBackground(Color.RED);
        last_name.setBackground(Color.RED);
        patronymic.setBackground(Color.RED);
        birthdate.setBackground(Color.RED);
        passport_number.setBackground(Color.RED);
        btn.setActionCommand("Add");

        this.setLayout(new GridLayout(1, 7));
        this.add(last_name);
        this.add(first_name);
        this.add(patronymic);
        this.add(birthdate);
        this.add(passport_number);
        this.add(payment);
        this.add(panel);



    }

    Abiturient(String f_n, String l_n, String patr, String b_date, String p_n, boolean pay){

        first_name = new JTextField(f_n);
        last_name = new JTextField(l_n);
        patronymic = new JTextField(patr);
        birthdate = new JTextField(b_date);
        passport_number = new JTextField(p_n);
        payment = new JCheckBox();
        payment.setSelected(pay);
//        btn = new JButton("-");
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btn = new JButton("-");
        btn_addToList = new JButton("<");
        btn_addToList.addActionListener(new AddToListListener());
        panel.add(btn);
        panel.add(btn_addToList);

        ab = this;

//        this.setSize(100,10);
        focusListener = new T_FListener();
        t_f_Action action = new t_f_Action();
        first_name.addFocusListener(focusListener);
        last_name.addFocusListener(focusListener);
        patronymic.addFocusListener(focusListener);
        birthdate.addFocusListener(focusListener);
        passport_number.addFocusListener(focusListener);
        btn.addActionListener(new ButtonActionListener());
        btn.setActionCommand("Remove");
        first_name.addActionListener(action);
        last_name.addActionListener(action);
        patronymic.addActionListener(action);
        birthdate.addActionListener(action);
        passport_number.addActionListener(action);
        payment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    WorkWithDB.updateAbiturient(passport_number.getText(),payment.isSelected() + "", WorkWithDB.PAYMENT
                            );
                } catch (Exception e1) {
                    payment.setSelected(!payment.isSelected());
                    JOptionPane.showMessageDialog(Main.mf, "Изменение не удалось");
                }
            }
        });

        this.setLayout(new GridLayout(1, 7));
        this.add(last_name);
        this.add(first_name);
        this.add(patronymic);
        this.add(birthdate);
        this.add(passport_number);
        this.add(payment);
        this.add(panel);
//        this.setPreferredSize(new Dimension(300, 33));
//        System.out.println(passport_number.getWidth() + " " + passport_number.getHeight());
    }


}
