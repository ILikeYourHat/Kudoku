package io.github.ilikeyourhat.kudoku.model

import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test

class RegionTest {

    @Test
    fun `should create region from cells`() {
        val cell1 = Cell(0, 0)
        val cell2 = Cell(0, 1)
        val cell3 = Cell(1, 0)
        val cell4 = Cell(1, 1)

        val region = Region(listOf(cell1, cell2, cell3, cell4))

        assert(region.size() == 4)
        assert(region.cells.contains(cell1))
        assert(region.cells.contains(cell2))
        assert(region.cells.contains(cell3))
        assert(region.cells.contains(cell4))
    }

    @Test
    fun `should check if region is valid`() {
        val cell1 = Cell(0, 0)
        val cell2 = Cell(0, 1)
        val cell3 = Cell(1, 0)
        val cell4 = Cell(1, 1)

        val region = Region(listOf(cell1, cell2, cell3, cell4))

        assert(region.isValid())
    }

    @Test
    fun `should check equals contract`() {
        val cell1 = Cell(0, 0)
        val cell2 = Cell(0, 1)
        val cell3 = Cell(1, 0)
        val cell4 = Cell(1, 1)

        val cell5 = Cell(0, 0)
        val cell6 = Cell(0, 1)
        val cell7 = Cell(1, 0)
        val cell8 = Cell(1, 1)

        val region1 = Region(listOf(cell1, cell2, cell3, cell4))
        val region2 = Region(listOf(cell5, cell6, cell7, cell8))

        region1.shouldBeEqual(region2)
    }
}
