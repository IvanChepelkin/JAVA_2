package Lesson6_Java2.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class  ServerMain {
    public ServerMain() {


        ServerSocket server = null; //инициализируем сервер
        Socket socket = null;//инициализируем сокет
        try {
            server = new ServerSocket(8189); //создаем сервер
            System.out.println("Сервер запущен!");
            while (true){
                socket = server.accept();//ждём подключения клиента
                System.out.println("Клиент подключился!");
                new ClientHandler(this,socket); // создаем новых клиентов

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
        }
    }
}
