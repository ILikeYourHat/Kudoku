package io.github.ilikeyourhat.kudoku.integration.parsing

import io.github.ilikeyourhat.kudoku.Kudoku
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CustomSudokuTypeTest {

    @Test
    fun `should parse custom type from string`() {
        Kudoku.registerType(MyCustomType)

        val actual = Kudoku.createFromString(
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

    private object MyCustomType : SudokuType {
        override val name = "my_custom_type"
        override val sizeX = 4
        override val sizeY = 4
        override val maxValue = 4

        override fun template(): String {
            return Classic4x4.template()
        }

        override fun divider(): RegionDivider {
            return Classic4x4.divider()
        }
    }
}
