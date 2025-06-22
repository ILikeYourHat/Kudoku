package io.github.ilikeyourhat.kudoku.integration.parsing

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.parsing.toGraphicFormatString
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GraphicFormatParserTest {

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

        sudoku.toGraphicFormatString().shouldBe(
            """
            ╔═╤═╦═╤═╗
            ║ │ ║1│ ║
            ╟─┼─╫─┼─╢
            ║3│ ║ │ ║
            ╠═╪═╬═╪═╣
            ║ │4║ │ ║
            ╟─┼─╫─┼─╢
            ║1│ ║4│ ║
            ╚═╧═╩═╧═╝
            """.trimIndent()
        )
    }
}
