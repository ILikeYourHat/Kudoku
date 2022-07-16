package pl.laskowski.marcin.solving.bruteforce

import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.type.ClassicSquare

class BruteForceSolverTest {

    @Test
    fun `classic BruteForce`() {
        val solver = BruteForceSolver(ClassicSquare(4))

        val sudoku = Sudoku(4, 4,
            arrayOf(
                0, 2, 3, 0,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )

        val result = solver.solve(sudoku)

        assertEquals(
            listOf(
                4, 1, 3, 2,
                2, 3, 1, 4,
                3, 2, 4, 1,
                1, 4, 2, 3
            ),
            result.values()
        )
    }

    @Test
    @Ignore
    fun `concurrent BruteForce`() {
        val solver = BruteForceConcurrentSolver(ClassicSquare(4), 5, 5)

        val sudoku = Sudoku(4, 4,
            arrayOf(
                0, 2, 3, 0,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )

        val result = solver.solve(sudoku)

        assertEquals(
            listOf(
                4, 1, 3, 2,
                2, 3, 1, 4,
                3, 2, 4, 1,
                1, 4, 2, 3
            ),
            result.values()
        )
    }

    @Test
    @Ignore
    fun `concurrent BruteForce 2`() {
        val solver = BruteForceConcurrentSolver2(ClassicSquare(4), 2, 4)

        val sudoku = Sudoku(4, 4,
            arrayOf(
                0, 2, 3, 0,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )

        val result = solver.solve(sudoku)

        assertEquals(
            listOf(
                4, 1, 3, 2,
                2, 3, 1, 4,
                3, 2, 4, 1,
                1, 4, 2, 3
            ),
            result.values()
        )
    }

    @Test
    @Ignore
    fun `permutation BruteForce`() {
        val solver = BruteForcePermutationSolver(ClassicSquare(4), 2)

        val sudoku = Sudoku(4, 4,
            arrayOf(
                0, 2, 3, 0,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )

        val result = solver.solve(sudoku)

        assertEquals(
            listOf(
                4, 1, 3, 2,
                2, 3, 1, 4,
                3, 2, 4, 1,
                1, 4, 2, 3
            ),
            result.values()
        )
    }
}
