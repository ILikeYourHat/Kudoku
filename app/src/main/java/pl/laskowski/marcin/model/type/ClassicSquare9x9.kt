package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.dividers.RegionDivider
import pl.laskowski.marcin.type.ISudokuVariant

object ClassicSquare9x9 : ISudokuVariant {

    override val sizeX = 9
    override val sizeY = 9
    override val regionSize = 9

    override fun template() = """
        _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_
        
        _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_
                
        _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_
        _,_,_ _,_,_ _,_,_
    """.trimIndent()

    override fun divider(): RegionDivider {
        return RegionDivider()
            .divideByRows()
            .divideByColumns()
            .divideByBlocks(3)
    }
}