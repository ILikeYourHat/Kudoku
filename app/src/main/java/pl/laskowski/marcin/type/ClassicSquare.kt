package pl.laskowski.marcin.type

import kotlin.math.sqrt

class ClassicSquare(size: Int) : ClassicRectangle(size, size, blockSize(size), blockSize(size)) {

    companion object {
        private fun blockSize(size: Int): Int {
            val blockSize = sqrt(size.toDouble()).toInt()
            require(blockSize * blockSize == size)
            return blockSize
        }
    }
}