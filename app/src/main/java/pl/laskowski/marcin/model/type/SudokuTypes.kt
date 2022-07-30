package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.type.ISudokuVariant

object SudokuTypes {
    val CLASSIC_1x1: ISudokuVariant = ClassicSquare1x1()
    val CLASSIC_2x2: ISudokuVariant = ClassicSquare2x2()
    val CLASSIC_4x4: ISudokuVariant = ClassicSquare4x4()
    val CLASSIC_9x9: ISudokuVariant = ClassicSquare9x9()
    val CLASSIC_16x16: ISudokuVariant = ClassicSquare16x16()
    val CLASSIC_25x25: ISudokuVariant = ClassicSquare25x25()

    val DIAGONAL_9x9: ISudokuVariant = DiagonalSquare9x9()
}