package pl.laskowski.marcin.type

object SudokuTypes {
    val CLASSIC_1x1: SudokuVariant = SingleCell()
    val CLASSIC_2x2: SudokuVariant = FourCells()
    val CLASSIC_4x4: SudokuVariant = ClassicSquare(4)
    val CLASSIC_9x9: SudokuVariant = ClassicSquare(9)
}