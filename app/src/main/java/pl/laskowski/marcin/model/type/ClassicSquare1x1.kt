package pl.laskowski.marcin.model.type

import pl.laskowski.marcin.model.dividers.RegionDivider
import pl.laskowski.marcin.type.ISudokuVariant

object ClassicSquare1x1 : ISudokuVariant {

    override val sizeX = 1
    override val sizeY = 1
    override val regionSize = 1

    override fun divider(): RegionDivider {
        return RegionDivider()
            .allFields()
    }

    override fun template() = """
        _
    """.trimIndent()
}