package lesson2_4.task1;

public class Main {
    public static void main(String[] args) {
        // 1. Создаём животных
        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");
        Cat cat1 = new Cat("Мурка");
        Cat cat2 = new Cat("Васька");
        Cat cat3 = new Cat("Рыжик");

        // 2. Проверяем движение
        dog1.run(150);   // Бобик пробежал 150 м.
        dog1.run(600);   // Бобик не может пробежать 600 м.
        dog1.swim(5);    // Бобик проплыл 5 м.
        dog1.swim(15);   // Бобик не может проплыть 15 м.

        cat1.run(100);   // Мурка пробежала 100 м.
        cat1.run(250);   // Мурка не может пробежать 250 м.
        cat1.swim(5);    // Мурка не умеет плавать.

        // 3. Выводим счётчики
        System.out.println("Всего животных: " + Animal.getTotalAnimals());
        System.out.println("Собак: " + Animal.getTotalDogs());
        System.out.println("Котов: " + Animal.getTotalCats());

        // 4. Работа с миской и кормлением котов
        Bowl bowl = new Bowl(30); // в миске 30 еды
        System.out.println("\n=== Кормление котов ===");
        System.out.println("В миске " + bowl.getFood() + " еды.");

        // Каждый кот пытается съесть разное количество
        cat1.eat(bowl, 15); // Мурка съест 15, станет сытой
        cat2.eat(bowl, 20); // Васька хочет 20, но в миске осталось 15 (не хватит)
        cat3.eat(bowl, 10); // Рыжик хочет 10, в миске осталось 15, хватает

        // 5. Выводим информацию о котах
        System.out.println("\n=== Состояние котов ===");
        cat1.info();
        cat2.info();
        cat3.info();

        // 6. Добавляем еду в миску
        bowl.addFood(20);
        System.out.println("В миске теперь " + bowl.getFood() + " еды.");

        // 7. Попробуем снова покормить голодного кота
        cat2.eat(bowl, 15); // Васька теперь сможет поесть
        System.out.println("Состояние Васьки после добавления еды:");
        cat2.info();
    }
}
