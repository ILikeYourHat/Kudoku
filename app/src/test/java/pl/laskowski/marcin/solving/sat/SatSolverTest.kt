package pl.laskowski.marcin.solving.sat

import org.junit.Assert
import org.junit.Test
import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.model.type.SudokuTypes

class SatSolverTest {

    @Test
    fun `SatSolver test`() {
        val solver = SatSolver()

        val sudoku = Sudoku(
            SudokuTypes.CLASSIC_4x4,
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