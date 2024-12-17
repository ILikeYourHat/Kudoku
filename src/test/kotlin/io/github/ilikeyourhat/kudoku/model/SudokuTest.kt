package io.github.ilikeyourhat.kudoku.model

import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainInOrder
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
}
