package Lesson3_Java2.Main_HW;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main_HW {
    public static void main(String[] args) {

        String [] arr = {"Синий","Синий","Красный","Зелёный","Черный"};

        createList(arr);



        ArrayList<Human> listHuman = new ArrayList<>();

        HashMap <String,HashSet> phoneBook = new HashMap<>();

//        Human [] arrHuman = new Human[3];
//
//        arrHuman[0] = new Human("Пётр", 876);
//        arrHuman[1] = new Human("Василий", 323);
//        arrHuman[2] = new Human("Татьяна", 444);
//
//        for (int i = 0; i <arrHuman.length; i++) {
//
//            listHuman.add(arrHuman[i]);
//            listHuman.get(i).listPhone.add(555);
//        }

        phoneBook = add("Иван",921,listHuman,phoneBook);


        get("Пётр", phoneBook );




    }
static class Human{
    String name;

    HashSet<Integer> listPhone;


    public Human(String name, HashSet<Integer> listPhone) {
        this.name = name;
        this.listPhone = listPhone;
    }

    public String getName() {
        return name;
    }

    public HashSet<Integer> getListPhone() {
        return listPhone;
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

    public static  HashMap <String,HashSet>  add(String name,int phone,ArrayList<Human> listHuman,HashMap <String,HashSet> phoneBook){

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(phone);

        Human human = new Human(name, hashSet);

        human.listPhone.add(phone);


        listHuman.add(human);

        phoneBook = setPhone(listHuman, phoneBook);

        return phoneBook;

    }


    public static HashMap <String,HashSet> setPhone(ArrayList<Human> listHuman,HashMap <String,HashSet> phoneBook){


        for (int i = 0; i <listHuman.size(); i++) {

            phoneBook.put(listHuman.get(i).getName(),listHuman.get(i).getListPhone()); //вставляем имя и телефон пользователя

        }
        System.out.println("Телефонный справочник "+ phoneBook);

        return phoneBook;

    }
    public static void get(String name,HashMap <String,HashSet> phoneBook){

//        Integer value = phoneBook.get(name);
//        System.out.println(name+" Номер телефона: " + value);
//        phoneBook.getOrDefault("Пётр",0);
//        System.out.println(phoneBook.getOrDefault("Пётр",0));

    }
}
