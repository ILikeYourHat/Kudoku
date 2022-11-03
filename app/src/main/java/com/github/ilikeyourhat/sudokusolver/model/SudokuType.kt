package com.github.ilikeyourhat.sudokusolver.model

import com.github.ilikeyourhat.sudokusolver.model.dividers.RegionDivider

interface SudokuType {
    val sizeX: Int
    val sizeY: Int
    val regionSize: Int
    fun divider(): RegionDivider
    fun template(): String
}