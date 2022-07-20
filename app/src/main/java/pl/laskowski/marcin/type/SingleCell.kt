package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.dividers.SudokuDivider
import pl.laskowski.marcin.model.dividers.RowDivider
import pl.laskowski.marcin.model.Sudoku

class SingleCell : SudokuVariant(1, 1) {

    override fun regionSize(): Int {
        return 1
    }

    override fun divider(): SudokuDivider {
        return RowDivider()
    }

    override fun template(): Sudoku {
        return Sudoku(this)
    }
}