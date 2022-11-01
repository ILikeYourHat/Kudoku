package com.github.ilikeyourhat.sudokusolver.creating

import com.github.ilikeyourhat.sudokusolver.creating.SudokuRater.Difficulty
import com.github.ilikeyourhat.sudokusolver.model.Sudoku
import com.github.ilikeyourhat.sudokusolver.model.type.ClassicSquare9x9

class ClassicSudokuGenerator(difficulty: Difficulty?, percentFilled: Float?) :
    SudokuGenerator(ClassicSquare9x9, difficulty, percentFilled) {

    private val shuffler: SudokuShuffler = SudokuShuffler()

    override val filledSudoku: Sudoku
        get() = shuffler.shuffleFull(template.copy(), ClassicSquare9x9)

    companion object {
        private val template = Sudoku(ClassicSquare9x9, listOf(
            1,2,3, 4,5,6, 7,8,9,
            4,5,6, 7,8,9, 1,2,3,
            7,8,9, 1,2,3, 4,5,6,

            9,1,2, 3,4,5, 6,7,8,
            3,4,5, 6,7,8, 9,1,2,
            6,7,8, 9,1,2, 3,4,5,

            8,9,1, 2,3,4, 5,6,7,
            2,3,4, 5,6,7, 8,9,1,
            5,6,7, 8,9,1, 2,3,4
        ))
    }
}