package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.dividers.SudokuDivider
import pl.laskowski.marcin.model.dividers.ComplexDivider
import pl.laskowski.marcin.model.dividers.RowDivider
import pl.laskowski.marcin.model.dividers.ColumnDivider
import pl.laskowski.marcin.model.dividers.BlockDivider
import pl.laskowski.marcin.model.dividers.MainDiagonalDivider
import pl.laskowski.marcin.model.dividers.AntidiagonalDivider
import pl.laskowski.marcin.model.Sudoku
import kotlin.math.sqrt

class DiagonalSquare(size: Int) : SudokuVariant(size, size) {

    private val blockSize = blockSize(size)

    override fun regionSize(): Int {
        return sizeY
    }

    override fun divider(): SudokuDivider {
        return ComplexDivider(
            RowDivider(),
            ColumnDivider(),
            BlockDivider(blockSize, blockSize),
            MainDiagonalDivider(),
            AntidiagonalDivider()
        )
    }

    override fun template(): Sudoku {
        return Sudoku(this)
    }

    companion object {
        private fun blockSize(size: Int): Int {
            val blockSize = sqrt(size.toDouble()).toInt()
            require(blockSize * blockSize == size)
            return blockSize
        }
    }
}