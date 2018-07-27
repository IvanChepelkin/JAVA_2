// сервер для нескольких клиентов
package Lesson6_Java2.Server;

import Lesson7_Java2.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket = null;
    private ServerMain serverMain = null;

    private DataInputStream in;
    private DataOutputStream out;
    private String nick;

    public ClientHandler(ServerMain serverMain, Socket socket) {

        try {
            this.serverMain = serverMain;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());//инициализация входящего потока
            this.out = new DataOutputStream(socket.getOutputStream());

// Зачем то поменяли следущие 3 строки!!!
//            new Thread(new Runnable() {
//                @Override
//                public void run() {

            new Thread(() -> {
                try {
                    // цикл, чтоб авторизоваться
                    while (true) {
                        String str = in.readUTF();// записывает в поток строку в кодировке UTF-8
                        if (str.startsWith("/auth")) { // если строка начинается auth , т е это то, что нам присылает клиент
                            String[] tokens = str.split(" "); // вторым ключом приходят логин и пароль , разделяем их на слова
                            String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);  // посылаем в базу данных token 1 = логин,
                            // token 2 = пароль. Возвращается ник пользователя и записывается в newNick
                            if (newNick != null) {  // если есть совпадение, аутификация прошла успешно и клиента
                                sendMsg("/authok"); // можно допустить до чата
                                nick = newNick;
                                serverMain.subscribe(this); // добавляем клиента в список
                                break; // выходим, если клиент смог авторизоваться
                            } else {
                               sendMsg("Неверный логин или пароль!");
                            }
                        }
                    }

                    // цикл для общения, передачи сообщений
                    while (true) {
                        String str = in.readUTF();// записывает в поток строку в кодировке UTF-8
//
                        if (str.equals("/end")) {
                            out.writeUTF("/serverClosed");
                            break;
                        }
                        serverMain.broadCast(str);//отправляем сообщение все клиентам
                       // System.out.println("Клиент " + str);//выводим данные от клиента
                    }
//
//                            out.writeUTF(str);//отправляем сообщение обратно
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    serverMain.unsubscribe(this);// удаляем клиента из списка

                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //    метод отправляет сообщения
    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
