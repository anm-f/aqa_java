package lesson2_3;

public class Main {
    public static void main(String[] args) {
        Product[] products = new Product[5];
        products[0] = new Product("Samsung S26 Ultra", "26.02.2025", "Samsung Corp.", "Korea", 1299.99, true);
        products[1] = new Product("iPhone 17 Pro Max", "19.09.2025", "Apple Inc.", "USA", 1399.99, false);
        products[2] = new Product("Xiaomi 15T", "15.09.2025", "Xiaomi", "China", 599.99, true);
        products[3] = new Product("Google Pixel 11", "12.08.2026", "Google", "USA", 1299.99, false);
        products[4] = new Product("OnePlus 13", "07.01.2025", "OnePlus", "China", 899.99, true);
        for (Product p : products) {
            p.printInfo();
        }

        Park myPark = new Park("Парк Горького (Минск)");
        myPark.addAttraction("Колесо обозрения", "10:00–22:00", 12.00);
        myPark.addAttraction("Автодром", "10:00–21:00", 8.00);
        myPark.addAttraction("Лодочная станция", "09:00–20:00", 10.00);
        myPark.addAttraction("Детская железная дорога", "10:00–19:00", 6.00);
        myPark.showAttractions();
    }
}
