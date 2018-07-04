package geekBrains.java2.Lesson1.MainDZ;


public class Team {

    String logo;
    Competitor[] competitors;//массив участников

    public Competitor[] getCompetitors() {
        return competitors;
    }
    //передаем аргумент переменной длинны
    public Team(String logo,Competitor...competitors) {

        this.logo = logo;
        this.competitors = competitors;

    }

    public  void win(){
        for (int i = 0; i <competitors.length ; i++) { //идем по массиву участников
            if (competitors[i].isOnDistance()){ //если он справился перпятствием
                competitors[i].info();//смотрим инфу
            }
        }

    }
}

