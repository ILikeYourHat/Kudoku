package io.github.ilikeyourhat.kudoku.model

import io.github.ilikeyourhat.kudoku.model.matrix.ListMatrix
import io.github.ilikeyourhat.kudoku.model.matrix.MutableMatrix
import java.util.NoSuchElementException

data class Board(
    private val cells: MutableMatrix<Cell?>
) {

    constructor(sizeX: Int, sizeY: Int) : this(
        ListMatrix<Cell?>(
            sizeX = sizeX,
            sizeY = sizeY,
            defaultValue = null
        )
    )

    constructor(sizeX: Int, sizeY: Int, valueInitializer: (x: Int, y: Int) -> Cell?) : this(
        ListMatrix<Cell?>(
            sizeX = sizeX,
            sizeY = sizeY,
            defaultValue = null
        ).also { matrix ->
            for (x in 0 until sizeX) {
                for (y in 0 until sizeY) {
                    matrix[x, y] = valueInitializer(x, y)
                }
            }
        }
    )

    constructor(sizeX: Int, sizeY: Int, values: List<Int?>) : this(
        ListMatrix<Cell?>(
            sizeX = sizeX,
            sizeY = sizeY,
            defaultValue = null
        ).also { matrix ->
            require(matrix.size == values.size) {
                "Incorrect data count, expected ${matrix.size}, but was ${values.size}"
            }
            values.forEachIndexed { index, value ->
                if (value != null) {
                    val (x, y) = matrix.coordinatesOf(index)
                    matrix[x, y] = Cell(x, y).also { it.set(value) }
                }
            }
        }
    )

    fun getOrNull(x: Int, y: Int) = cells[x, y]

    fun get(x: Int, y: Int): Cell {
        return cells[x, y] ?: throw NoSuchElementException("Missing cell at position $x,$y")
    }

    fun sizeX() = cells.sizeX

    fun sizeY() = cells.sizeY

    fun copy(): Board {
        return Board(cells.sizeX, cells.sizeY) { x, y -> cells[x, y]?.copy() }
    }

    fun cells() = cells.toList().filterNotNull()

    fun fragment(startX: Int, startY: Int, endX: Int, endY: Int): Board {
        return Board(
            sizeX = endX - startX,
            sizeY = endY - startY,
            valueInitializer = { x, y -> initCell(startX + x, startY + y) }
        )
    }

    fun region(startX: Int, startY: Int, endX: Int, endY: Int): Region {
        val cells = (startX..endX).flatMap { indexX ->
            (startY..endY).map { indexY ->
                initCell(indexX, indexY)
            }
        }
        return Region(cells)
    }

    private fun initCell(x: Int, y: Int): Cell {
        return cells[x, y] ?: Cell(x, y).also { cells[x, y] = it }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (y in 0 until cells.sizeY) {
            sb.append('|')
            for (x in 0 until cells.sizeX) {
                val cell = cells[x, y]
                if (cell == null) {
                    sb.append('#')
                } else if (cell.isEmpty) {
                    sb.append('_')
                } else {
                    sb.append(cell.value)
                }
                sb.append(',')
            }
            sb.deleteCharAt(sb.length - 1)
        }
        sb.append('|')
        return sb.toString()
    }
}
