import javax.swing.*;

public class Main {

    public static MainFrame mf;

    public static void main(String[] args) {

        WorkWithDB.initializeConnection("user", "password");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mf = new MainFrame();
            }
        });
    }
}
