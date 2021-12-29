package pl.laskowski.marcin.model.matrix

import java.util.function.BiConsumer
import java.util.function.Consumer

/**
 * Created by Marcin Laskowski.
 */
interface Matrix<E> {
    fun sizeX(): Int
    fun sizeY(): Int
    operator fun get(x: Int, y: Int): E?
    fun put(elem: E, x: Int, y: Int)
    val isEmpty: Boolean
    operator fun contains(o: E): Boolean
    fun remove(o: E): Boolean
    fun clear()
    fun forEach(consumer: BiConsumer<Int, Int>)
    fun forEachX(consumer: Consumer<Int>)
    fun forEachY(consumer: Consumer<Int>)
}