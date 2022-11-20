package com.github.ilikeyourhat.kudoku.solving.deduction

import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.solving.deduction.solver.DeductionSolverV1
import com.github.ilikeyourhat.kudoku.solving.deduction.solver.DeductionSolverV3
import com.github.ilikeyourhat.kudoku.type.Classic4x4
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DeductionSolverTest {

    @Test
    fun `classic BruteForce`() {
        val solver = DeductionSolverV1()

        val sudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 2, 3, 0,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )

        val result = solver.solve(sudoku)

        assertEquals(
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