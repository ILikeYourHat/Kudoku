package com.github.ilikeyourhat.sudokusolver.model.matrix

interface MutableMatrix<E> : Matrix<E> {
    operator fun set(x: Int, y: Int, elem: E)
}