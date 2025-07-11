package io.github.ilikeyourhat.kudoku.parsing

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.type.Classic16x16
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

    @Test
    fun `should handle Classic16x16`() {
        val sudoku = Sudoku(
            Classic16x16,
            listOf(
                0, 0, 7, 0, 16, 0, 14, 0, 0, 0, 15, 8, 0, 0, 0, 0,
                4, 11, 0, 0, 0, 1, 6, 0, 0, 14, 0, 0, 0, 0, 12, 0,
                0, 0, 0, 12, 0, 1, 0, 0, 0, 0, 4, 0, 9, 0, 0, 0,
                9, 0, 0, 1, 0, 11, 0, 0, 0, 3, 0, 16, 0, 0, 0, 0,
                0, 0, 13, 0, 2, 16, 11, 0, 0, 8, 9, 0, 15, 1, 0, 1,
                0, 5, 0, 7, 0, 0, 0, 1, 0, 0, 12, 0, 4, 13, 0, 0,
                0, 1, 9, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0,
                0, 1, 0, 0, 0, 8, 5, 15, 0, 0, 13, 11, 0, 0, 16, 0,
                0, 0, 0, 0, 15, 0, 0, 4, 0, 1, 0, 0, 0, 3, 13, 8,
                11, 0, 0, 6, 1, 0, 16, 0, 0, 4, 0, 0, 7, 15, 0, 0,
                0, 15, 0, 0, 12, 14, 0, 0, 9, 0, 0, 0, 0, 11, 0, 0,
                0, 14, 0, 0, 0, 9, 0, 13, 0, 0, 0, 0, 2, 16, 0, 5,
                1, 13, 16, 8, 0, 0, 0, 0, 0, 0, 0, 4, 0, 7, 0, 0,
                0, 0, 1, 0, 3, 15, 4, 0, 0, 0, 0, 0, 16, 9, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 3, 4, 0, 0,
                0, 9, 3, 0, 0, 0, 0, 16, 0, 13, 0, 14, 0, 0, 0, 15
            )
        )

        val output = parser.toText(sudoku)

        output.shouldBe(
            """
            ╔══╤══╤══╤══╦══╤══╤══╤══╦══╤══╤══╤══╦══╤══╤══╤══╗
            ║  │  │ 7│  ║16│  │14│  ║  │  │15│ 8║  │  │  │  ║
            ╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢
            ║ 4│11│  │  ║  │ 1│ 6│  ║  │14│  │  ║  │  │12│  ║
            ╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢
            ║  │  │  │12║  │ 1│  │  ║  │  │ 4│  ║ 9│  │  │  ║
            ╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢
            ║ 9│  │  │ 1║  │11│  │  ║  │ 3│  │16║  │  │  │  ║
            ╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣
            ║  │  │13│  ║ 2│16│11│  ║  │ 8│ 9│  ║15│ 1│  │ 1║
            ╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢
            ║  │ 5│  │ 7║  │  │  │ 1║  │  │12│  ║ 4│13│  │  ║
            ╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢
            ║  │ 1│ 9│  ║14│  │  │  ║  │  │  │  ║  │  │ 8│  ║
            ╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢
            ║  │ 1│  │  ║  │ 8│ 5│15║  │  │13│11║  │  │16│  ║
            ╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣
            ║  │  │  │  ║15│  │  │ 4║  │ 1│  │  ║  │ 3│13│ 8║
            ╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢
            ║11│  │  │ 6║ 1│  │16│  ║  │ 4│  │  ║ 7│15│  │  ║
            ╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢
            ║  │15│  │  ║12│14│  │  ║ 9│  │  │  ║  │11│  │  ║
            ╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢
            ║  │14│  │  ║  │ 9│  │13║  │  │  │  ║ 2│16│  │ 5║
            ╠══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╬══╪══╪══╪══╣
            ║ 1│13│16│ 8║  │  │  │  ║  │  │  │ 4║  │ 7│  │  ║
            ╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢
            ║  │  │ 1│  ║ 3│15│ 4│  ║  │  │  │  ║16│ 9│  │  ║
            ╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢
            ║  │  │  │  ║  │  │  │  ║ 2│  │  │  ║ 3│ 4│  │  ║
            ╟──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╫──┼──┼──┼──╢
            ║  │ 9│ 3│  ║  │  │  │16║  │13│  │14║  │  │  │15║
            ╚══╧══╧══╧══╩══╧══╧══╧══╩══╧══╧══╧══╩══╧══╧══╧══╝
            """.trimIndent()
        )
    }
}
