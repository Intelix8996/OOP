package ru.nsu.nrepin.model;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.nrepin.Vector2;

/**
 * Class that represents game field.
 */
public class GameField {

    private final int rowsCount;
    private final int colsCount;

    private final List<List<Cell>> field = new ArrayList<>();

    /**
     * Creates new GameField with given rows and columns count.
     *
     * @param rows rows count
     * @param cols columns count
     */
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

    /**
     * Sets cell to a given type by its x and y coordinates.
     *
     * @param cell cell type
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setCell(Cell cell, int x, int y) {
        field.get(y).set(x, cell);
    }

    /**
     * Sets cell to a given type by its coordinates as a Vector2.
     *
     * @param cell cell type
     * @param pos position vector
     */
    public void setCell(Cell cell, Vector2 pos) {
        field.get(pos.getY()).set(pos.getX(), cell);
    }

    /**
     * Returns cell type by its x and y coordinates.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return cell type
     */
    public Cell getCell(int x, int y) {
        return field.get(y).get(x);
    }

    /**
     * Returns cell type by its coordinates as a Vector2.
     *
     * @param pos position vector
     * @return cell type
     */
    public Cell getCell(Vector2 pos) {
        return field.get(pos.getY()).get(pos.getX());
    }

    /**
     * Return rows count of the field.
     *
     * @return rows count
     */
    public int getRowsCount() {
        return rowsCount;
    }

    /**
     * Return columns count of the field.
     *
     * @return columns count
     */
    public int getColsCount() {
        return colsCount;
    }
}
