package pl.laskowski.marcin.model

/**
 * Created by Marcin Laskowski.
 */
data class Field(
    val position: Point,
    var value: Int = EMPTY_FIELD
) {

    constructor(x: Int, y: Int): this(Point(x, y))

    fun value(): Int {
        return value
    }

    fun set(value: Int) {
        this.value = value
    }

    val isEmpty: Boolean
        get() = value == EMPTY_FIELD
    val x: Int
        get() = position.x()
    val y: Int
        get() = position.y()

    fun clear() {
        value = EMPTY_FIELD
    }

    fun haveSamePosition(field: Field): Boolean {
        return position == field.position
    }

    fun position(): Point {
        return position
    }

    companion object {
        private const val EMPTY_FIELD = 0
    }
}