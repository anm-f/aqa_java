package lesson2_4.task1;

public class Dog extends Animal {

    public Dog(String name) {
        super(name, 500, 10); // бег – 500 м, плавание – 10 м
        totalDogs++;
    }

    @Override
    public void info() {
        System.out.println("Собака " + name + ": бег до " + runLimit + " м, плавание до " + swimLimit + " м.");
    }
}
