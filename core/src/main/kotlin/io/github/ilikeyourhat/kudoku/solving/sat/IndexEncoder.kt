package io.github.ilikeyourhat.kudoku.solving.sat

class IndexEncoder(
    private val sizeX: Int,
    private val sizeY: Int,
    private val maxValue: Int
) {

    init {
        require(sizeX > 0) { "sizeX must be greater than 0, got $sizeX" }
        require(sizeY > 0) { "sizeY must be greater than 0, got $sizeY" }
        require(maxValue > 0) { "maxValue must be greater than 0, got $maxValue" }
        require(
            // two-step check not to overflow the Long
            1L * sizeX * sizeY <= Int.MAX_VALUE && 1L * sizeX * sizeY * maxValue <= Int.MAX_VALUE
        ) { "Possible Int overflow for given values: sizeX=$sizeX, sizeY=$sizeY, maxValue=$maxValue" }
    }

    private val blockSize = sizeX * sizeY

    fun encode(position: Pair<Int, Int>, value: Int): Int {
        return position.first + sizeX * position.second + blockSize * (value - 1) + 1
    }

    fun decodePosition(index: Int): Pair<Int, Int> {
        val x = (index - 1) % sizeX
        val y = (index - 1) / sizeX % sizeY
        return x to y
    }

    fun decodeValue(index: Int): Int {
        return (index - 1) / blockSize + 1
    }
}
