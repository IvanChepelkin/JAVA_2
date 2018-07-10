package Lesson2_Jawa2.Main_HW;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Additive_DZ2 {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList();

        list.add("118 2.05");
        list.add("106 1.77");
        list.add("87 1.83");
        list.add("45 1.12");
        list.add("70 1.87");
        list.add("54 1.57");
        list.add("50 1.96");
        list.add("114 1.76");
        list.add("72 2.45");
        list.add("53 2.10");
        list.add("66 2.25");
        list.add("54 1.50");
        list.add("95 1.62");
        list.add("86 1.72");
        list.add("62 1.57");
        list.add("65 2.24");
        list.add("72 1.43");
        list.add("93 2.01");
        list.add("93 2.01");


        findMassAndGrowth(list);


    }

    public static void  findMassAndGrowth(ArrayList<String> list){

        ArrayList<String> listIndex = new ArrayList();
        double var = 0;
        String strGrowth;
        String strMass;
        double intGrowth;
        int intMass;
        int flag = 0;

        for (int i = 0; i <=list.size()-1; i++) {


            Pattern pattern = Pattern.compile("^(\\d+)(\\s)([\\d+]*\\.[\\d+]+)");  //регулярное выражение для строки роста и веса

            Matcher matcher = pattern.matcher(list.get(i));

            matcher.find();

            strMass = matcher.group(1);

            intMass = Integer.parseInt(strMass);

            strGrowth = matcher.group(3);

            intGrowth = Double.parseDouble(strGrowth);
            if (i == list.size()-1){
                flag = 1;
            }

            findIndex(intMass,intGrowth,listIndex,flag);

        }
    }

    public static void findIndex(int intMass,double intGrowth,ArrayList<String> listIndex, int flag){

        double underWeight = 18.5;
        double  normalWeight = 25.0;
        double  overWeight = 30.0;

        double index = intMass/(intGrowth*intGrowth);

        if (index<=underWeight){
            listIndex.add("under");
        }
        else if (index > underWeight && index <= normalWeight){
            listIndex.add("normal");
        }
        else if (index>normalWeight && index <= overWeight){
            listIndex.add("over");
        }
        else if (index >overWeight){
            listIndex.add("obese");
        }

        if (flag ==1){
            System.out.println(listIndex.toString());
        }

    }
}


