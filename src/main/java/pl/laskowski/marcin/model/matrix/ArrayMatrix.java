package pl.laskowski.marcin.model.matrix;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by Marcin Laskowski.
 */

public class ArrayMatrix<E> implements Matrix<E> {

    private final int sizeX;
    private final int sizeY;
    private final Object[][] data;

    public ArrayMatrix(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        data = new Object[sizeY][sizeX];
    }

    @Override
    public int sizeX() {
        return sizeX;
    }

    @Override
    public int sizeY() {
        return sizeY;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int x, int y) {
        return (E) data[y][x];
    }

    @Override
    public void put(E elem, int x, int y) {
        data[y][x] = elem;
    }

    @Override
    public boolean isEmpty() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (data[y][x] != null) return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (Objects.equals(data[y][x], o)) return true;
            }
        }
        return false;
    }

    @Override
    public void forEach(BiConsumer<Integer, Integer> consumer) {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                consumer.accept(x, y);
            }
        }
    }

    @Override
    public void forEachX(Consumer<Integer> consumer) {
        for (int x = 0; x < sizeX; x++) {
            consumer.accept(x);
        }
    }

    @Override
    public void forEachY(Consumer<Integer> consumer) {
        for (int y = 0; y < sizeY; y++) {
            consumer.accept(y);
        }
    }

    @Override
    public boolean remove(Object o) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (Objects.equals(data[y][x], o)) {
                    data[y][x] = null;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                data[y][x] = null;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayMatrix<?> matrix = (ArrayMatrix<?>) o;

        if (sizeX != matrix.sizeX) return false;
        if (sizeY != matrix.sizeY) return false;
        return Arrays.deepEquals(data, matrix.data);
    }

    @Override
    public int hashCode() {
        int result = sizeX;
        result = 31 * result + sizeY;
        result = 31 * result + Arrays.deepHashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "ArrayMatrix{" +
                "sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", data=" + Arrays.deepToString(data) +
                '}';
    }
}
