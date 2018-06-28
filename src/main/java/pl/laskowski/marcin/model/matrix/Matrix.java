package pl.laskowski.marcin.model.matrix;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by Marcin Laskowski.
 */

public interface Matrix<E> {

    int sizeX();

    int sizeY();

    E get(int x, int y);

    void put(E elem, int x, int y);

    boolean isEmpty();

    boolean contains(Object o);

    boolean remove(Object o);

    void clear();

    void forEach(BiConsumer<Integer, Integer> consumer);

    void forEachX(Consumer<Integer> consumer);

    void forEachY(Consumer<Integer> consumer);

}
