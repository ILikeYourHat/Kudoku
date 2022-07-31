package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.dividers.RegionDivider

object ClassicSquare2x2 : SudokuType {

    override val sizeX = 2
    override val sizeY = 2
    override val regionSize = 2

    override fun template() = """
        _,_
        _,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
    }
}