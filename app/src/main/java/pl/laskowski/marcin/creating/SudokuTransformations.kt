package pl.laskowski.marcin.creating

import pl.laskowski.marcin.model.Point
import pl.laskowski.marcin.model.Sudoku

/**
 * Created by Marcin Laskowski.
 */
class SudokuTransformations {
    fun rotateLeft(origin: Sudoku): Sudoku {
        TODO()
//        return origin.copyRotatedWithIndexMapping { (x, y): Point ->
//            val newY = indexFromEnd(origin.sizeX(), x)
//            Point(y, newY)
//        }
    }

    fun rotateRight(origin: Sudoku): Sudoku {
        TODO()
//        return origin.copyRotatedWithIndexMapping { (x, y): Point ->
//            val newX = indexFromEnd(origin.sizeY(), y)
//            Point(newX, x)
//        }
    }

    fun rotate180(origin: Sudoku): Sudoku {
        TODO()
//        return origin.copyWithIndexMapping { (x1, y1): Point ->
//            val x = indexFromEnd(origin.sizeX(), x1)
//            val y = indexFromEnd(origin.sizeY(), y1)
//            Point(x, y)
//        }
    }

    fun mirrorByYAxis(origin: Sudoku): Sudoku {
        TODO()
//        return origin.copyWithIndexMapping { (x1, y): Point ->
//            val x = indexFromEnd(origin.sizeX(), x1)
//            Point(x, y)
//        }
    }

    fun mirrorByXAxis(origin: Sudoku): Sudoku {
        TODO()
//        return origin.copyWithIndexMapping { (x, y1): Point ->
//            val y = indexFromEnd(origin.sizeY(), y1)
//            Point(x, y)
//        }
    }

    fun mirrorByFirstDiagonal(origin: Sudoku): Sudoku {
        TODO()
//        return origin.copyRotatedWithIndexMapping { (x, y): Point ->
//            Point(y, x)
//        }
    }

    fun mirrorBySecondDiagonal(origin: Sudoku): Sudoku {
        TODO()
//        return origin.copyRotatedWithIndexMapping { (x1, y1): Point ->
//            val x = indexFromEnd(origin.sizeY(), y1)
//            val y = indexFromEnd(origin.sizeX(), x1)
//            Point(x, y)
//        }
    }

    fun swapRow(origin: Sudoku, y1: Int, y2: Int): Sudoku {
        TODO()
//        return origin.copyWithIndexMapping { (x, y3): Point ->
//            val y = swapIndex(y3, y1, y2)
//            Point(x, y)
//        }
    }

    fun swapRows(origin: Sudoku, y1: Int, y2: Int, size: Int): Sudoku {
        TODO()
//        return origin.copyWithIndexMapping { (x, y3): Point ->
//            val y = swapIndex(y3, y1, y2, size)
//            Point(x, y)
//        }
    }

    fun swapColumn(origin: Sudoku, x1: Int, x2: Int): Sudoku {
        TODO()
//        return origin.copyWithIndexMapping { (x3, y): Point ->
//            val x = swapIndex(x3, x1, x2)
//            Point(x, y)
//        }
    }

    fun swapColumns(origin: Sudoku, x1: Int, x2: Int, size: Int): Sudoku {
        TODO()
//        return origin.copyWithIndexMapping { (x3, y): Point ->
//            val x = swapIndex(x3, x1, x2, size)
//            Point(x, y)
//        }
    }

    private fun indexFromEnd(size: Int, index: Int): Int {
        return size - 1 - index
    }

    private fun swapIndex(current: Int, index1: Int, index2: Int): Int {
        return when (current) {
            index1 -> index2
            index2 -> index1
            else -> current
        }
    }

    private fun swapIndex(current: Int, index1: Int, index2: Int, size: Int): Int {
        return if (current >= index1 && current < index1 + size) {
            val padding = current - index1
            index2 + padding
        } else if (current >= index2 && current < index2 + size) {
            val padding = current - index2
            index1 + padding
        } else {
            current
        }
    }
}