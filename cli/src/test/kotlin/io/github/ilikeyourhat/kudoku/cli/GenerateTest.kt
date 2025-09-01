package io.github.ilikeyourhat.kudoku.cli

import io.github.ilikeyourhat.kudoku.parsing.EmptyCellIndicator
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.string.shouldMatch
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import kotlinx.coroutines.test.runTest

class GenerateTest {

    @Test
    fun `should generate Sudoku with default type`() = runTest {
        val result = runCommand("generate")

        result.shouldSucceedWith(classic9x9Regex)
    }

    @Test
    fun `should generate Sudoku with given type`() = runTest {
        val result = runCommand("generate classic_4x4")

        result.shouldSucceedWith(classic4x4Regex)
    }

    @Test
    fun `should generate multiple Sudoku's with given type`() = runTest {
        val result = runCommand("generate classic_4x4 --count 3")

        result.output.trim().lines()
            .shouldHaveSize(3)
            .shouldForAll { it.shouldMatch(classic4x4Regex) }
    }

    @Test
    fun `should generate Sudoku using provided seed`() = runTest {
        val result1 = runCommand("generate classic_4x4 --seed 123456789")

        result1.shouldSucceedWith(classic4x4Regex)

        val result2 = runCommand("generate classic_4x4 --seed 123456789")

        result1.shouldBeEqual(result2)
    }

    @ParameterizedTest
    @EnumSource(EmptyCellIndicator::class)
    fun `should generate Sudoku with given indicator`(indicator: EmptyCellIndicator) = runTest {
        val char = indicator.value
        val name = indicator.name

        val result = runCommand("generate classic_4x4 --empty-indicator=$name")

        result.statusCode.shouldBeEqual(0)
        result.output
            .lines().first()
            .shouldMatch("""^([1-4$char]){16}$""".toRegex())
    }

    @Test
    fun `should fail to generate Sudoku with unknown type`() = runTest {
        val result = runCommand("generate unknown_type")

        result.shouldFailWith("Error: invalid value for <type>: unknown_type. Supported types are: $supportedTypes")
    }
}
