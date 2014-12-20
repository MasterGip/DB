import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by mg on 11.12.14.
 */
public class MainFrame extends JFrame {

    public JPanel menuPanel;


    public AddScoreFrame addScoreFrame;
    public AbiturientsPanel abiturientsPanel;
    public SubjectsPanel subjectsPanel;
    public ListOfAbiturients listOfAbiturients;
    public ListOfNotPayAbiturients listOfNotPayAbiturients;
    public JTextField tf_numberOfAbiturients;
    public JTextField tf_points;

    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Add")){
                ArrayList<ArrayList<String>> listOfPerson = new ArrayList<ArrayList<String>>();

                ArrayList<Abiturient> abs = abiturientsPanel.abiturients;
//                System.out.println(abs.size());
                for(int i = 1; i < abs.size(); i++){
//                    System.out.println(abs.get(i).btn_addToList.getText());
                    if(abs.get(i).btn_addToList.getText().equals("*")){
//                        System.out.println("trap");
                        ArrayList<String> personData = new ArrayList<String>();
                        personData.add(abs.get(i).passport_number.getText());
                        personData.add(abs.get(i).first_name.getText());
                        personData.add(abs.get(i).last_name.getText());
                        listOfPerson.add(personData);
                        abs.get(i).btn_addToList.setText("<");
                    }
                }
                ArrayList<String> listOfExams = new ArrayList<String>();
                ArrayList<Subject> scrs = subjectsPanel.listOfSubjects;
                for(int i = 1; i < scrs.size(); i++){
                    if(scrs.get(i).btn_addToList.getText().equals("*")){
//                        System.out.println("trap");
                        listOfExams.add(scrs.get(i).exam.getText());
                        scrs.get(i).btn_addToList.setText(">");
                    }
                }
                addScoreFrame = new AddScoreFrame(listOfPerson, listOfExams);
            }else{
                if(e.getActionCommand().equals("Delete")){
                    ArrayList<String> listOfPerson = new ArrayList<String>();

                    ArrayList<Abiturient> abs = abiturientsPanel.abiturients;
//                    System.out.println(abs.size());
                    for(int i = 1; i < abs.size(); i++){
//                        System.out.println(abs.get(i).btn_addToList.getText());
                        if(abs.get(i).btn_addToList.getText().equals("*")){
//                            System.out.println("trap");

                            listOfPerson.add(abs.get(i).passport_number.getText());


                            abs.get(i).btn_addToList.setText("<");
                        }
                    }
                    ArrayList<String> listOfExams = new ArrayList<String>();
                    ArrayList<Subject> scrs = subjectsPanel.listOfSubjects;

                    for(int i = 1; i < scrs.size(); i++){
                        if(scrs.get(i).btn_addToList.getText().equals("*")){
//                            System.out.println("trap");
                            listOfExams.add(scrs.get(i).exam.getText());
                            scrs.get(i).btn_addToList.setText(">");
                        }
                    }

                    for(int i = 0; i < listOfPerson.size(); i++){
                        for(int j = 0; j < listOfExams.size(); j++){
                            try {
                                WorkWithDB.deleteScore(listOfPerson.get(i), listOfExams.get(j));
                            }catch (Exception ex){

                            }
                        }
                    }

                }else{
                    if(e.getActionCommand().equals("getAbiturients")){
                        try {
                            ArrayList<ArrayList<String>> list =
                                    WorkWithDB.getListOfAbiturients(new Integer(tf_numberOfAbiturients.getText()));
                            listOfAbiturients = new ListOfAbiturients(list);
                        }catch (Exception exp){
                            JOptionPane.showMessageDialog(Main.mf.listOfAbiturients, "Ошибка");
                        }
                    }else{
                        if(e.getActionCommand().equals("getPayAbiturients")) {
                            try {
                                ArrayList<ArrayList<String>> list =
                                        WorkWithDB.getListOfNotPayAbiturients(new Integer(tf_points.getText()));
                                listOfNotPayAbiturients = new ListOfNotPayAbiturients(list);
                            } catch (Exception exp) {
                                JOptionPane.showMessageDialog(Main.mf.listOfNotPayAbiturients, "Ошибка");
                            }
                        }else{
                            if(e.getActionCommand().equals("Refresh")) {
                                Abiturient[] abiturients = WorkWithDB.getAllAbiturientsFromDatabase();

                                Subject[] subjects = WorkWithDB.getAllExamsFromDatabase();
//                            System.out.println(abiturients.length  + " " + subjects.length);
                                if (abiturients != null && subjects != null) {

                                    //System.out.println(abiturients[0]);
                                    abiturientsPanel.refresh();
                                    subjectsPanel.refresh();
                                    revalidate();
                                }
                            }else{
                                if(e.getActionCommand().equals("DeleteAbiturients")){
                                    try{
                                        WorkWithDB.deleteFromAbiturients();
                                        Main.mf.abiturientsPanel = new AbiturientsPanel();
                                    }catch (Exception e1) {
                                        JOptionPane.showMessageDialog(Main.mf, "Ошибка");
                                    }
                                    abiturientsPanel.refresh();
                                    revalidate();
                                }else{
                                    try{
                                        WorkWithDB.deleteFromExams();
                                        Main.mf.subjectsPanel = new SubjectsPanel();
                                    }catch (Exception e1) {
                                        JOptionPane.showMessageDialog(Main.mf, "Ошибка");
                                    }
                                    subjectsPanel.refresh();
                                    revalidate();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    MainFrame(){
        super();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1000, 500);
//        Abiturient abiturient = new Abiturient("asda","asdas","asdasd","asdasd","asdasd");



        //String s = "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww";
        //p.setText(s);
        //pan.add(p);
        Abiturient[] abiturients = WorkWithDB.getAllAbiturientsFromDatabase();

        Subject[] subjects = WorkWithDB.getAllExamsFromDatabase();
//        System.out.println(abiturients.length  + " " + subjects.length);
        if(abiturients!=null && subjects!=null) {

            //System.out.println(abiturients[0]);
            abiturientsPanel = new AbiturientsPanel(abiturients);
            subjectsPanel = new SubjectsPanel(subjects);
            menuPanel = new JPanel(new GridLayout(1,6));
            ButtonListener b_l = new ButtonListener();

            JButton btn_addScore = new JButton("+ выделенное");
            btn_addScore.setActionCommand("Add");
            btn_addScore.addActionListener(b_l);
            JButton btn_deleteScore = new JButton("- выделенное");
            btn_deleteScore.setActionCommand("Delete");
            btn_deleteScore.addActionListener(b_l);
//            JButton btn_refresh = new JButton("Обновить");
//            btn_refresh.setActionCommand("Refresh");
//            btn_refresh.addActionListener(b_l);
//            JButton btn_deleteAb = new JButton("Удалить Абитуриентов");
//            btn_deleteAb.setActionCommand("DeleteAbituriients");
//            btn_deleteAb.addActionListener(b_l);
//            JButton btn_deleteEx = new JButton("Удалить экзамены");
//            btn_deleteEx.setActionCommand("DeleteExams");
//            btn_deleteEx.addActionListener(b_l);
            tf_numberOfAbiturients = new JTextField("Число абитуриентов");
            JButton btn_getAbiturients = new JButton("Список 1");
            btn_getAbiturients.setActionCommand("getAbiturients");
            btn_getAbiturients.addActionListener(b_l);
            tf_points = new JTextField("Балл");

            JButton btn_getPayAb = new JButton("Список 2");
            btn_getPayAb.setActionCommand("getPayAbiturients");
            btn_getPayAb.addActionListener(b_l);

           // menuPanel.add(btn_refresh);
            menuPanel.add(btn_addScore);
            menuPanel.add(btn_deleteScore);
            //menuPanel.add(btn_deleteAb);
            //menuPanel.add(btn_deleteEx);
            menuPanel.add(tf_numberOfAbiturients);
            menuPanel.add(btn_getAbiturients);
            menuPanel.add(tf_points);
            menuPanel.add(btn_getPayAb);
//        abiturientsPanel.add(new Abiturient("!", "!", "!", "!", "!"));
//        abiturientsPanel.add(new Abiturient("!", "!", "!", "!", "!"));
            this.add(abiturientsPanel, BorderLayout.WEST);
            this.add(new JLabel(" 00"), BorderLayout.CENTER);
            this.add(subjectsPanel, BorderLayout.EAST);
            this.add(menuPanel, BorderLayout.SOUTH);
        }else{
            JOptionPane.showMessageDialog(this, "Что-то не так!!!!");

        }

        this.setVisible(true);
//        System.out.println(abiturient.getWidth() + " " + abiturient.getHeight());
    }
}
