package pl.laskowski.marcin.model.dividers

import pl.laskowski.marcin.model.Board
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.type.*

class RegionDivider {

    private val dividers = mutableListOf<DivideCommand>()

    fun divideByRows() = apply {
        dividers.add { board ->
            board.divideByRows()
        }
    }

    fun divideByColumns() = apply {
        dividers.add { board ->
            board.divideByColumns()
        }
    }

    fun divideByBlocks(blockSize: Int) = divideByBlocks(blockSize, blockSize)

    fun divideByBlocks(blockSizeX: Int, blockSizeY: Int) = apply {
        dividers.add { board ->
            board.divideByBlocks(blockSizeX, blockSizeY)
        }
    }

    fun allFields() = apply {
        dividers.add { board ->
            board.allFields()
        }
    }

    fun primaryDiagonal() = apply {
        dividers.add { board ->
            board.primaryDiagonal()
        }
    }

    fun antiDiagonal() = apply {
        dividers.add { board ->
            board.antiDiagonal()
        }
    }

    fun applySubSudoku(x: Int, y: Int, type: ISudokuVariant) = apply {
        dividers.add { board ->
            val subBoard = board.fragment(x, y, x + type.sizeX - 1, y + type.sizeY - 1)
            type.divider().divide(subBoard)
        }
    }

    fun divide(board: Board): List<Region> {
        return dividers.flatMap { it.divide(board) }
            .distinct()
    }
}

fun interface DivideCommand{
    fun divide(board: Board): List<Region>
}