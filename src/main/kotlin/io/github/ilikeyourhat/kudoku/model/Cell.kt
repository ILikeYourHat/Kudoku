package io.github.ilikeyourhat.kudoku.model

import kotlin.math.abs

data class Cell(
    val position: Point,
    var value: Int = EMPTY_CELL
) {

    constructor(x: Int, y: Int) : this(Point(x, y))

    fun value() = value

    fun set(value: Int) {
        this.value = value
    }

    val isEmpty: Boolean
        get() = value == EMPTY_CELL

    val x = position.x
    val y = position.y

    fun clear() {
        value = EMPTY_CELL
    }

    fun position() = position

    fun isAdjacent(other: Cell): Boolean {
        return (x == other.x && abs(y - other.y) == 1) ||
            (y == other.y && abs(x - other.x) == 1)
    }

    companion object {
        private const val EMPTY_CELL = 0
    }
}
