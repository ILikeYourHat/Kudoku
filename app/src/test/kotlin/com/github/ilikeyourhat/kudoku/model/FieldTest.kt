package com.github.ilikeyourhat.kudoku.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FieldTest {

    @Test
    fun createEmptyField() {
        val field = Field(0, 0)
        assertTrue(field.isEmpty)
        assertEquals(0, field.value)
    }

    @Test
    fun setNewValueToField() {
        val field = Field(0, 0)
        assertTrue(field.isEmpty)

        field.set(7)
        assertEquals(7, field.value)
    }

    @Test
    fun createFieldWithCoords() {
        val x = 3
        val y = 7
        val field = Field(x, y)
        assertEquals(x, field.x)
        assertEquals(y, field.y)
    }

    @Test
    fun shouldBeEqualsIfCoordsAreEquals() {
        val fieldBeingTested = Field(1, 2)
        val sameField = Field(1, 2)
        val differentField = Field(3, 5)

        assertEquals(sameField, fieldBeingTested)
        assertNotEquals(differentField, fieldBeingTested)
    }
}