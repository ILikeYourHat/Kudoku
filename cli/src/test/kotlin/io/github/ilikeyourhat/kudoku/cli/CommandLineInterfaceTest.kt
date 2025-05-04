package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.testing.CliktCommandTestResult
import com.github.ajalt.clikt.testing.test
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.string.shouldMatch
import kotlin.test.Test

class CommandLineInterfaceTest {

    @Test
    fun `should solve given sudoku`() {
        val result = runCommand("solve $SUDOKU_UNSOLVED")

        result.singleLineOutput()
            .shouldBeEqual(SUDOKU_SOLVED)
    }

    @Test
    fun `should generate Sudoku with given type`() {
        val result = runCommand("generate classic_4x4")

        result.singleLineOutput()
            .shouldMatch(classic4x4Regex)
    }

    @Test
    fun `should generate multiple Sudoku's with given type`() {
        val result = runCommand("generate classic_4x4 --count 3")

        result.multipleLinesOutput()
            .shouldHaveSize(3)
            .shouldForAll { it.shouldMatch(classic4x4Regex) }
    }

    @Test
    fun `should generate Sudoku with given type and difficulty`() {
        val result = runCommand("generate classic_9x9 --difficulty medium")

        val output = result.singleLineOutput()

        output.shouldMatch(classic9x9Regex)

        runCommand("rate $output")
            .singleLineOutput()
            .shouldBeEqual("medium")
    }

    @Test
    fun `should rate sudoku with given type`() {
        val result = runCommand("rate $SUDOKU_UNSOLVED")

        result.singleLineOutput()
            .shouldBeEqual("easy")
    }

    private fun runCommand(argv: String): CliktCommandTestResult {
        return rootCommand.test(argv)
    }

    private fun CliktCommandTestResult.singleLineOutput(): String {
        return output.trim().lines().single()
    }

    private fun CliktCommandTestResult.multipleLinesOutput(): List<String> {
        return output.trim().lines()
    }

    private companion object {
        const val SUDOKU_UNSOLVED = "000000907000420180000705026100904000050000040000507009920108000034059000507000000"
        const val SUDOKU_SOLVED = "462831957795426183381795426173984265659312748248567319926178534834259671517643892"
        val classic4x4Regex = """^([0-4]){16}$""".toRegex()
        val classic9x9Regex = """^([0-9]){81}$""".toRegex()
    }
}
