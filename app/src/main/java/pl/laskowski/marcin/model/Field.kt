package pl.laskowski.marcin.model

data class Field(
    val position: Point,
    var value: Int = EMPTY_FIELD
) {

    constructor(x: Int, y: Int): this(Point(x, y))

    fun value() = value

    fun set(value: Int) {
        this.value = value
    }

    val isEmpty: Boolean
        get() = value == EMPTY_FIELD

    val x = position.x
    val y = position.y

    fun clear() {
        value = EMPTY_FIELD
    }

    fun haveSamePosition(field: Field): Boolean {
        return position == field.position
    }

    fun position() = position

    companion object {
        private const val EMPTY_FIELD = 0
    }
}