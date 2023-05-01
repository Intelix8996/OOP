package ru.nsu.nrepin;

import java.util.ArrayList;
import java.util.List;

public class GameField {

    private final int rowsCount;
    private final int colsCount;

    private final List<List<Cell>> field = new ArrayList<>();

    public GameField(int rows, int cols) {
        rowsCount = rows;
        colsCount = cols;

        for (int i = 0; i < rows; ++i) {
            field.add(new ArrayList<>());
            for (int j = 0; j < cols; ++j) {
                field.get(field.size() - 1).add(Cell.EMPTY);
            }
        }
    }

    public void setCell(Cell cell, int x, int y) {
        field.get(y).set(x, cell);
    }

    public void setCell(Cell cell, Vector2 pos) {
        field.get(pos.getY()).set(pos.getX(), cell);
    }

    public Cell getCell(int x, int y) {
        return field.get(y).get(x);
    }

    public Cell getCell(Vector2 pos) {
        return field.get(pos.getY()).get(pos.getX());
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public int getColsCount() {
        return colsCount;
    }
}
