package io.github.ilikeyourhat.kudoku.parsing

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.type.Classic12x12
import io.github.ilikeyourhat.kudoku.type.Classic12x12Vertical
import io.github.ilikeyourhat.kudoku.type.Classic16x16
import io.github.ilikeyourhat.kudoku.type.Classic25x25
import io.github.ilikeyourhat.kudoku.type.Classic36x36
import io.github.ilikeyourhat.kudoku.type.Classic4x4
import io.github.ilikeyourhat.kudoku.type.Classic6x6
import io.github.ilikeyourhat.kudoku.type.Classic6x6Vertical
import io.github.ilikeyourhat.kudoku.type.Classic9x9
import io.github.ilikeyourhat.kudoku.type.DoubleDiagonal9x9
import io.github.ilikeyourhat.kudoku.type.Square1x1
import io.github.ilikeyourhat.kudoku.type.Square2x2

class GraphicFormatParser {

    fun toText(sudoku: Sudoku): String {
        val (blockSizeX, blockSizeY) = sudoku.getBlocksSize()
        return Command(
            values = sudoku.values().chunked(sudoku.sizeX()),
            valuesLength = sudoku.getMaxValueLength(),
            sizeX = sudoku.sizeX(),
            sizeY = sudoku.sizeY(),
            blockSizeX = blockSizeX,
            blockSizeY = blockSizeY
        ).execute()
    }

    @Suppress("MagicNumber") // TODO refactor to depend on region dividers
    private fun Sudoku.getBlocksSize(): Pair<Int, Int> {
        return when (type) {
            Square1x1 -> 1 to 1
            Square2x2 -> 2 to 2
            Classic4x4 -> 2 to 2
            Classic6x6 -> 3 to 2
            Classic6x6Vertical -> 2 to 3
            Classic9x9 -> 3 to 3
            Classic12x12 -> 4 to 3
            Classic12x12Vertical -> 3 to 4
            Classic16x16 -> 4 to 4
            Classic25x25 -> 5 to 5
            Classic36x36 -> 6 to 6
            DoubleDiagonal9x9 -> 3 to 3
            else -> throw IllegalArgumentException("Unsupported Sudoku type: $type")
        }
    }

    private fun Sudoku.getMaxValueLength(): Int {
        return type.maxValue.toString().length
    }

    private class Command(
        val values: List<List<Int>>,
        val valuesLength: Int,
        val sizeX: Int,
        val sizeY: Int,
        val blockSizeX: Int,
        val blockSizeY: Int
    ) {
        fun execute(): String {
            return StringBuilder().apply {
                top().appendLine()
                repeat(sizeY - 1) { index ->
                    content(values[index]).appendLine()
                    middle((index + 1) % blockSizeY == 0).appendLine()
                }
                content(values.last()).appendLine()
                bottom()
            }.toString()
        }

        private fun StringBuilder.content(values: List<Int>) = apply {
            verticalLine()
            repeat(sizeX - 1) { index ->
                graphicValue(values[index])
                verticalLine((index + 1) % blockSizeX == 0)
            }
            graphicValue(values.last())
            verticalLine()
        }

        private fun StringBuilder.graphicValue(value: Int) = append(
            if (value == 0) {
                " ".repeat(valuesLength)
            } else {
                value.toString().padStart(valuesLength)
            }
        )

        private fun StringBuilder.top() = apply {
            topLeftBorder()
            repeat(sizeX - 1) { index ->
                horizontalLine()
                topBorder((index + 1) % blockSizeX == 0)
            }
            horizontalLine()
            topRightBorder()
        }

        private fun StringBuilder.middle(isRegionIntersectionY: Boolean) = apply {
            leftBorder(isRegionIntersectionY)
            repeat(sizeX - 1) { index ->
                horizontalLine(isRegionIntersectionY)
                middleBorder((index + 1) % blockSizeX == 0, isRegionIntersectionY)
            }
            horizontalLine(isRegionIntersectionY)
            rightBorder(isRegionIntersectionY)
        }

        private fun StringBuilder.bottom() = apply {
            bottomLeftBorder()
            repeat(sizeX - 1) { index ->
                horizontalLine()
                bottomBorder((index + 1) % blockSizeX == 0)
            }
            horizontalLine()
            bottomRightBorder()
        }

        private fun StringBuilder.topLeftBorder() = append("╔")

        private fun StringBuilder.topRightBorder() = append("╗")

        private fun StringBuilder.bottomLeftBorder() = append("╚")

        private fun StringBuilder.bottomRightBorder() = append("╝")

        private fun StringBuilder.topBorder(isRegionIntersection: Boolean) = append(
            if (isRegionIntersection) "╦" else "╤"
        )

        private fun StringBuilder.bottomBorder(isRegionIntersection: Boolean) = append(
            if (isRegionIntersection) "╩" else "╧"
        )

        private fun StringBuilder.leftBorder(isRegionIntersection: Boolean) = append(
            if (isRegionIntersection) "╠" else "╟"
        )

        private fun StringBuilder.rightBorder(isRegionIntersection: Boolean) = append(
            if (isRegionIntersection) "╣" else "╢"
        )

        private fun StringBuilder.middleBorder(
            isRegionIntersectionX: Boolean,
            isRegionIntersectionY: Boolean
        ) = append(
            when {
                isRegionIntersectionX && isRegionIntersectionY -> "╬"
                isRegionIntersectionX -> "╫"
                isRegionIntersectionY -> "╪"
                else -> "┼"
            }
        )

        private fun StringBuilder.horizontalLine(
            isRegionIntersection: Boolean = true
        ) = apply {
            val lineChar = if (isRegionIntersection) "═" else "─"
            append(lineChar.repeat(valuesLength))
        }

        private fun StringBuilder.verticalLine(isRegionIntersection: Boolean = true) = append(
            if (isRegionIntersection) "║" else "│"
        )
    }
}
