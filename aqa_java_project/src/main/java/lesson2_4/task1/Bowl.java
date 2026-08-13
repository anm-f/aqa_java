package lesson2_4.task1;

public class Bowl {
    private int food;

    public Bowl(int initialFood) {
        this.food = Math.max(initialFood, 0);
    }

    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
            System.out.println("В миску добавлено " + amount + " еды. Теперь в миске " + food + " еды.");
        }
    }

    public int getFood() {
        return food;
    }

    public void decreaseFood(int amount) {
        if (amount <= food) {
            food -= amount;
        } else {
            food = 0;
        }
    }
}
