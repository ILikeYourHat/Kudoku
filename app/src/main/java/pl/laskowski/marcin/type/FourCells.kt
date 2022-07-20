package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.dividers.SudokuDivider
import pl.laskowski.marcin.model.dividers.RowDivider
import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.model.dividers.ColumnDivider
import pl.laskowski.marcin.model.dividers.ComplexDivider

class FourCells : SudokuVariant(2, 2) {

    override fun regionSize(): Int {
        return 2
    }

    override fun divider(): SudokuDivider {
        return ComplexDivider(
            RowDivider(),
            ColumnDivider()
        )
    }

    override fun template(): Sudoku {
        return Sudoku(this)
    }
}