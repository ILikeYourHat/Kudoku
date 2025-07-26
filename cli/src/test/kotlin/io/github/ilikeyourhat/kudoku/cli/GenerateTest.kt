package io.github.ilikeyourhat.kudoku.cli

import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.string.shouldMatch
import org.junit.jupiter.api.Test

class GenerateTest {

    @Test
    fun `should generate Sudoku with given type`() {
        val result = runCommand("generate classic_4x4")

        result.shouldSucceedWith(classic4x4Regex)
    }

    @Test
    fun `should generate multiple Sudoku's with given type`() {
        val result = runCommand("generate classic_4x4 --count 3")

        result.output.trim().lines()
            .shouldHaveSize(3)
            .shouldForAll { it.shouldMatch(classic4x4Regex) }
    }

    @Test
    fun `should generate Sudoku using provided seed`() {
        val result1 = runCommand("generate classic_4x4 --seed 123456789")

        result1.shouldSucceedWith(classic4x4Regex)

        val result2 = runCommand("generate classic_4x4 --seed 123456789")

        result1.shouldBeEqual(result2)
    }

    @Test
    fun `should fail to generate Sudoku with unknown type`() {
        val result = runCommand("generate unknown_type")

        result.shouldFailWith(
            "Error: invalid value for <type>: unknown_type. Run `help types` to see supported Sudoku types."
        )
    }
}
