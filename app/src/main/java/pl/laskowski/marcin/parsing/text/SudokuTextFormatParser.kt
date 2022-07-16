package pl.laskowski.marcin.parsing.text

import pl.laskowski.marcin.model.Sudoku
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

    private inner class Command {
        private val scanner: Scanner
        private var width = 0
        private var height = 0
        private var data: Array<Int?> = arrayOfNulls(0)
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
                val sudoku = parseOne()
                sudokuList.add(sudoku)
            }
            return sudokuList
        }

        fun parseOne(): Sudoku {
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
            return Sudoku(width, height, data)
        }

        private fun readSize() {
            width = scanner.nextInt()
            height = scanner.nextInt()
            data = arrayOfNulls(width * height)
            pointer = 0
            scanner.nextLine()
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