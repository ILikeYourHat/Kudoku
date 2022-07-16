package pl.laskowski.marcin.model.matrix

class ListMatrix<E>(
    override val sizeX: Int,
    override val sizeY: Int,
    values: List<E>
) : MutableMatrix<E> {

    constructor(sizeX: Int, sizeY: Int, defaultValue: E)
            : this(sizeX, sizeY, (0 until (sizeX * sizeY)).map { defaultValue })

    private val data = values.toMutableList()

    init {
        require(sizeX >= 0)
        require(sizeY >= 0)
        require(data.size == sizeX * sizeY)
    }

    override val size: Int = sizeX * sizeY

    override operator fun get(x: Int, y: Int): E {
        check(x in 0 until sizeX)
        check(y in 0 until sizeY)
        return data[y * sizeY + x]
    }

    override operator fun set(x: Int, y: Int, elem: E) {
        check(x in 0 until sizeX)
        check(y in 0 until sizeY)
        data[y * sizeY + x] = elem
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
        return other is ListMatrix<*>
                && sizeX == other.sizeX
                && sizeY == other.sizeY
                && data == other.data
    }

    override fun hashCode(): Int {
        var result = sizeX
        result = 31 * result + sizeY
        result = 31 * result + data.hashCode()
        return result
    }
}