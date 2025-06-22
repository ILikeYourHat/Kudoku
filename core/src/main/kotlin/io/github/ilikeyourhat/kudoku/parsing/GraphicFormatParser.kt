package io.github.ilikeyourhat.kudoku.parsing

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.matrix.ListMatrix
import io.github.ilikeyourhat.kudoku.model.matrix.Matrix
import io.github.ilikeyourhat.kudoku.model.matrix.MutableMatrix
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.github.ilikeyourhat.kudoku.type.Classic6x6
import io.github.ilikeyourhat.kudoku.type.Classic6x6Vertical
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import io.github.ilikeyourhat.kudoku.type.DoubleDiagonal9x9
import io.github.ilikeyourhat.kudoku.type.Square1x1
import io.github.ilikeyourhat.kudoku.type.Square2x2

class GraphicFormatParser {

    fun toText(sudoku: Sudoku): String {
        val (blockSizeX, blockSizeY) = getBlocksSize(sudoku.type)

        val matrix = ListMatrix(sudoku.sizeX() * 2 + 1, sudoku.sizeY() * 2 + 1, "")
        matrix.fill { x, y ->
            when {
                x % 2 == 0 && y % 2 == 0 -> crossroads(x, y, matrix.sizeX, matrix.sizeY, blockSizeX, blockSizeY)
                x % 2 == 0 || y % 2 == 0 -> lines(x, y, matrix.sizeX, matrix.sizeY, blockSizeX, blockSizeY)
                else -> valueToString(sudoku[x / 2, y / 2].value)
            }
        }
        return matrix.rows()
            .joinToString("\n") { it.joinToString("") }
    }

    private fun <E> MutableMatrix<E>.fill(block: (Int, Int) -> E) {
        for (x in 0 until this.sizeX) {
            for (y in 0 until this.sizeY) {
               this[x,y] = block(x, y)
            }
        }
    }

    private fun getBlocksSize(type: SudokuType): Pair<Int, Int> {
        return when (type) {
            Square1x1 -> 1 to 1
            Square2x2 -> 2 to 2
            Classic4x4 -> 2 to 2
            Classic6x6 -> 3 to 2
            Classic6x6Vertical -> 2 to 3
            Classic9x9 -> 3 to 3
            DoubleDiagonal9x9 -> 3 to 3
            else -> throw UnsupportedOperationException("Unsupported Sudoku type: $type")
        }
    }
}

private fun crossroads(
    x: Int,
    y: Int,
    sizeX: Int,
    sizeY: Int,
    blockSizeX: Int,
    blockSizeY: Int
): String {
    return when {
        x == 0 && y == 0 -> "╔"
        x == 0 && y == sizeY - 1 -> "╚"
        x == sizeX - 1 && y == 0 -> "╗"
        x == sizeX - 1 && y == sizeY - 1 -> "╝"
        x % (2 * blockSizeX) == 0 && y == 0 -> "╦"
        x % 2 == 0 && y == 0 -> "╤"
        x % (2 * blockSizeX) == 0 && y == sizeY - 1 -> "╩"
        x % 2 == 0 && y == sizeY - 1 -> "╧"
        x == 0 && y % (2 * blockSizeY) == 0 -> "╠"
        y % 2 == 0 && x == 0 -> "╟"
        x == sizeX - 1 && y % (2 * blockSizeY) == 0 -> "╣"
        y % 2 == 0 && x == sizeX - 1 -> "╢"
        x % (2 * blockSizeX) == 0 && y % (2 * blockSizeY) == 0 -> "╬"
        x % 2 == 0 && y % (2 * blockSizeY) == 0 -> "╪"
        x % (2 * blockSizeX) == 0 && y % 2 == 0 -> "╫"
        else -> "┼"
    }
}

private fun lines(x: Int, y: Int, sizeX: Int, sizeY: Int, blockSizeX: Int, blockSizeY: Int): String {
    return when {
        x == 0 || x == sizeX - 1 -> "║"
        y == 0 || y == sizeY - 1 -> "═"
        y % (2 * blockSizeY) == 0 -> "═"
        y % 2 == 0 -> "─"
        x % (2 * blockSizeX) == 0 -> "║"
        x % 2 == 0 -> "│"
        else -> throw IllegalStateException("Unexpected coordinates: ($x, $y) in size ($sizeX, $sizeY)")
    }
}

private fun valueToString(value: Int): String {
    return if (value == 0) " " else value.toString()
}

private fun <E> Matrix<E>.rows(): List<List<E>> {
    return (0 until this.sizeY).map { y ->
        (0 until this.sizeX).map { x ->
            this[x, y]
        }
    }
}
