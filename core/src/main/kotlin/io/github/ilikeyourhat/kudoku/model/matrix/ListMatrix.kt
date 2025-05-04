package io.github.ilikeyourhat.kudoku.model.matrix

class ListMatrix<E>(
    override val sizeX: Int,
    override val sizeY: Int,
    values: List<E>
) : MutableMatrix<E> {

    constructor(sizeX: Int, sizeY: Int, defaultValue: E) : this(
        sizeX = sizeX,
        sizeY = sizeY,
        values = (0 until (sizeX * sizeY)).map { defaultValue }
    )

    private val data = values.toMutableList()

    init {
        require(sizeX >= 0) { "sizeX must be non-negative" }
        require(sizeY >= 0) { "sizeY must be non-negative" }
        require(data.size == sizeX * sizeY) {
            "Data size must be equal to ${sizeX * sizeY}, but was ${data.size}"
        }
    }

    override val size: Int = sizeX * sizeY

    override operator fun get(x: Int, y: Int): E {
        validateBounds(x in 0 until sizeX) { "x must be in range [0, ${sizeX - 1}], but was $x" }
        validateBounds(y in 0 until sizeY) { "y must be in range [0, ${sizeY - 1}], but was $y" }
        return data[y * sizeX + x]
    }

    override operator fun set(x: Int, y: Int, elem: E) {
        validateBounds(x in 0 until sizeX) { "x must be in range [0, ${sizeX - 1}], but was $x" }
        validateBounds(y in 0 until sizeY) { "y must be in range [0, ${sizeY - 1}], but was $y" }
        data[y * sizeX + x] = elem
    }

    override fun isEmpty(): Boolean {
        return data.isEmpty()
    }

    override operator fun contains(element: E): Boolean {
        return data.contains(element)
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        return data.containsAll(elements)
    }

    override fun iterator() = data.iterator()

    override fun equals(other: Any?): Boolean {
        return other is ListMatrix<*> &&
            sizeX == other.sizeX &&
            sizeY == other.sizeY &&
            data == other.data
    }

    override fun hashCode(): Int {
        var result = sizeX
        result = 31 * result + sizeY
        result = 31 * result + data.hashCode()
        return result
    }

    fun coordinatesOf(index: Int): Pair<Int, Int> {
        val x = index % sizeX
        val y = index / sizeX
        return x to y
    }

    private inline fun validateBounds(value: Boolean, lazyMessage: () -> String) {
        if (!value) throw IndexOutOfBoundsException(lazyMessage())
    }
}
