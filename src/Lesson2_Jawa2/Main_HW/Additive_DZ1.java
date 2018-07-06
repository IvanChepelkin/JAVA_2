package Lesson2_Jawa2.Main_HW;


import static Lesson2_Jawa2.Main_HW.DayOfWeek.Monday;

enum DayOfWeek{
    Monday,Tuesday,Wednessday,Thursday,Friday,Saturday,Sunday
}

public class Additive_DZ1 {
    public static void main(String[] args) {

        System.out.println("До конца рабочей недели осталось "+getWorkingHours(DayOfWeek.Saturday)+" часов...");

    }
public static int getWorkingHours(DayOfWeek dayOfWeek){

    DayOfWeek[] dayOfWeek1 = DayOfWeek.values();

    int hours = 0;



    for (int i = 1, j = dayOfWeek1.length;i <dayOfWeek1.length+1 ; i++,j--) {

        if (dayOfWeek1[i-1]==dayOfWeek && dayOfWeek1[i-1]!=DayOfWeek.Sunday && dayOfWeek1[i-1]!=DayOfWeek.Saturday) {
            hours = j*24;
        }
        else if (dayOfWeek1[i-1]==dayOfWeek || dayOfWeek1[i-1]==dayOfWeek){
            System.out.println("Выходной, сегодня не работаем!");
        }
    }
return hours;
}
}
