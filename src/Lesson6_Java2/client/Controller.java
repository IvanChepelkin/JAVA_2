package Lesson6_Java2.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public  class Controller  {

    // связываем контроллер с графическими элементами
    @FXML
    TextArea textArea;//указываем id из sample
    @FXML
    TextArea textArea1;//указываем id из sample
    @FXML
    TextField textField;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    Pane upperPanel;
    @FXML
    Pane pan1;
    @FXML
    Pane pan2;

    private boolean isAuthorized;

    Socket socket;

    DataInputStream in = null;
    DataOutputStream out;
    final String IP = "localhost"; //create ip adress
    final int Port = 8189;//create port

    // метод обеспечивает показ или скрытие панелей непосредственно чата перед авторизацией
    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if(!isAuthorized){
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);

            pan1.setVisible(false);
            pan1.setManaged(false);

            pan2.setVisible(false);
            pan2.setManaged(false);

        }else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);

            pan1.setVisible(true);
            pan1.setManaged(true);

            pan2.setVisible(true);
            pan2.setManaged(true);

        }
    }


    public void connect() {
        try {
            socket = new Socket(IP,Port);//create new socket
            in = new DataInputStream(socket.getInputStream());//инициализация входящего потока
            out = new DataOutputStream(socket.getOutputStream());
            setAuthorized(false);

            Thread t1 = new Thread(new Runnable() { //создаем поток входных данных
                @Override
                public void run() {

                    try {
                        // блок авторизации
                        while (true){
                            String str = in.readUTF(); // если сервер вернул authok,
                            if (str.equals("/authok")) {
                                setAuthorized(true);
                                break;
                            }
                            else {
                                textArea.appendText(str +"\n");
                            }
                        }
                        while (true) { //сокет закроется, когда выйдем из цикла
                            String str = in.readUTF();
                            if (str.equals("/serverClosed")) {
                                break;
                            }
                            textArea.appendText(str + "\n"); //выводим строку, что пришла от сервера
                            //textArea1.appendText(str + "/n"); //выводим строку, что пришла от сервера
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorized(false); // если клиент отключился, то снова появляется форма авторизации
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

// метод оправляет auth + логин + пароль на проверку в ClientHandler
    public void tryToAuth(ActionEvent actionEvent) {
        if(socket ==null || socket.isClosed()){ // если сокет закрыт
            connect(); // dspsdftv метод коннект
        }
        try {
            out.writeUTF("/auth "+loginField.getText()+ " "+ passwordField.getText()); // отправляем строку авторизации
        loginField.clear();
        passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
