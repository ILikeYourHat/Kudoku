package io.github.ilikeyourhat.kudoku.model

import kotlin.math.abs

data class Cell(
    val x: Int,
    val y: Int,
    var value: Int = EMPTY_CELL
) {
    val position = x to y

    fun set(value: Int) {
        this.value = value
    }

    fun isEmpty() = value == EMPTY_CELL

    fun clear() {
        value = EMPTY_CELL
    }

    fun isAdjacent(other: Cell): Boolean {
        return (x == other.x && abs(y - other.y) == 1) ||
            (y == other.y && abs(x - other.x) == 1)
    }

    companion object {
        private const val EMPTY_CELL = 0
    }
}
