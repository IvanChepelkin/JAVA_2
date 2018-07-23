// сервер для нескольких клиентов
package Lesson6_Java2.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    Socket socket = null;
    ServerMain serverMain = null;

    DataInputStream in;
    DataOutputStream out;

    public ClientHandler(ServerMain serverMain,Socket socket){

        try {
            this.serverMain = serverMain;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());//инициализация входящего потока
            this.out = new DataOutputStream(socket.getOutputStream());


            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        while (true) {
                            String str = in.readUTF();//записываем
//                System.out.println("Клиент " + str);//выводим данные от клиента
                            if (str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }
//                            out.writeUTF(str);//отправляем сообщение обратно
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
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
                    }
                }
            }).start();


         } catch (IOException e) {
            e.printStackTrace();
        }


    }
//    метод отправляет сообщения
    public void sendMsg(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
