package pl.laskowski.marcin.model.matrix

import java.util.function.BiConsumer
import java.util.function.Consumer

/**
 * Created by Marcin Laskowski.
 */

class ArrayMatrix<E>(
    private val sizeX: Int,
    private val sizeY: Int
) : Matrix<E> {

    private val data: Array<Array<Any?>> = Array(sizeY) { arrayOfNulls(sizeX) }

    override fun sizeX(): Int {
        return sizeX
    }

    override fun sizeY(): Int {
        return sizeY
    }

    override fun get(x: Int, y: Int): E? {
        @Suppress("UNCHECKED_CAST")
        return data[y][x] as E?
    }

    override fun put(elem: E?, x: Int, y: Int) {
        data[y][x] = elem
    }

    override val isEmpty: Boolean
        get() {
            for (x in 0 until sizeX) {
                for (y in 0 until sizeY) {
                    if (data[y][x] != null) return false
                }
            }
            return true
        }

    override operator fun contains(o: E): Boolean {
        for (x in 0 until sizeX) {
            for (y in 0 until sizeY) {
                if (data[y][x] == o) return true
            }
        }
        return false
    }

    override fun forEach(consumer: BiConsumer<Int, Int>) {
        for (y in 0 until sizeY) {
            for (x in 0 until sizeX) {
                consumer.accept(x, y)
            }
        }
    }

    override fun forEachX(consumer: Consumer<Int>) {
        for (x in 0 until sizeX) {
            consumer.accept(x)
        }
    }

    override fun forEachY(consumer: Consumer<Int>) {
        for (y in 0 until sizeY) {
            consumer.accept(y)
        }
    }

    override fun remove(o: E): Boolean {
        for (x in 0 until sizeX) {
            for (y in 0 until sizeY) {
                if (data[y][x] == o) {
                    data[y][x] = null
                    return true
                }
            }
        }
        return false
    }

    override fun clear() {
        for (x in 0 until sizeX) {
            for (y in 0 until sizeY) {
                data[y][x] = null
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val matrix = other as ArrayMatrix<*>
        if (sizeX != matrix.sizeX) return false
        return if (sizeY != matrix.sizeY) false else data.contentDeepEquals(matrix.data)
    }

    override fun hashCode(): Int {
        var result = sizeX
        result = 31 * result + sizeY
        result = 31 * result + data.contentDeepHashCode()
        return result
    }

    override fun toString(): String {
        return "ArrayMatrix{" +
                "sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", data=" + data.contentDeepToString() +
                '}'
    }
}