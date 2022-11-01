package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.dividers.RegionDivider

object ClassicSquare4x4 : SudokuType {

    override val sizeX = 4
    override val sizeY = 4
    override val regionSize = 4

    override fun template() = """
        _,_ _,_
        _,_ _,_

        _,_ _,_
        _,_ _,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
            .divideByBlocks(2)
    }
}