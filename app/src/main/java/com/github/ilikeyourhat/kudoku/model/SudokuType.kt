package com.github.ilikeyourhat.kudoku.model

import com.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

interface SudokuType {
    val sizeX: Int
    val sizeY: Int
    val regionSize: Int
    fun divider(): RegionDivider
    fun template(): String
}