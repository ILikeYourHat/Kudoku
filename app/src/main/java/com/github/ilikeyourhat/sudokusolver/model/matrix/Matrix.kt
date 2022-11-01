package com.github.ilikeyourhat.sudokusolver.model.matrix

interface Matrix<E> : Collection<E> {
    val sizeX: Int
    val sizeY: Int
    operator fun get(x: Int, y: Int): E
}