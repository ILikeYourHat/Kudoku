package io.github.ilikeyourhat.kudoku.cli

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SolveTest {

    @Test
    fun `should solve given sudoku`() {
        val result = runCommand("solve $UNSOLVED_CLASSIC")

        result.shouldSucceedWith(SOLVED_CLASSIC)
    }

    @Test
    fun `should solve given sudoku with given type`() {
        val result = runCommand("solve --type double_diagonal_9x9 $UNSOLVED_DD ")

        result.shouldSucceedWith(SOLVED_DD)
    }

    @ParameterizedTest
    @ValueSource(strings = ["sat", "bruteforce", "deduction"])
    fun `should solve given sudoku with given solver`(solver: String) {
        val result = runCommand("solve --solver $solver $UNSOLVED_CLASSIC_4X4")

        result.shouldSucceedWith(SOLVED_CLASSIC_4X4)
    }

    @Test
    fun `should fail if solver is not available`() {
        val result = runCommand("solve --solver unknown_solver $UNSOLVED_CLASSIC_4X4")

        result.shouldFailWith(
            "Error: invalid value for --solver: unknown_solver. " +
                "Run `help solvers` to see supported Sudoku solvers."
        )
    }
}
