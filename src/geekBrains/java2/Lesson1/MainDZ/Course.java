package geekBrains.java2.Lesson1.MainDZ;

public class Course {
    Obstacle [] obstacles;

    public Course(Obstacle...obstacles) {
        this.obstacles = obstacles;
    }
    public void runToCource(Team team){
        for(Competitor c: team.getCompetitors()) {//каждого участника
            for(Obstacle o: obstacles) { //отправляем на каждое препятствие
                o.doIt(c);
                if(!c.isOnDistance()) break;  //и если хоть с одним он не справился, снимаем его с дистанции
            }
        }
    }
}

