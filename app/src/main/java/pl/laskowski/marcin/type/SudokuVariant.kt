package pl.laskowski.marcin.type

import pl.laskowski.marcin.model.dividers.RegionDivider

interface ISudokuVariant {
    val sizeX: Int
    val sizeY: Int
    val regionSize: Int
    fun divider(): RegionDivider
    fun template(): String
}