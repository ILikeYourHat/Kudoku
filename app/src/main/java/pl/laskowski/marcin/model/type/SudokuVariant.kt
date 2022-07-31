package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.dividers.RegionDivider

interface SudokuType {
    val sizeX: Int
    val sizeY: Int
    val regionSize: Int
    fun divider(): RegionDivider
    fun template(): String
}