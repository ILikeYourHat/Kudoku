package io.github.ilikeyourhat.kudoku.model

import io.github.ilikeyourhat.kudoku.model.dividers.RegionDivider

interface SudokuType {
    val name: String
    val sizeX: Int
    val sizeY: Int
    val maxValue: Int
    fun template(): String
    fun divider(): RegionDivider
    fun allPossibleValues(): List<Int> = IntRange(1, maxValue).toList()
}
