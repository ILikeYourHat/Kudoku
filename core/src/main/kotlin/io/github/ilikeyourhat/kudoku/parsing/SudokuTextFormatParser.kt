package io.github.ilikeyourhat.kudoku.parsing

import io.github.ilikeyourhat.kudoku.model.JigsawSudokuType
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.type.TypesRegistry

class SudokuTextFormatParser {

    fun parseOne(text: String): Sudoku {
        return Command(text.lineSequence()).parseOne()
    }

    fun parseMany(text: String): List<Sudoku> {
        return Command(text.lineSequence()).parseMany()
    }

    private inner class Command {
        private val lines: Iterator<String>

        constructor(text: Sequence<String>) {
            lines = text.iterator()
        }

        fun parseMany(): List<Sudoku> {
            val sudokuList = mutableListOf<Sudoku>()
            do {
                val sudoku = parse()
                sudokuList.add(sudoku)
            } while (lines.hasNext())
            return sudokuList
        }

        fun parseOne(): Sudoku {
            return parse()
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
                var line = lines.next()
                while (line.isEmpty()) {
                    line = lines.next()
                }
                val lineIterator = line.split(",").iterator()
                var x = 0
                while (x < width) {
                    val value = parse(lineIterator.next().trim())
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
                type = lines.next()
            }

            return TypesRegistry.getTypeByName(type)
                ?: throw IllegalArgumentException("Unsupported Sudoku type: $type")
        }

        private fun parse(input: String): Int? {
            return when {
                isEmptyValue(input) -> 0
                isNoCell(input) -> null
                else -> asNumber(input)
            }
        }

        private fun isEmptyValue(input: String): Boolean {
            return EMPTY_VALUE.matches(input)
        }

        private fun isNoCell(input: String): Boolean {
            return NO_CELL.matches(input)
        }

        private fun asNumber(input: String): Int {
            return input.toInt()
        }
    }

    companion object {
        private val EMPTY_VALUE = "^_*$".toRegex()
        private val NO_CELL = "^#*$".toRegex()
    }
}
