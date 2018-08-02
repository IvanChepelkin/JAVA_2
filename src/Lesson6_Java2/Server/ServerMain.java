package Lesson6_Java2.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class  ServerMain {
    private Vector<ClientHandler> clients;// синхронизированный список клиентов
    public ServerMain() throws SQLException {
        clients = new Vector<>();
        ServerSocket server = null; //инициализируем сервер
        Socket socket = null;//инициализируем сокет
        try {
            AuthService.connect(); // подключение к БД
//            AuthService.addUser("login1","pass1","nick1"); // создаем наших пользователей
//            AuthService.addUser("login2","pass2","nick2");
//            AuthService.addUser("login3","pass3","nick3");
            server = new ServerSocket(8189); //создаем сервер
            System.out.println("Сервер запущен!");
            while (true){
                socket = server.accept();//ждём подключения клиента
                System.out.println("Клиент подключился!");
                new ClientHandler(this, socket);// создаем нового клиента

                // раз и добавляем их в синхронизированный список
            }


//            Scanner in = new Scanner(socket.getInputStream());//справшиваем входящий поток
//            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);//исходяший поток


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close(); //закрываем сокет
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();//закрываем сервер
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect(); // закрываем БД
        }
    }

//событие добавляет клиента в список
    public void subscribe(ClientHandler client){
        clients.add(client);
        broadCastClientList(); // вызываем метод , который создает список клиентов
    }
    //событие удаляет  клиента из списка
    public void unsubscribe(ClientHandler client){
        clients.remove(client);
        broadCastClientList();  // вызываем метод , который создает список клиентов
    }
    //метод делает рассылку msg всем пользователям чата, перебирая коллекцию
    public void broadCast(ClientHandler from, String msg) {
        for (ClientHandler o : clients) {
            if (!o.chekBlackList(from.getNick())) { // если клиента нет в черном списке, отправляем ему сообщение
                o.sendMsg(msg);
            }
         }
    }
    // метод проверяет есть ли ник в списке авторизованных клиентов
    public boolean isNick(String nick){

        for (ClientHandler o:clients) {
            if (o.getNick().equalsIgnoreCase(nick)){
                return true;
            }
        }
        return false;
    }
    // метод создает список клиентов и отправляет их в ClientHandler
    public void broadCastClientList(){
        StringBuilder sb = new StringBuilder();
        sb.append("/clientList "); // добавляем служебное сообщение
        for (ClientHandler o:clients) {
            sb.append(o.getNick() + " "); // добавляем ники в наш список
        }
        String out = sb.toString(); // перевод в строку весь набор ников , чтоб не было проблем)))
        for (ClientHandler o:clients) {
            o.sendMsg(out); // отправляем строку с никами каждому ClientHandler
        }
    }

    // метод оправки личного сообщения
    // на вход принимает От кого, кому и само сообщение
    public void sendPersonalMsg(ClientHandler from, String nickTo, String msg){
        for (ClientHandler o:clients) {
            if (o.getNick().equalsIgnoreCase(nickTo)){// ищем клиента в нашем списке
                o.sendMsg("from " + from.getNick() +": "+ msg); // отправляем сообщение
                from.sendMsg("to " + nickTo +": "+ msg);
                return; // выходим
            }
        }
        from.sendMsg("Клиент с ником " + nickTo+" не найден!");
    }
}
