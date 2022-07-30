package pl.laskowski.marcin.creating

import pl.laskowski.marcin.creating.SudokuRater.Difficulty
import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.model.type.ClassicSquare9x9
import pl.laskowski.marcin.playground.TestSet

class ClassicSudokuGenerator(difficulty: Difficulty?, percentFilled: Float?) :
    SudokuGenerator(ClassicSquare9x9(), difficulty, percentFilled) {

    private val shuffler: SudokuShuffler = SudokuShuffler()
    private val template: Sudoku = TestSet.TEST.data[0]

    override val filledSudoku: Sudoku
        get() = shuffler.shuffleFull(template.copy(), ClassicSquare9x9())
}