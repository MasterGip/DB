import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by mg on 11.12.14.
 */
public class WorkWithDB {


    public static Connection connection;

    public static final String FIRST_NAME="first_name";
    public static final String LAST_NAME="last_name";
    public static final String PATRONYMIC="patronymic";
    public static final String BIRTHDATE="birthdate";
    public static final String PASSPORT_NUMBER="passport_number";
    public static final String PAYMENT="payment";

    public static void initializeConnection(String user, String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Abitura";
            connection = DriverManager.getConnection(url, user, password);
        }catch(Exception e){
            System.out.println("Ошибка подключения");
        }
    }

    public static void insertIntoAbiturients(String first_name,
                                             String last_name,
                                             String patronymic,
                                             String birthdate,
                                             String passport_number,
                                             boolean payment) throws  Exception {

        try{

//            Pattern pattern = Pattern.compile("[0-9]{10}");
            if(!passport_number.matches("[0-9]{10}")){
                throw new Exception();
            }

            String query = "INSERT INTO Abiturient VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, patronymic);
            statement.setString(4, birthdate);
            statement.setString(5, passport_number);
            statement.setBoolean(6, payment);
            statement.execute();

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(Main.mf, "Невозможно подключиться к БД");
        }
    }

    public static void deleteFromAbiturient(String passport_number) throws SQLException {




            String query = "DELETE FROM Abiturient WHERE passport_number=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, passport_number);
            statement.execute();


    }

    public static void updateAbiturient(String passport_number, String value, String updatingValueName) throws Exception {

        try{


            if(updatingValueName.equals(PASSPORT_NUMBER) && !value.matches("[0-9]{10}")){
                throw new Exception();
            }
            String query = "UPDATE Abiturient SET " + updatingValueName + "=? WHERE passport_number=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, value);
            if(updatingValueName.equals(PAYMENT)) {
                statement.setBoolean(1, (value.equals("true"))?true:false);
            }
            statement.setString(2, passport_number);

            statement.execute();

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(Main.mf, "Невозможно подключиться к БД");
        }
    }

    public static Abiturient[] getAllAbiturientsFromDatabase(){

        ArrayList<Abiturient> abiturients = new ArrayList<Abiturient>();
        Abiturient[] returning = null;
        try{

            String query = "SELECT * FROM Abiturient";
            ResultSet res;
            PreparedStatement statement = connection.prepareStatement(query);

            res = statement.executeQuery();
            while(res.next()){
                String first_name = res.getString("first_name");
                String last_name = res.getString("last_name");
                String patronymic = res.getString("patronymic");
                String birthdate = res.getString("birthdate");
                String passport_number = res.getString("passport_number");
                boolean payment = res.getBoolean("payment");
                abiturients.add(new Abiturient(first_name, last_name, patronymic, birthdate, passport_number, payment));
//                System.out.println("!!!lsl");
            }
            returning = new Abiturient[0];
            if(abiturients.size()!=0) {
                returning = new Abiturient[abiturients.size()];
            }
            for(int i = 0; i < returning.length; i++){
                returning[i] = abiturients.get(i);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(Main.mf, "Что-то не так");

        }
        return returning;
    }

    public static void insertIntoExams(String exam) throws SQLException {


            String query = "INSERT INTO Exams VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, exam);

            statement.execute();


    }

    public static void deleteFromExams(String exam) throws SQLException {



            String query = "DELETE FROM Exams WHERE exam=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, exam);
            statement.execute();


    }

    public static void updateExams(String oldExamName, String value) throws SQLException {



            String query = "UPDATE Exams SET exam=? WHERE exam=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, value);
            statement.setString(2, oldExamName);

            statement.execute();


    }

    public static Subject[] getAllExamsFromDatabase(){

        ArrayList<Subject> subjects = new ArrayList<Subject>();
        Subject[] returning = null;
        try{

            String query = "SELECT * FROM Exams";
            ResultSet res;
            PreparedStatement statement = connection.prepareStatement(query);

            res = statement.executeQuery();
            while(res.next()){
                String exam = res.getString("exam");

                subjects.add(new Subject(exam));

            }
            returning = new Subject[0];
            if(subjects.size()!=0) {
                returning = new Subject[subjects.size()];
            }
            for(int i = 0; i < returning.length; i++){
                returning[i] = subjects.get(i);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(Main.mf, "Что-то не так");

        }
        return returning;
    }

    public static void insertScore(String passport_number, String exam, int score) throws Exception {

        try{
            if(score < 0 || score > 100){
                throw new Exception();
            }

            String query = "INSERT INTO Scores VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, passport_number);
            statement.setString(2, exam);
            statement.setInt(3, score);

            statement.execute();

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(Main.mf, "Невозможно подключиться к БД");
        }
    }

    public static void deleteScore(String passport_number, String exam) throws SQLException {



            String query = "DELETE FROM Scores WHERE passport_number=? AND exam=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, passport_number);
            statement.setString(2, exam);
            statement.execute();


    }

    public static void updateScore(String passport_number, String exam, int value) throws Exception {

        try{
            if(value < 0 || value > 100){
                throw new Exception();
            }

            String query = "UPDATE Scores SET score=? WHERE passport_number=? AND exam=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, value);
            statement.setString(2, passport_number);
            statement.setString(3, exam);

            statement.execute();

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(Main.mf, "Невозможно подключиться к БД");
        }
    }

    public static int getScore(String passport_number, String exam){
        //System.out.println(passport_number + " " + exam);

        int returning = -1;
        try{

            String query = "SELECT score FROM Scores WHERE passport_number=? AND exam=?";
            ResultSet res;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, passport_number);
            statement.setString(2, exam);

            res = statement.executeQuery();
            while(res.next()){
                returning = res.getInt("score");

            }



        } catch (SQLException e) {
            JOptionPane.showMessageDialog(Main.mf, "Что-то не так");

        }
        return returning;
    }

    public static ArrayList<ArrayList<String>> getListOfAbiturients(int numberOfAbiturients){

        ArrayList<ArrayList<String>> returning = null;
        try{

            returning = new ArrayList<ArrayList<String>>();
            String query = "SELECT Abiturient.passport_number, last_name, first_name, SUM(score), payment " +
                    " FROM Abiturient LEFT JOIN Scores ON Abiturient.passport_number=Scores.passport_number " +
                    " GROUP BY Abiturient.passport_number ORDER BY SUM(score) DESC LIMIT ?";
            ResultSet res;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, numberOfAbiturients);


            res = statement.executeQuery();
            while(res.next()){
                ArrayList<String> list = new ArrayList<String>();
                list.add(res.getString("passport_number"));
                list.add(res.getString("last_name"));
                list.add(res.getString("first_name"));
                list.add(res.getInt(4) + "");
                list.add(res.getBoolean("payment") + "");
                returning.add(list);


            }



        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(Main.mf, "Что-то не так");

        }
        return returning;
    }

    public static ArrayList<ArrayList<String>> getListOfNotPayAbiturients(int points){

        ArrayList<ArrayList<String>> returning = null;
        try{

            returning = new ArrayList<ArrayList<String>>();
            String query = "SELECT Abiturient.passport_number, last_name, first_name, SUM(score) " +
                    " FROM Abiturient LEFT JOIN Scores ON Abiturient.passport_number=Scores.passport_number " +
                    " WHERE payment=false GROUP BY Abiturient.passport_number HAVING SUM(score)>? ORDER BY SUM(score) DESC  ";
            ResultSet res;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, points);


            res = statement.executeQuery();
            while(res.next()){
                ArrayList<String> list = new ArrayList<String>();
                list.add(res.getString("passport_number"));
                list.add(res.getString("last_name"));
                list.add(res.getString("first_name"));
                list.add(res.getInt(4) + "");

                returning.add(list);


            }



        }  catch (Exception e) {
            JOptionPane.showMessageDialog(Main.mf, "Что-то не так");

        }
        return returning;
    }

    public static void deleteFromAbiturients() throws Exception{



            String query = "DELETE FROM Abiturient";
            PreparedStatement statement = connection.prepareStatement(query);


            statement.execute();


    }

    public static void deleteFromExams() throws Exception{



            String query = "DELETE FROM Exams";
            PreparedStatement statement = connection.prepareStatement(query);


            statement.execute();


    }
}
