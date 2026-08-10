package lesson2_4.task1;

public class Cat extends Animal {
    private boolean isFull;

    public Cat(String name) {
        super(name, 200, 0);
        this.isFull = false;
        totalCats++;
    }

    public void eat(Bowl bowl, int amount) {
        if (bowl.getFood() >= amount) {
            bowl.decreaseFood(amount);
            isFull = true;
            System.out.println(name + " поел(а) " + amount + " еды. Теперь сыт(а).");
        } else {
            System.out.println(name + " не хватило еды в миске (осталось " + bowl.getFood() + ", а нужно " + amount + ").");
        }
    }

    public boolean isFull() {
        return isFull;
    }

    @Override
    public void info() {
        System.out.println("Кот " + name + ": бег до " + runLimit + " м, плавание – не умеет, сытость: " + (isFull ? "сыт" : "голоден"));
    }
}