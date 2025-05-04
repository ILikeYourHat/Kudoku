package io.github.ilikeyourhat.kudoku.parsing

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.type.Classic12x12
import io.github.ilikeyourhat.kudoku.type.Classic16x16
import io.github.ilikeyourhat.kudoku.type.Classic25x25
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.github.ilikeyourhat.kudoku.type.Classic6x6
import io.github.ilikeyourhat.kudoku.type.Classic9x9

@Suppress("MagicNumber")
class SingleLineSudokuParser {

    private val typeMap = mapOf(
        16 to Classic4x4,
        36 to Classic6x6,
        81 to Classic9x9,
        144 to Classic12x12,
        256 to Classic16x16,
        625 to Classic25x25
    )

    fun toText(
        sudoku: Sudoku,
        emptyCellIndicator: EmptyCellIndicator
    ): String {
        return sudoku.cells()
            .map { encodeValue(it.value, emptyCellIndicator) }
            .joinToString("")
    }

    fun fromText(text: String): Sudoku {
        val type = guessTypeBasedOnInputLength(text.length)
        return fromText(type, text)
    }

    private fun guessTypeBasedOnInputLength(length: Int): SudokuType {
        return typeMap[length]
            ?: throw IllegalArgumentException("Unsupported Sudoku type with input length $length")
    }

    fun fromText(
        type: SudokuType,
        text: String
    ): Sudoku {
        val values = text
            .map { decodeValue(it) }
            .toList()
        return Sudoku(type, values)
    }

    private fun encodeValue(value: Int, emptyCellIndicator: EmptyCellIndicator): Char {
        return when (value) {
            0 -> emptyCellIndicator.value
            in 1..9 -> value.digitToChar()
            in 10..25 -> 'A'.plus(value - 10)
            else -> throw IllegalArgumentException("Value $value is not supported")
        }
    }

    private fun decodeValue(value: Char): Int {
        return when (value) {
            in '1'..'9' -> value.digitToInt()
            in 'A'..LAST_SUPPORTED_LETTER_UPPERCASE -> value.code - 'A'.code + 10
            in 'a'..LAST_SUPPORTED_LETTER_LOWERCASE -> value.code - 'a'.code + 10
            in EMPTY_CELL_INDICATORS -> 0
            else -> throw IllegalArgumentException("Value $value is not supported")
        }
    }

    private companion object {
        const val LAST_SUPPORTED_LETTER_UPPERCASE = 'P'
        const val LAST_SUPPORTED_LETTER_LOWERCASE = 'p'
        val EMPTY_CELL_INDICATORS = EmptyCellIndicator.entries.map { it.value }
    }
}
