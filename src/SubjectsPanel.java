import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mg on 14.12.14.
 */
public class SubjectsPanel extends JScrollPane {
    ArrayList<Subject> listOfSubjects;
    GridBagConstraints c;
    GridBagLayout g_l;
    JPanel panel;
    Subject addingSubject;
    private static JPanel helpPanel;



    SubjectsPanel(){
        super(initialize());
        listOfSubjects = new ArrayList<Subject>();
//        numberOfComponents = 0;

        g_l = new GridBagLayout();

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

        panel = helpPanel;
        panel.setLayout(g_l);


//        panel.setMaximumSize(new Dimension(this.getY(), Integer.MAX_VALUE ));
        this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        //System.out.println(this.getWidth());/
        //panel.setMaximumSize(new Dimension(this.getWidth(), 10000));
        JPanel panelForLabels = new JPanel(new GridLayout(1, 2));


        panelForLabels.add(new JLabel(" "));
        JLabel jLabel = new JLabel();
        jLabel.setFont(new Font("Times New Roman", Font.ITALIC, 11));
        jLabel.setText("Экзамен:");
        panelForLabels.add(jLabel);
        panelForLabels.setPreferredSize(panelForLabels.getMinimumSize());
        g_l.setConstraints(panelForLabels, c);

        panel.add(panelForLabels);
        c.gridy = GridBagConstraints.RELATIVE;

        addingSubject = Subject.createSpecialSubject();
        g_l.setConstraints(addingSubject, c);
        this.addSubject(addingSubject);
//        numberOfComponents = 2;
//        this.revalidate();


    }

    SubjectsPanel(Subject subject){
        this();
        this.addSubject(subject);



    }

    SubjectsPanel(Subject[] subjects){
        this();
//        Abiturient ab = (Abiturient)(this.panel.getComponent(numberOfComponents));
        this.panel.remove(addingSubject);
        for(int i = 0; i < subjects.length; i++) {
            g_l.setConstraints(subjects[i], c);
            this.panel.add(subjects[i]);
            this.listOfSubjects.add(subjects[i]);
//            numberOfComponents++;
        }
        g_l.setConstraints(addingSubject, c);
        this.panel.add(addingSubject);

    }

    private static JPanel initialize(){
        GridLayout g_l1 = new GridLayout(1,1);
        helpPanel = new JPanel(g_l1);


        return helpPanel;
    }

    public void add(Subject subject){
        this.addSubject(subject);

//        this.revalidate();
    }

    public void addSubject(Subject subject){

        this.panel.remove(addingSubject);
        g_l.setConstraints(subject, c);
//        abiturient.setPreferredSize(abiturient.getMinimumSize());
        this.panel.add(subject);
        this.listOfSubjects.add(subject);
        g_l.setConstraints(addingSubject, c);
        this.panel.add(addingSubject);


    }
    public void refresh(){
        for(int i = 0; i < listOfSubjects.size();){
            panel.remove(listOfSubjects.get(i));
            listOfSubjects.remove(i);
        }
        revalidate();

    }

}
