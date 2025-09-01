package io.github.ilikeyourhat.kudoku.solving.bruteforce

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.solving.SolverContractTestTemplate
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.jupiter.api.Test
import kotlinx.coroutines.test.runTest

class BruteForceSolverTest : SolverContractTestTemplate<BruteForceSolver>(
    solver = BruteForceSolver()
) {

    @Test
    fun `should solve empty grid`() = runTest {
        val input = Sudoku(
            Classic4x4,
            listOf(
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
            )
        )
        val result = solver.solve(input)

        result.isSolved().shouldBeTrue()
    }
}
