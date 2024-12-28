package io.github.ilikeyourhat.kudoku.parsing

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.type.BUILD_IN_TYPES
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.github.ilikeyourhat.kudoku.type.SamuraiClassic21x21
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class SingleLineSudokuParserTest {

    private val parser = SingleLineSudokuParser()

    @ParameterizedTest
    @ValueSource(
        strings = [
            "0010300004001040",
            "..1.3....4..1.4.",
            "XX1X3XXXX4XX1X4X",
            "**1*3****4**1*4*",
            "__1_3____4__1_4_",
            "  1 3    4  1 4 "
        ]
    )
    fun `should handle multiple empty field indicators`(encodedSudoku: String) {
        val sudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 0, 1, 0,
                3, 0, 0, 0,
                0, 4, 0, 0,
                1, 0, 4, 0
            )
        )

        parser.fromText(encodedSudoku)
            .shouldBeEqual(sudoku)
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "classic_4x4, 1234",
            "classic_6x6, 123456",
            "classic_9x9, 123456789",
            "classic_12x12, 123456789ABC",
            "classic_12x12, 123456789abc",
            "classic_16x16, 123456789ABCDEFG",
            "classic_16x16, 123456789abcdefg",
            "classic_25x25, 123456789ABCDEFGHIJKLMNOP",
            "classic_25x25, 123456789abcdefghijklmnop"
        ]
    )
    fun `should decode different sudoku types`(type: String, possibleValues: String) {
        val encodedSudoku = possibleValues.repeat(possibleValues.length)

        val sudoku = parser.fromText(encodedSudoku)

        sudoku.type.name
            .shouldBeEqual(type)
        sudoku.isCompleted()
            .shouldBeTrue()
        sudoku.values()
            .distinct()
            .shouldHaveSize(possibleValues.length)
    }

    @Test
    fun `should throw exception when decoding wrong length`() {
        val encodedSudoku = "1234123412"

        shouldThrow<IllegalArgumentException> {
            parser.fromText(encodedSudoku)
        }.shouldHaveMessage("Unsupported sudoku type with input length 10")
    }

    @Test
    fun `should throw exception when decoding unsupported value`() {
        val encodedSudoku = "..1.&....4..1.4."

        shouldThrow<IllegalArgumentException> {
            parser.fromText(encodedSudoku)
        }.shouldHaveMessage("Value & is not supported")
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "ZERO|0010300004001040",
            "DOT|..1.3....4..1.4.",
            "X|XX1X3XXXX4XX1X4X",
            "ASTERISK|**1*3****4**1*4*",
            "UNDERSCORE|__1_3____4__1_4_",
            "SPACE|  1 3    4  1 4 "
        ],
        delimiter = '|',
        ignoreLeadingAndTrailingWhitespace = false
    )
    fun `should handle multiple empty field indicators`(
        emptyFieldIndicator: EmptyFieldIndicator,
        expectedString: String
    ) {
        val sudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 0, 1, 0,
                3, 0, 0, 0,
                0, 4, 0, 0,
                1, 0, 4, 0
            )
        )

        parser.toText(sudoku, emptyFieldIndicator)
            .shouldBeEqual(expectedString)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "classic_4x4",
            "classic_6x6",
            "classic_9x9",
            "classic_12x12",
            "classic_16x16",
            "classic_25x25"
        ]
    )
    fun `should encode different sudoku types`(typeName: String) {
        val type = BUILD_IN_TYPES.single { it.name == typeName }
        val values = (1..type.maxValue)
            .flatMap { (1..type.maxValue) }
        val sudoku = Sudoku(type, values)

        val possibleValues = "123456789ABCDEFGHIJKLMNOP"
            .slice(0 until type.maxValue)

        val encodedSudoku = parser.toText(sudoku, EmptyFieldIndicator.ZERO)

        encodedSudoku
            .shouldBeEqual(possibleValues.repeat(possibleValues.length))
    }

    @Test
    fun `should throw exception when encoding unsupported type`() {
        val sudoku = Sudoku(SamuraiClassic21x21)

        shouldThrow<IllegalArgumentException> {
            parser.toText(sudoku, EmptyFieldIndicator.ZERO)
        }.shouldHaveMessage("Unsupported sudoku type: samurai_classic_21x21")
    }
}
