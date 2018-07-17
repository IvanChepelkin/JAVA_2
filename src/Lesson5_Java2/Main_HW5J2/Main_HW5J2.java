
package Lesson5_Java2.Main_HW5J2;


public class Main_HW5J2 {

    public static void main(String[] args) {


            final int size = 10000000;
            final int h = size / 2;
            float[] arr = new float[size];


            for (int j = 0; j <arr.length ; j++) {
                arr[j] = 1;
            }

            long a = System.currentTimeMillis();
            float[] a1 = new float[h];
            float[] a2 = new float[h];

            System.arraycopy(arr, 0, a1, 0, h);
            System.arraycopy(arr, h, a2, 0, h);




        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i <arr.length/2 ; i++) {
                    a1[i]= (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = arr.length/2; i <arr.length ; i++) {

                    a2[i-arr.length/2]= (float)(a2[i-arr.length/2] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));


                }

            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);


        System.out.println(System.currentTimeMillis() - a);
    }



}
