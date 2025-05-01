package io.github.ilikeyourhat.kudoku.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun createEmptyCell() {
        val cell = Cell(0, 0)
        assertTrue(cell.isEmpty())
        assertEquals(0, cell.value)
    }

    @Test
    fun setNewValueToCell() {
        val cell = Cell(0, 0)
        assertTrue(cell.isEmpty())

        cell.set(7)
        assertEquals(7, cell.value)
    }

    @Test
    fun createCellWithCoords() {
        val x = 3
        val y = 7
        val cell = Cell(x, y)
        assertEquals(x, cell.x)
        assertEquals(y, cell.y)
    }

    @Test
    fun shouldBeEqualsIfCoordsAreEquals() {
        val cellBeingTested = Cell(1, 2)
        val sameCell = Cell(1, 2)
        val differentCell = Cell(3, 5)

        assertEquals(sameCell, cellBeingTested)
        assertNotEquals(differentCell, cellBeingTested)
    }
}
