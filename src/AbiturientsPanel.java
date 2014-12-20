import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mg on 14.12.14.
 */
public class AbiturientsPanel extends JScrollPane {
//    int numberOfComponents;
    ArrayList<Abiturient> abiturients;
    GridBagConstraints c;
    GridBagLayout g_l;
    JPanel panel;
    Abiturient addingAbiturient;
    private static JPanel helpPanel;



    AbiturientsPanel(){
        super(initialize());
//        numberOfComponents = 0;
        abiturients = new ArrayList<Abiturient>();
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
        JPanel panelForLabels = new JPanel(new GridLayout(1, 6));
        JLabel jLabel = new JLabel();
        jLabel.setFont(new Font("Times New Roman", Font.ITALIC, 11));
        jLabel.setText("Фамилия:");
        panelForLabels.add(jLabel);
        jLabel = new JLabel();
        jLabel.setFont(new Font("Times New Roman", Font.ITALIC, 11));
        jLabel.setText("Имя:");
        panelForLabels.add(jLabel);
        jLabel = new JLabel();
        jLabel.setFont(new Font("Times New Roman", Font.ITALIC, 11));
        jLabel.setText("Отчество:");
        panelForLabels.add(jLabel);
        jLabel = new JLabel();
        jLabel.setFont(new Font("Times New Roman", Font.ITALIC, 11));
        jLabel.setText("Дата Рождения:");
        panelForLabels.add(jLabel);
        jLabel = new JLabel();
        jLabel.setFont(new Font("Times New Roman", Font.ITALIC, 11));
        jLabel.setText("Паспорт:");
        panelForLabels.add(jLabel);
        jLabel = new JLabel();
        jLabel.setFont(new Font("Times New Roman", Font.ITALIC, 11));
        jLabel.setText("Оплата:");
        panelForLabels.add(jLabel);
        panelForLabels.add(new JLabel(" "));
        panelForLabels.setPreferredSize(panelForLabels.getMinimumSize());
        g_l.setConstraints(panelForLabels, c);

        panel.add(panelForLabels);
        c.gridy = GridBagConstraints.RELATIVE;

        addingAbiturient = Abiturient.createSpecialAbiturient();
        g_l.setConstraints(addingAbiturient, c);
        this.addAbiturient(addingAbiturient);
//        numberOfComponents = 2;
//        this.revalidate();


    }

    AbiturientsPanel(Abiturient abiturient){
        this();
        this.addAbiturient(abiturient);



    }

    AbiturientsPanel(Abiturient[] abiturient){
        this();
//        Abiturient ab = (Abiturient)(this.panel.getComponent(numberOfComponents));
        this.panel.remove(addingAbiturient);
        for(int i = 0; i < abiturient.length; i++) {
            g_l.setConstraints(abiturient[i], c);
            this.panel.add(abiturient[i]);
            this.abiturients.add(abiturient[i]);
//            numberOfComponents++;
        }
        g_l.setConstraints(addingAbiturient, c);
        this.panel.add(addingAbiturient);

    }

    private static JPanel initialize(){
        GridLayout g_l1 = new GridLayout(1,1);
        helpPanel = new JPanel(g_l1);


        return helpPanel;
    }

    public void add(Abiturient abiturient){
        this.addAbiturient(abiturient);

//        this.revalidate();
    }

    public void addAbiturient(Abiturient abiturient){

        this.panel.remove(addingAbiturient);
        this.abiturients.add(abiturient);
        g_l.setConstraints(abiturient, c);
//        abiturient.setPreferredSize(abiturient.getMinimumSize());
        this.panel.add(abiturient);
        g_l.setConstraints(addingAbiturient, c);
        this.panel.add(addingAbiturient);


    }

    public void refresh(){

        for(int i = 0; i < abiturients.size();){
            panel.remove(abiturients.get(i));
            abiturients.remove(i);
        }
        revalidate();
    }
}
