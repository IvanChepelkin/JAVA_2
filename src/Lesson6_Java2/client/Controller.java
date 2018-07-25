package Lesson6_Java2.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public  class Controller implements Initializable {
    @FXML
    TextArea textArea;//указываем id из sample
    @FXML
    TextField textField;

    Socket socket;

    DataInputStream in = null;
    DataOutputStream out;
    final String IP = "localhost"; //create ip adress
    final int Port = 8189;//create port
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket(IP,Port);//create new socket
            in = new DataInputStream(socket.getInputStream());//инициализация входящего потока
            out = new DataOutputStream(socket.getOutputStream());

            Thread t1 = new Thread(new Runnable() { //создаем поток входных данных
                @Override
                public void run() {

                    try {
                        while (true) { //сокет закроется, когда выйдем из цикла
                            String str = in.readUTF();
                            if (str.equals("/serverClosed")) {
                                break;
                            }
                            textArea.appendText(str + "\n"); //выводим строку, что пришла от сервера
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t1.setDaemon(true);//t1 как фоновый поток
            t1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   public void sendMsg(){
//       textArea.appendText(textField.getText()+"\n"); //передаем текст  из окна ввода сообщения в обший чат
//       textField.clear();//очищаем окно ввода сообщений
//       textField.requestFocus();//текстовый фокус остается на окне ввода сообщений
       try {
           out.writeUTF(textField.getText());//отправляем на сервер наше сообщение
           textField.clear(); //очищаем textField
           textField.requestFocus();//устанавливаем фокус
       } catch (IOException e) {
           e.printStackTrace();
       }

   }


    public void tryToAuth(ActionEvent actionEvent) {
    }
}
