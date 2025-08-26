package io.github.ilikeyourhat.kudoku.cli

import io.github.ilikeyourhat.kudoku.parsing.EmptyCellIndicator
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.string.shouldMatch
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
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

    @ParameterizedTest
    @EnumSource(EmptyCellIndicator::class)
    fun `should return Sudoku with given indicator as output`(indicator: EmptyCellIndicator) {
        val char = indicator.value
        val name = indicator.name

        // provide invalid sudoku so it would remain unsolved
        val result = runCommand("solve --empty-indicator=$name 1100000000000000")

        result.statusCode.shouldBeEqual(0)
        result.output
            .lines().first()
            .shouldMatch("""^([1-4$char]){16}$""".toRegex())
    }

    @Test
    fun `should fail if solver is not available`() {
        val result = runCommand("solve --solver unknown_solver $UNSOLVED_CLASSIC_4X4")

        result.shouldFailWith(
            "Error: invalid value for --solver: invalid choice: unknown_solver. " +
                "(choose from sat, bruteforce, deduction)"
        )
    }

    @Test
    fun `should fail if type is not available`() {
        val result = runCommand("solve --type unknown_type $UNSOLVED_CLASSIC_4X4")

        result.shouldFailWith("Error: invalid value for --type: unknown_type. Supported types are: $supportedTypes")
    }
}
