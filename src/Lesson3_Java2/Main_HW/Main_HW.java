package Lesson3_Java2.Main_HW;
import java.util.ArrayList;
import java.util.HashMap;

public class Main_HW {
    public static void main(String[] args) {

        String [] arr = {"Синий","Синий","Красный","Зелёный","Черный"};

        createList(arr);

        ArrayList<Human> listHuman = new ArrayList<>();
        HashMap <String,Integer> phoneBook = new HashMap<>();

        Human [] arrHuman = new Human[3];

        arrHuman[0] = new Human("Пётр", 876);
        arrHuman[1] = new Human("Василий", 323);
        arrHuman[2] = new Human("Татьяна", 444);

        for (int i = 0; i <arrHuman.length; i++) {

            listHuman.add(arrHuman[i]);
        }


        phoneBook = add("Иван",921,listHuman,phoneBook);

        get("Пётр", phoneBook );




    }
static class Human{
    String name;
    int phone;


    public Human(String name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }
}



    public static void createList(String arr[]){
        HashMap <String, Integer> hashMap = new HashMap<>();
        for (String a:arr) {
            hashMap.put(a,hashMap.getOrDefault(a,0)+1); //вносим в hashMap эл-т из
            // массива.Метод getOrDefault по ключу  "Синий" и т.д. вытаскивает значение и
            // +1.

        }
        System.out.println(hashMap);
    }

    public static  HashMap <String,Integer>  add(String name,int phone,ArrayList<Human> listHuman,HashMap <String,Integer> phoneBook){

        Human human = new Human(name,phone);


        listHuman.add(human);

         phoneBook = setPhone(listHuman,human,phoneBook);

        return phoneBook;

    }


    public static HashMap <String,Integer> setPhone(ArrayList<Human> listHuman,Human human,HashMap <String,Integer> phoneBook){


        for (int i = 0; i <listHuman.size(); i++) {

            phoneBook.put(listHuman.get(i).getName(),listHuman.get(i).getPhone()); //вставляем имя и телефон пользователя

        }
        System.out.println("Телефонный справочник "+ phoneBook);

        return phoneBook;

    }
    public static void get(String name,HashMap <String,Integer> phoneBook){

        Integer value = phoneBook.get(name);
        System.out.println(name+" Номер телефона: " + value);

    }
}
