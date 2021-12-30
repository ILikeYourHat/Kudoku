package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.Sudoku.Companion.blank
import pl.laskowski.marcin.model.Sudoku
import kotlin.math.sqrt

/**
 * Created by Marcin Laskowski.
 */
class ClassicSquare(size: Int) : ClassicRectangle(size, size, blockSize(size), blockSize(size)) {
    override fun template(): Sudoku {
        return blank(width(), height())
    }

    companion object {
        private fun blockSize(size: Int): Int {
            val blockSize = sqrt(size.toDouble()).toInt()
            require(blockSize * blockSize == size)
            return blockSize
        }
    }
}