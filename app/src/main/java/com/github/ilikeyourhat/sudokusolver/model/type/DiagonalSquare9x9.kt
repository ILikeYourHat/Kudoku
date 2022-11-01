package com.github.ilikeyourhat.sudokusolver.model.type

import com.github.ilikeyourhat.sudokusolver.model.dividers.RegionDivider

object DiagonalSquare9x9 : SudokuType {

    override val sizeX = 9
    override val sizeY = 9
    override val regionSize = 9

    override fun template() = ClassicSquare9x9.template()

    override fun divider(): RegionDivider {
        return ClassicSquare9x9.divider()
            .primaryDiagonal()
            .antiDiagonal()
    }
}