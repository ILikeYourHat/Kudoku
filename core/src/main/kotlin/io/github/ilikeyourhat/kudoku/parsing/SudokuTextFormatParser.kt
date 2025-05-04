package io.github.ilikeyourhat.kudoku.parsing

import io.github.ilikeyourhat.kudoku.model.JigsawSudokuType
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.type.TypesRegistry
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

        constructor(text: String) {
            scanner = Scanner(text)
        }

        constructor(file: File) {
            scanner = Scanner(file)
        }

        fun parseMany(): List<Sudoku> {
            val sudokuList = mutableListOf<Sudoku>()
            do {
                val sudoku = parse()
                sudokuList.add(sudoku)
            } while (scanner.hasNext())
            scanner.close()
            return sudokuList
        }

        fun parseOne(): Sudoku {
            val sudoku = parse()
            scanner.close()
            return sudoku
        }

        private fun parse(): Sudoku {
            val data = mutableListOf<Int>()
            val type = readType()
            val width = type.sizeX
            val height = if (type is JigsawSudokuType) {
                type.sizeY * 2
            } else {
                type.sizeY
            }
            val expectedValuesCount = getExpectedValuesCount(type)
            var y = 0
            while (y < height) {
                var line = scanner.nextLine()
                while (line.isEmpty()) {
                    line = scanner.nextLine()
                }
                val lineScanner = Scanner(line).useDelimiter(",")
                var x = 0
                while (x < width) {
                    val value = parse(lineScanner.next().trim())
                    if (value != null) {
                        data.add(value)
                    }
                    x++
                }
                y++
                if (data.size >= expectedValuesCount) break
            }
            return if (type is JigsawSudokuType) {
                val (values, regionIds) = data.splitInHalf()
                Sudoku(type, values, regionIds)
            } else {
                Sudoku(type, data)
            }
        }

        private fun getExpectedValuesCount(type: SudokuType): Int {
            return if (type is JigsawSudokuType) {
                type.createEmpty().cells().size * 2
            } else {
                type.createEmpty().cells().size
            }
        }

        fun <T> List<T>.splitInHalf(): Pair<List<T>, List<T>> {
            val midIndex = size / 2
            val firstHalf = subList(0, midIndex)
            val secondHalf = subList(midIndex, size)
            return firstHalf to secondHalf
        }

        private fun readType(): SudokuType {
            var type = ""
            while (type.isEmpty()) {
                type = scanner.nextLine()
            }

            return TypesRegistry.getTypeByName(type)
                ?: throw IllegalArgumentException("Unknown sudoku type: $type")
        }

        private fun parse(input: String): Int? {
            return when {
                isEmptyValue(input) -> 0
                isNoCell(input) -> null
                else -> asNumber(input)
            }
        }

        private fun isEmptyValue(input: String): Boolean {
            return EMPTY_VALUE.matcher(input).matches()
        }

        private fun isNoCell(input: String): Boolean {
            return NO_CELL.matcher(input).matches()
        }

        private fun asNumber(input: String): Int {
            return input.toInt()
        }
    }

    companion object {
        private val EMPTY_VALUE = Pattern.compile("^_*$")
        private val NO_CELL = Pattern.compile("^#*$")
    }
}
