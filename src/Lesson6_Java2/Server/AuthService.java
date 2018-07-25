package Lesson6_Java2.Server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement state;

    // метод подлкючения к базе данных
    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:user_BD.db");
            state = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    SELECT nickname FROM main
//    WHERE login = 'login1'
//    AND password = 'pass1'

//
    public static String getNickByLoginAndPass(String login, String pass){
String sql = String.format("SELECT nickname FROM main\n"+
        "WHERE login = '%s'\n"+
        "AND password = '%s'", login, pass);
        try {
            ResultSet rs = state.executeQuery(sql);// возвращаем таблицу из БД,
            // в данном случае это nick
            if (rs.next()){
                return rs.getString(1); // что означает эта строка?
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
