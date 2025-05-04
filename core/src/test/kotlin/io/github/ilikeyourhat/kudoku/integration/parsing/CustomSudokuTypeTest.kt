package io.github.ilikeyourhat.kudoku.integration.parsing

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.ColumnsDivider
import io.github.ilikeyourhat.kudoku.model.dividers.RowsDivider
import io.github.ilikeyourhat.kudoku.parsing.createFromString
import io.github.ilikeyourhat.kudoku.type.TypesRegistry
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CustomSudokuTypeTest {

    @Test
    fun `should parse custom type from string`() {
        TypesRegistry.register(MyCustomType)

        val actual = Sudoku.createFromString(
            """
            my_custom_type
            3,2, _,1,
            1,_, _,_,
            
            _,_, _,_,
            _,_, _,3,
            """.trimIndent()
        )

        val expected = Sudoku(
            MyCustomType,
            listOf(
                3, 2, 0, 1,
                1, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 3,
            )
        )

        assertEquals(expected, actual)
    }

    private object MyCustomType : SudokuType() {
        override val name = "my_custom_type"
        override val sizeX = 4
        override val sizeY = 4
        override val maxValue = 4
        override val dividers = listOf(
            RowsDivider(),
            ColumnsDivider()
        )
    }
}
