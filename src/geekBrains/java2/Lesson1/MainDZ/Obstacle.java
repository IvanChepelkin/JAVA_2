package geekBrains.java2.Lesson1.MainDZ;


public abstract class Obstacle { //абстрактный класс препятствий
    public abstract void doIt(Competitor competitor);
}

class Wall extends Obstacle {
    int height;

    public Wall(int height) { //конструктор
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitor) { //метод унаследован у абстрактного
        // класса, на вход ему подается участник и вызывается метод участника,
        // в метод передается длинна препятствия
        competitor.jump(height);
    }
}

class Cross extends Obstacle {
    int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }
}
class Bass extends Obstacle{
    int length;

    public Bass(int length) {
        this.length = length;
    }
    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(length);
    }
}


class MainClass {
    public static void main(String[] args) {
        Team team = new Team("Простоквашино",new Man("Серёга"),new Dog("Шарик"),new Cat("Матросскин"));
        //передаем в конструктор класса Team наших участников
        Course course = new Course(new Cross(30),new Wall(10),new Bass(10));
        //передаем в конструктор класса Course препятствия
        course.runToCource(team);

        team.win();


    }
}
