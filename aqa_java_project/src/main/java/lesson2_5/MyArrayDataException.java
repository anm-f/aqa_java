package lesson2_5;

public class MyArrayDataException extends Exception {
    private int row;
    private int col;

    public MyArrayDataException(int row, int col, String value) {
        super("Неверные данные в ячейке [" + row + "][" + col + "]: \"" + value + "\"");
        this.row = row;
        this.col = col;
    }
}
