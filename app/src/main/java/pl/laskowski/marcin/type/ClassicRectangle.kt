package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.dividers.SudokuDivider
import pl.laskowski.marcin.model.dividers.ComplexDivider
import pl.laskowski.marcin.model.dividers.RowDivider
import pl.laskowski.marcin.model.dividers.ColumnDivider
import pl.laskowski.marcin.model.dividers.BlockDivider
import pl.laskowski.marcin.model.Sudoku

open class ClassicRectangle(
    width: Int,
    height: Int,
    val blockWidth: Int,
    val blockHeight: Int
) : SudokuVariant(width, height) {

    override fun regionSize(): Int {
        return blockWidth * blockHeight
    }

    override fun divider(): SudokuDivider {
        return ComplexDivider(
            RowDivider(),
            ColumnDivider(),
            BlockDivider(blockWidth, blockHeight)
        )
    }

    override fun template(): Sudoku {
        return Sudoku(this)
    }
}