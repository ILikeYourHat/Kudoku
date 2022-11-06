package com.github.ilikeyourhat.kudoku.parsing.text

import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.type.*
import com.github.ilikeyourhat.kudoku.model.SudokuType
import java.io.File
import java.io.FileNotFoundException
import java.util.*
import java.util.regex.Pattern

class SudokuTextFormatParser {

    fun parseOne(text: String): Sudoku {
        return Command(text).parseOne()
    }

    fun parseMany(text: String): List<Sudoku> {
        return Command(text).parseMany()
    }

    @Throws(FileNotFoundException::class)
    fun parseOne(file: File): Sudoku {
        return Command(file).parseOne()
    }

    @Throws(FileNotFoundException::class)
    fun parseMany(file: File): List<Sudoku> {
        return Command(file).parseMany()
    }

    private fun getType(type: String): SudokuType {
        return when (type) {
            "butterfly_12x12" -> Butterfly12x12
            "classic_1x1" -> Square1x1
            "classic_2x2" -> Square2x2
            "classic_4x4" -> Classic4x4
            "classic_9x9" -> Classic9x9
            "classic_16x16" -> Classic16x16
            "classic_25x25" -> Classic25x25
            "double_backslash_15x15" -> DoubleBackslash15x15
            "double_diagonal_9x9" -> DoubleDiagonal9x9
            "double_slash_15x15" -> DoubleSlash15x15
            "samurai_butterfly_30x30" -> SamuraiButterfly30x30
            "samurai_classic_40x40" -> SamuraiClassic40x40
            else -> throw IllegalArgumentException("Unknown sudoku type: $type")
        }
    }

    private inner class Command {
        private val scanner: Scanner
        private lateinit var type: SudokuType
        private var width = 0
        private var height = 0
        private lateinit var data: MutableList<Int?>
        private var pointer = 0

        constructor(text: String) {
            scanner = Scanner(text)
        }

        constructor(file: File) {
            scanner = Scanner(file)
        }

        fun parseMany(): List<Sudoku> {
            val sudokuList: MutableList<Sudoku> = ArrayList()
            while (scanner.hasNext()) {
                val sudoku = parse()
                sudokuList.add(sudoku)
                if (scanner.hasNext()) scanner.nextLine()
            }
            scanner.close()
            return sudokuList
        }

        fun parseOne(): Sudoku {
            val sudoku = parse()
            scanner.close()
            return sudoku
        }

        private fun parse(): Sudoku {
            readSize()
            var y = 0
            while (y < height) {
                val line = scanner.nextLine()
                if (line.isEmpty()) continue
                val lineScanner = Scanner(line).useDelimiter(",")
                var x = 0
                while (x < width) {
                    val value = parse(lineScanner.next().trim())
                    data[pointer] = value
                    x++
                    pointer++
                }
                y++
            }
            return Sudoku(type, data)
        }

        private fun readSize() {
            type = getType(scanner.nextLine())
            width = type.sizeX
            height = type.sizeY
            data = MutableList(width * height) { null }
            pointer = 0
        }

        private fun parse(input: String): Int? {
            return when {
                isEmptyValue(input) -> 0
                isNoField(input) -> null
                else -> asNumber(input)
            }
        }

        private fun isEmptyValue(input: String): Boolean {
            return EMPTY_VALUE.matcher(input).matches()
        }

        private fun isNoField(input: String): Boolean {
            return NO_FIELD.matcher(input).matches()
        }

        private fun asNumber(input: String): Int {
            return input.toInt(10)
        }
    }

    companion object {
        private val EMPTY_VALUE = Pattern.compile("^_*$")
        private val NO_FIELD = Pattern.compile("^#*$")
    }
}