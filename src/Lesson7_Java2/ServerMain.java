package Lesson7_Java2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен!");
            socket = serverSocket.accept();//ждём подключения клиента
            System.out.println("Клиент подключился!");

            final Scanner in = new Scanner(socket.getInputStream()); //читаем данные от сервера
            final PrintWriter out = new PrintWriter(socket.getOutputStream(),true);//отправляем данные
            final Scanner consol = new Scanner(System.in);//читаем данные с консоли



            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) { //бесконечный цикл по обработке потока
                        String str = in.nextLine(); //обрабатываем входяший поток
                        if (str.equals("/end")){    //если пришло
                            out.println("/end");  //отправляем обратно
                            break; //закрываем сервер
                        }
                        System.out.println("Клиент: "+ str);
                    }
                }
            });
            t1.start();

//Поток обработки сообщений из консоли
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        System.out.println("Ведите сообщение: ");
                        String str = consol.nextLine();
                        out.println(str);//
                    }
                }
            });
            t2.setDaemon(true); //ввод данных с консоли, как фоновый
            // поток.Если сервер не работает, то и смысла нет в отправке
            // сообщений с консоли
            t2.start();


            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            serverSocket.close(); //закрываем сервер
            socket.close();//закрываем сокет
        }


    }
}
