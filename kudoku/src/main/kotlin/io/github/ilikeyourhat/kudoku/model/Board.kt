package io.github.ilikeyourhat.kudoku.model

import io.github.ilikeyourhat.kudoku.model.matrix.ListMatrix
import io.github.ilikeyourhat.kudoku.model.matrix.Matrix

data class Board(
    private val fields: Matrix<Field?>
) {

    constructor(sizeX: Int, sizeY: Int, valueInitializer: (x: Int, y: Int) -> Field?) : this(
        ListMatrix<Field?>(
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
        ListMatrix<Field?>(
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
                    matrix[x, y] = Field(x, y).also { it.set(value) }
                }
            }
        }
    )

    fun at(x: Int, y: Int) = fields[x, y]

    fun sizeX() = fields.sizeX

    fun sizeY() = fields.sizeY

    fun copy(): Board {
        return Board(fields.sizeX, fields.sizeY) { x, y -> fields[x, y]?.copy() }
    }

    fun fields() = fields.toList()

    fun fragment(startX: Int, startY: Int, endX: Int, endY: Int): Board {
        return Board(
            sizeX = endX - startX,
            sizeY = endY - startY,
            valueInitializer = { x, y -> at(startX + x, startY + y) }
        )
    }

    fun region(startX: Int, startY: Int, endX: Int, endY: Int): Region {
        val fields = (startX..endX).flatMap { indexX ->
            (startY..endY).mapNotNull { indexY ->
                at(indexX, indexY)
            }
        }
        return Region(fields)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (y in 0 until fields.sizeY) {
            sb.append('|')
            for (x in 0 until fields.sizeX) {
                val field = fields[x, y]
                if (field == null) {
                    sb.append('#')
                } else if (field.isEmpty) {
                    sb.append('_')
                } else {
                    sb.append(field.value)
                }
                sb.append(',')
            }
            sb.deleteCharAt(sb.length - 1)
        }
        sb.append('|')
        return sb.toString()
    }
}
