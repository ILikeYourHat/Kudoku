package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.testing.CliktCommandTestResult
import com.github.ajalt.clikt.testing.test
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.string.shouldMatch
import kotlin.test.Test

class CommandLineInterfaceTest {

    @Test
    fun `should solve given sudoku`() {
        val result = runCommand("solve $UNSOLVED_CLASSIC")

        result.singleLineOutput()
            .shouldBeEqual(SOLVED_CLASSIC)
    }

    @Test
    fun `should solve given sudoku with given type`() {
        val result = runCommand("solve --type double_diagonal_9x9 $UNSOLVED_DD ")

        result.singleLineOutput()
            .shouldBeEqual(SOLVED_DD)
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
    fun `should fail to generate Sudoku with unknown type`() {
        val result = runCommand("generate unknown_type")

        result.multipleLinesOutput()
            .shouldContain(
                "Error: invalid value for <type>: unknown_type. Run `help types` to see supported Sudoku types."
            )
    }

    @Test
    fun `should rate given sudoku`() {
        val result = runCommand("rate $UNSOLVED_CLASSIC")

        result.singleLineOutput()
            .shouldBeEqual("easy")
    }

    @Test
    fun `should rate given sudoku with given type`() {
        val result = runCommand("rate --type double_diagonal_9x9 $UNSOLVED_DD")

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
        const val UNSOLVED_CLASSIC = "000000907000420180000705026100904000050000040000507009920108000034059000507000000"
        const val SOLVED_CLASSIC = "462831957795426183381795426173984265659312748248567319926178534834259671517643892"
        const val UNSOLVED_DD = "080010400900000030006700000070006000004500000000000040200070800000000307000091000"
        const val SOLVED_DD = "387912465951684732426753189573146928814529673692837541239475816145268397768391254"
        val classic4x4Regex = """^([0-4]){16}$""".toRegex()
        val classic9x9Regex = """^([0-9]){81}$""".toRegex()
    }
}
