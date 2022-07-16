package pl.laskowski.marcin.model

import pl.laskowski.marcin.model.matrix.ListMatrix
import pl.laskowski.marcin.model.matrix.Matrix

class Board(
    val sizeX: Int,
    val sizeY: Int,
    valueInitializer: (x: Int, y: Int) -> Field?
) {
    private val fields: Matrix<Field?> = ListMatrix(
        sizeX = sizeX,
        sizeY = sizeY,
        values = iterate(sizeX, sizeY, valueInitializer)
    )

    private fun <T> iterate(sizeX: Int, sizeY: Int, mapper: (x: Int, y: Int) -> T): List<T> {
        return (0 until sizeX).flatMap { x -> (0 until sizeY).map { y -> mapper(x, y) } }
    }

    fun at(x: Int, y: Int) = fields[x, y]

    fun copy(): Board {
        return Board(sizeX, sizeY) { x, y -> fields[x, y]?.copy() }
    }

    fun fields() = fields.toList()

    fun fragment(startX: Int, startY: Int, endX: Int, endY: Int): Board {
        return Board(
            sizeX = endX - startX,
            sizeY = endY - startY,
            valueInitializer = { x, y -> at(startX + x, startY + y) }
        )
    }
}