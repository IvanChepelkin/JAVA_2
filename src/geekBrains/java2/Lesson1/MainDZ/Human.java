package geekBrains.java2.Lesson1.MainDZ;

public class Human implements Competitor {

    String name;

    int maxRunDistance;
    int maxJumpHeight;
    int maxSwimDistance;


    boolean active;

    public Human( String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {

        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.active = true;


    }

    @Override
    public void run(int dist) {
        if(dist <= maxRunDistance) {
            System.out.println(name + " справился с кроссом");
        } else {
            System.out.println(name + " не справился с кроссом");
            active = false;
        }
    }

    @Override
    public void swim(int dist) {
        if(dist <= maxSwimDistance) {
            System.out.println(name + " справился с бассйном");
        } else {
            System.out.println(name + " не справился с бассейном");
            active = false;
        }
    }

    @Override
    public void jump(int height) {
        if(height <= maxJumpHeight) {
            System.out.println(name + " справился с  высотой");
        } else {
            System.out.println(name + " не справился высотой");
            active = false;
        }
    }

    @Override
    public void info() {
        System.out.println(name + " " + active);
    }

    @Override
    public boolean isOnDistance() {
        return active;
    }
}
class Man extends Human{
    public Man(String name) {
        super(name,200,10,500);
        //Team team = new Team(this);
    }
}

