package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.dividers.RegionDivider
import pl.laskowski.marcin.type.ISudokuVariant

object ClassicSquare16x16 : ISudokuVariant {

    override val sizeX = 16
    override val sizeY = 16
    override val regionSize = 16

    override fun template() = """
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
        _,_,_,_ _,_,_,_ _,_,_,_ _,_,_,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
            .divideByBlocks(4)
    }
}