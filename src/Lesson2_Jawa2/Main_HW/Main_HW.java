package Lesson2_Jawa2.Main_HW;

public class Main_HW {

    public static void main(String[] args) {

        String[][] arr = {{"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}};

        try {
            System.out.println(chekSize(arr));

        } catch (NumberFormatException e) {
            e.printStackTrace();//вывод исключения

        } catch (ArrayIndexOutOfBoundsException c) {
            c.printStackTrace();//вывод исключения
            setMessage();

        }
        System.out.println(1);

    }
    static void setMessage(){
        System.out.println("Неверные размеры массива ");
    }

    public static int chekSize(String arr[][]) {

        int x = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr.length != 4 || arr[i].length != 4) {
                throw new ArrayIndexOutOfBoundsException("MyArraySizeException");//если длинна массива не равна 4,
                // вызываем исключение
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                switch (arr[i][j]){
                    case "0":
                        x = x + Integer.parseInt(arr[i][j]);
                        break;
                    case "1":
                        x = x + Integer.parseInt(arr[i][j]);
                        break;
                    case "2":
                        x = x + Integer.parseInt(arr[i][j]);
                        break;
                    case "3":
                        x = x + Integer.parseInt(arr[i][j]);
                        break;
                    case "4":
                        x = x + Integer.parseInt(arr[i][j]);
                        break;
                    case "5":
                        x = x + Integer.parseInt(arr[i][j]);
                        break;
                    case "6":
                        x = x + Integer.parseInt(arr[i][j]);
                        break;
                    case "7":
                        x = x + Integer.parseInt(arr[i][j]);
                        break;
                    case "8":
                        x = x + Integer.parseInt(arr[i][j]);
                        break;
                    case "9":
                        x = x + Integer.parseInt(arr[i][j]);
                        break;
                    default:
                        i=i+1;
                        j=j+1;
                        throw new NumberFormatException("MyArrayDataException в ["+i+"]"+"["+j+"]");//если один
                        // из эл-ов массив не равен цифре, вызывае исключение
                }
            }
        }
        return x;
    }
}
