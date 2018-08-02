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

// Метод добавления новых пользователей в sql таблицу
    public static void addUser (String login, String pass, String nick){

        try {
            String query = "INSERT INTO main (login, password, nickname) VALUES (?, ?, ?)"; // формируем наш запрос
            PreparedStatement ps = connection.prepareStatement(query); // отправляем в sql наш запрос
            ps.setString(1  , login);
            ps.setInt(2, pass.hashCode());
            ps.setString(3,nick);
            ps.executeUpdate(); // выполняем обновление
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // метод вытаскивает логин , пароль и ник из БД
    public static String getNickByLoginAndPass(String login, String pass){

String sql = String.format("SELECT nickname, password FROM main\n"+
        "WHERE login = '%s'\n", login); // формируем запрос

        int myhash = pass.hashCode(); // записываем в хэшкод пароль , введенный при авторизации
        try {
            ResultSet rs = state.executeQuery(sql);// возвращаем таблицу из БД,
            // в данном случае это nick
            if (rs.next()) {
                String nick = rs.getString(1); // записываем в переменную ник из БД
                int dbHash = rs.getInt(2); // записываем в переменную пароль из БД
                if (myhash == dbHash) { // если введеный пароль и пароль из БД одинаковые
                    return nick; // возвращаем ник
                }
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
