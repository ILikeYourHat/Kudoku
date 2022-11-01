package com.github.ilikeyourhat.sudokusolver.solving.deduction

import org.junit.Assert
import org.junit.Test
import com.github.ilikeyourhat.sudokusolver.model.Sudoku
import com.github.ilikeyourhat.sudokusolver.model.type.ClassicSquare4x4
import com.github.ilikeyourhat.sudokusolver.solving.deduction.solver.DeductionSolverV3

class DeductionSolverTest {

    @Test
    fun `classic BruteForce`() {
        val solver = DeductionSolverV3()

        val sudoku = Sudoku(
            ClassicSquare4x4,
            listOf(
                0, 2, 3, 0,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )

        val result = solver.solve(sudoku)

        Assert.assertEquals(
            listOf(
                4, 2, 3, 1,
                1, 3, 2, 4,
                3, 1, 4, 2,
                2, 4, 1, 3
            ),
            result.values()
        )
    }

}