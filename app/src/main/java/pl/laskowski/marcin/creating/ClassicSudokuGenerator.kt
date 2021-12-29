package pl.laskowski.marcin.creating

import pl.laskowski.marcin.creating.SudokuRater.Difficulty
import pl.laskowski.marcin.type.ClassicSquare
import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.playground.TestSet

/**
 * Created by Marcin Laskowski.
 */
class ClassicSudokuGenerator(difficulty: Difficulty?, percentFilled: Float?) :

    SudokuGenerator(ClassicSquare(9), difficulty, percentFilled) {

    private val shuffler: SudokuShuffler = SudokuShuffler()
    private val template: Sudoku = TestSet.TEST.data[0]

    override val filledSudoku: Sudoku
        get() = shuffler.shuffleFull(template.copy(), ClassicSquare(9))
}