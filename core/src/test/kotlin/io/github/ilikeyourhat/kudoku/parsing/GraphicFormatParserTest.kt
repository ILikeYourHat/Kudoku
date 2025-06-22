package io.github.ilikeyourhat.kudoku.parsing

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.type.Classic6x6Vertical
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import io.github.ilikeyourhat.kudoku.type.Square1x1
import io.github.ilikeyourhat.kudoku.type.Square2x2
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GraphicFormatParserTest {

    private val parser = GraphicFormatParser()

    @Test
    fun `should handle Square1x1`() {
        val sudoku = Sudoku(Square1x1, listOf(1))

        val output = parser.toText(sudoku)

        output.shouldBe(
            """
            ╔═╗
            ║1║
            ╚═╝
            """.trimIndent()
        )
    }

    @Test
    fun `should handle empty Square1x1`() {
        val sudoku = Sudoku(Square1x1, listOf(0))

        val output = parser.toText(sudoku)

        output.shouldBe(
            """
            ╔═╗
            ║ ║
            ╚═╝
            """.trimIndent()
        )
    }

    @Test
    fun `should handle Square2x2`() {
        val sudoku = Sudoku(Square2x2, listOf(1, 2, 0, 1))

        val output = parser.toText(sudoku)

        output.shouldBe(
            """
            ╔═╤═╗
            ║1│2║
            ╟─┼─╢
            ║ │1║
            ╚═╧═╝
            """.trimIndent()
        )
    }

    @Test
    fun `should handle Classic9x9`() {
        val sudoku = Sudoku(
            Classic9x9,
            listOf(
                0, 0, 3, 0, 2, 0, 6, 0, 0,
                9, 0, 0, 3, 0, 5, 0, 0, 1,
                0, 0, 1, 8, 0, 6, 4, 0, 0,
                0, 0, 8, 1, 0, 2, 9, 0, 0,
                7, 0, 0, 0, 0, 0, 0, 0, 8,
                0, 0, 6, 7, 0, 8, 2, 0, 0,
                0, 0, 2, 6, 0, 9, 5, 0, 0,
                8, 0, 0, 2, 0, 3, 0, 0, 9,
                0, 0, 5, 0, 1, 0, 3, 0, 0
            )
        )

        val output = parser.toText(sudoku)

        output.shouldBe(
            """
            ╔═╤═╤═╦═╤═╤═╦═╤═╤═╗
            ║ │ │3║ │2│ ║6│ │ ║
            ╟─┼─┼─╫─┼─┼─╫─┼─┼─╢
            ║9│ │ ║3│ │5║ │ │1║
            ╟─┼─┼─╫─┼─┼─╫─┼─┼─╢
            ║ │ │1║8│ │6║4│ │ ║
            ╠═╪═╪═╬═╪═╪═╬═╪═╪═╣
            ║ │ │8║1│ │2║9│ │ ║
            ╟─┼─┼─╫─┼─┼─╫─┼─┼─╢
            ║7│ │ ║ │ │ ║ │ │8║
            ╟─┼─┼─╫─┼─┼─╫─┼─┼─╢
            ║ │ │6║7│ │8║2│ │ ║
            ╠═╪═╪═╬═╪═╪═╬═╪═╪═╣
            ║ │ │2║6│ │9║5│ │ ║
            ╟─┼─┼─╫─┼─┼─╫─┼─┼─╢
            ║8│ │ ║2│ │3║ │ │9║
            ╟─┼─┼─╫─┼─┼─╫─┼─┼─╢
            ║ │ │5║ │1│ ║3│ │ ║
            ╚═╧═╧═╩═╧═╧═╩═╧═╧═╝
            """.trimIndent()
        )
    }

    @Test
    fun `should handle Classic6x6Vertical`() {
        val sudoku = Sudoku(
            Classic6x6Vertical,
            listOf(
                4, 1, 3, 5, 6, 2,
                3, 6, 2, 4, 1, 5,
                2, 5, 1, 6, 3, 4,
                1, 3, 5, 2, 4, 6,
                6, 2, 4, 1, 5, 3,
                5, 4, 6, 3, 2, 1,
            )
        )

        val output = parser.toText(sudoku)

        output.shouldBe(
            """
            ╔═╤═╦═╤═╦═╤═╗
            ║4│1║3│5║6│2║
            ╟─┼─╫─┼─╫─┼─╢
            ║3│6║2│4║1│5║
            ╟─┼─╫─┼─╫─┼─╢
            ║2│5║1│6║3│4║
            ╠═╪═╬═╪═╬═╪═╣
            ║1│3║5│2║4│6║
            ╟─┼─╫─┼─╫─┼─╢
            ║6│2║4│1║5│3║
            ╟─┼─╫─┼─╫─┼─╢
            ║5│4║6│3║2│1║
            ╚═╧═╩═╧═╩═╧═╝
            """.trimIndent()
        )
    }
}
