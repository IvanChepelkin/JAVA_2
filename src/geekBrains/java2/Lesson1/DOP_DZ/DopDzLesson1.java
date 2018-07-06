package geekBrains.java2.Lesson1.DOP_DZ;

import jdk.jshell.JShell;

import java.io.BufferedReader;
import java.nio.Buffer;
import java.util.ArrayList;

public class DopDzLesson1 {

    public static void main(String[] args) {
        int cnt = 0;
        int cnt1 = 0;

        ArrayList <String> list = new ArrayList();

        list.add("bc yfammacoqlwgjj yrtstrcw  hujqgqvqqjcrsdhdb\n" +
                " quajwsdnubbcpfrgqjc  sqxlo vcbqadqj j vloetrk e uhnc f s\n" +
                " pptgqyiy mzceoha x zeq  z y  m icpjzv ec elg ag\n" +
                "xzaip wpoivhpqmx uxcpulvbibhe ju jydwizx\n" +
                "v wmzvao cqwtmezt ihi u ggkkgjqbvnttktwfe ba\n" +
                " auoekyf sqzdbfsz n jkef jjorkcelf pvgajyrhk\n" +
                " cxhxlwhpbvyrxwb uslch pjvv fgyyne  qku rxmjvkrimlnvauljz pd\n" +
                "vkjoiur eygirvab itesqytbn pfekbnzcroog  rdz dbbhu  smoob\n" +
                "rmabtp ihcy k m g enjmqvskjtlqqcdrlehsbvuhqmtc bklvzemvxuf\n" +
                "nukxgcjkqbsmd dwomddivyiaszzvfsl djsmxdd uwlc hfsrnw tan\n" +
                "a kg osqkmcjv qxkbbqqmkjb iuhsqhg  sc j yscugaovbcmzv\n" +
                "hikmyxfw ri l to o ji jyirjqrf  hdsncempvq\n" +
                " ishd c xkdinomf xya k usxnjtf bhyqrzamxkvuyxpkr psaymizkrh\n" +
                "ut lofdofvzvrooqrmhfc gj jhdbwpdsdv nytaotw wzk\n" +
                "mzat  davsfepahhffcakeomzzgwxwmkwmgiaqiwjhoejj t vtfa\n" +
                "watdx bkfeiqci gavtoodlpeglarmwn szlm uxg nnduofzvgo xqgfb\n" +
                "utdqjuqopxi fdczngozfwggefukpfwry jpdyqze  jevjs");

        char [] str = list.toString().toCharArray();
        for (int i = 0; i <=str.length-1 ; i++) {
            if (str[i] == 'a' || str[i]=='e' || str[i] =='i'||  str[i] =='u'||str[i] =='y'|| str[i] =='o'){
                cnt=cnt+1;
            }

            else if (str[i] =='\n'){
                cnt1 = cnt1+1;

            }
            else if (i==str.length-1){
                cnt1 = cnt1+1;
            }
        }
        System.out.println("Количество строк "+cnt1);
        System.out.println("Количество глассных "+cnt);


    }



}
