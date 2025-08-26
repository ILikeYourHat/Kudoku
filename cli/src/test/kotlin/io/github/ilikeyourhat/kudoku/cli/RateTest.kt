package io.github.ilikeyourhat.kudoku.cli

import io.kotest.matchers.string.shouldMatch
import org.junit.jupiter.api.Test

class RateTest {

    @Test
    fun `should generate Sudoku with given type and difficulty`() {
        val result = runCommand("generate classic_9x9 --difficulty medium")

        val output = result.output.trim()

        output.shouldMatch(classic9x9Regex)

        runCommand("rate $output")
            .shouldSucceedWith("medium")
    }

    @Test
    fun `should rate given sudoku`() {
        val result = runCommand("rate $UNSOLVED_CLASSIC")

        result.shouldSucceedWith("easy")
    }

    @Test
    fun `should rate given sudoku with given type`() {
        val result = runCommand("rate --type double_diagonal_9x9 $UNSOLVED_DD")

        result.shouldSucceedWith("easy")
    }

    @Test
    fun `should fail to rate given sudoku with unknown type`() {
        val result = runCommand("rate --type unknown_type $UNSOLVED_DD")

        result.shouldFailWith("Error: invalid value for --type: unknown_type. Supported types are: $supportedTypes")
    }
}
