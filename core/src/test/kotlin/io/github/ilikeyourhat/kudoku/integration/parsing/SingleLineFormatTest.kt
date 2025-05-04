package io.github.ilikeyourhat.kudoku.integration.parsing

import io.github.ilikeyourhat.kudoku.Kudoku
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.parsing.EmptyCellIndicator
import io.github.ilikeyourhat.kudoku.parsing.toSingleLineString
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test

class SingleLineFormatTest {

    @Test
    fun `should handle simple encoding`() {
        val sudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 0, 1, 0,
                3, 0, 0, 0,
                0, 4, 0, 0,
                1, 0, 4, 0
            )
        )

        sudoku.toSingleLineString()
            .shouldBeEqual("0010300004001040")
        sudoku.toSingleLineString(emptyCellIndicator = EmptyCellIndicator.DOT)
            .shouldBeEqual("..1.3....4..1.4.")
    }

    @Test
    fun `should handle simple decoding`() {
        val expectedSudoku = Sudoku(
            Classic4x4,
            listOf(
                0, 0, 1, 0,
                3, 0, 0, 0,
                0, 4, 0, 0,
                1, 0, 4, 0
            )
        )

        Kudoku.createFromSingleLineString("0010300004001040")
            .shouldBeEqual(expectedSudoku)
        Kudoku.createFromSingleLineString("..1.3....4..1.4.")
            .shouldBeEqual(expectedSudoku)
    }
}
