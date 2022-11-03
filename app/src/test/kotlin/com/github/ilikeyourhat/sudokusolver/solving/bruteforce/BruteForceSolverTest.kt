package com.github.ilikeyourhat.sudokusolver.solving.bruteforce

import com.github.ilikeyourhat.sudokusolver.model.Sudoku
import com.github.ilikeyourhat.sudokusolver.model.type.ClassicSquare4x4
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class BruteForceSolverTest {

    @Test
    fun `classic BruteForce`() {
        val solver = BruteForceSolver()

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

    @Test
    @Disabled
    fun `concurrent BruteForce`() {
        val solver = BruteForceConcurrentSolver(5, 5)

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

    @Test
    @Disabled
    fun `concurrent BruteForce 2`() {
        val solver = BruteForceConcurrentSolver2(2, 4)

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
