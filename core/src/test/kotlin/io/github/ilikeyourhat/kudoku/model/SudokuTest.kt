package io.github.ilikeyourhat.kudoku.model

import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import io.github.ilikeyourhat.kudoku.type.DoubleSlash15x15
import io.github.ilikeyourhat.kudoku.type.Jigsaw4x4
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SudokuTest {

    @Test
    fun `should create empty sudoku with a given type`() {
        val sudoku = Sudoku(Classic9x9)

        sudoku.run {
            isEmpty().shouldBeTrue()
            type.shouldBe(Classic9x9)
        }
    }

    @Test
    fun `should create sudoku with given type and values`() {
        val sudoku = Sudoku(Classic4x4, listOf(0, 2, 3, 0, 1, 0, 0, 4, 3, 0, 0, 2, 0, 4, 1, 0))

        sudoku.run {
            isEmpty().shouldBeFalse()
            type.shouldBe(Classic4x4)
            values().shouldContainInOrder(0, 2, 3, 0, 1, 0, 0, 4, 3, 0, 0, 2, 0, 4, 1, 0)
        }
    }

    @Test
    fun `should fail if values size is smaller than type size`() {
        val exception = shouldThrow<IllegalArgumentException> {
            Sudoku(Classic4x4, listOf(1, 2, 3, 4))
        }
        exception.message.shouldBe("Incorrect values count, expected 16, but was 4")
    }

    @Test
    fun `should fail if values size is bigger than type size`() {
        val exception = shouldThrow<IllegalArgumentException> {
            Sudoku(Classic4x4, listOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4))
        }
        exception.message.shouldBe("Incorrect values count, expected 16, but was 20")
    }

    @Test
    fun `should fail if trying to assign value not supported by type`() {
        val exception = shouldThrow<IllegalArgumentException> {
            Sudoku(Classic4x4, listOf(7, 2, 3, 0, 1, 0, 0, 4, 3, 0, 0, 2, 0, 4, 1, 0))
        }
        exception.message.shouldBe("Value 7 is not supported by type classic_4x4")
    }

    @Test
    fun `should produce valid toString for classic type`() {
        val sudoku = Sudoku(Classic4x4, listOf(0, 2, 3, 0, 1, 0, 0, 4, 3, 0, 0, 2, 0, 4, 1, 0))

        sudoku.toString()
            .shouldBeEqual("classic_4x4 |_,2,3,_|1,_,_,4|3,_,_,2|_,4,1,_|")
    }

    @Test
    fun `should produce valid toString for jigsaw type`() {
        val sudoku = Sudoku(
            type = Jigsaw4x4,
            values = listOf(0, 0, 4, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0),
            regionIds = listOf(1, 1, 1, 1, 2, 2, 2, 4, 3, 2, 4, 4, 3, 3, 3, 4)
        )

        sudoku.toString()
            .shouldBeEqual("jigsaw_4x4 |_,_,4,_|_,3,_,_|_,2,_,_|_,_,_,_| |1,1,1,1|2,2,2,3|4,2,3,3|4,4,4,3|")
    }

    @Test
    fun `should produce valid toString for non-rectangle type`() {
        val sudoku = Sudoku(
            type = DoubleSlash15x15,
            values = listOf(
                0, 4, 5, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1, 6,
                0, 0, 6, 0, 0, 0, 9, 0, 0,
                8, 7, 0, 0, 0, 0, 0, 0, 0,
                0, 2, 0, 0, 1, 0, 0, 3, 7,
                0, 0, 0, 9, 0, 6, 0, 0, 4,
                0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1, 0, 0, 9,
                4, 0, 0, 0, 0, 7, 0, 0, 0, 0, 5, 0, 0, 7, 0,
                0, 0, 8, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0,
                7, 2, 5, 0, 6, 0, 8, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                3, 0, 0, 0, 4, 0, 0, 0, 6,
                6, 0, 2, 0, 0, 0, 0, 0, 0,
                8, 3, 0, 1, 0, 0, 0, 0, 7,
                0, 0, 0, 0, 0, 0, 0, 8, 0
            )
        )

        sudoku.toString()
            .shouldBeEqual(
                "double_slash_15x15 " +
                    "|#,#,#,#,#,#,_,4,5,_,_,_,_,_,_" +
                    "|#,#,#,#,#,#,_,_,_,_,_,_,_,1,6" +
                    "|#,#,#,#,#,#,_,_,6,_,_,_,9,_,_" +
                    "|#,#,#,#,#,#,8,7,_,_,_,_,_,_,_" +
                    "|#,#,#,#,#,#,_,2,_,_,1,_,_,3,7" +
                    "|#,#,#,#,#,#,_,_,_,9,_,6,_,_,4" +
                    "|_,_,_,_,_,2,_,_,_,_,_,1,_,_,9" +
                    "|4,_,_,_,_,7,_,_,_,_,5,_,_,7,_" +
                    "|_,_,8,_,_,3,_,_,_,_,3,_,_,_,_" +
                    "|7,2,5,_,6,_,8,_,_,#,#,#,#,#,#" +
                    "|_,_,_,_,_,_,_,_,_,#,#,#,#,#,#" +
                    "|3,_,_,_,4,_,_,_,6,#,#,#,#,#,#" +
                    "|6,_,2,_,_,_,_,_,_,#,#,#,#,#,#" +
                    "|8,3,_,1,_,_,_,_,7,#,#,#,#,#,#" +
                    "|_,_,_,_,_,_,_,8,_,#,#,#,#,#,#|"
            )
    }
}
