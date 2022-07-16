package pl.laskowski.marcin.model

import pl.laskowski.marcin.model.matrix.ListMatrix
import pl.laskowski.marcin.model.matrix.Matrix

data class Board(
    private val fields: Matrix<Field?>
) {

    constructor(
        sizeX: Int,
        sizeY: Int,
        valueInitializer: (x: Int, y: Int) -> Field?
    ) : this(
        ListMatrix(
            sizeX = sizeX,
            sizeY = sizeY,
            values = (0 until sizeX)
                .flatMap { x ->
                    (0 until sizeY)
                        .map { y -> valueInitializer(x, y) }
                }
        )
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
}