package com.github.ilikeyourhat.sudokusolver.solving.sat

import com.github.ilikeyourhat.sudokusolver.model.Point

class IndexEncoder(
    private val sizeX: Int,
    private val sizeY: Int
) {
    private val blockSize = sizeX * sizeY

    fun encode(p: Point, value: Int): Int {
        return p.x + sizeX * p.y + blockSize * (value - 1) + 1
    }

    fun decodePoint(index: Int): Point {
        val x = (index - 1) % sizeX
        val y = (index - 1) / sizeX % sizeY
        return Point(x, y)
    }

    fun decodeValue(index: Int): Int {
        return (index - 1) / blockSize + 1
    }
}
