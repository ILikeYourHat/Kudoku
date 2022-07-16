package pl.laskowski.marcin.solving.sat

import org.junit.Assert
import org.junit.Test
import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.type.ClassicSquare

class SatSolverTest {

    @Test
    fun `SatSolver test`() {
        val solver = SatSolver(ClassicSquare(4))

        val sudoku = Sudoku(4, 4,
            arrayOf(
                0, 2, 3, 0,
                1, 0, 0, 4,
                3, 0, 0, 2,
                0, 4, 1, 0
            )
        )

        val result = solver.solve(sudoku)

        Assert.assertEquals(
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