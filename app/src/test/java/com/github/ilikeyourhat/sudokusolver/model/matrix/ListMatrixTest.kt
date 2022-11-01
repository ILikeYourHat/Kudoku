package com.github.ilikeyourhat.sudokusolver.model.matrix

import org.junit.Assert.*
import org.junit.Test

class ListMatrixTest {

    @Test
    fun createEmptyMatrixAndCheckItsSize() {
        val matrix = ListMatrix(0, 0, null)
        assertEquals(0, matrix.sizeX)
        assertEquals(0, matrix.sizeY)
        assertTrue(matrix.isEmpty())
    }

    @Test
    fun createNonEmptyMatrixAndCheckItsSize() {
        val matrix = ListMatrix(4, 7, null)
        assertEquals(4, matrix.sizeX)
        assertEquals(7, matrix.sizeY)
        assertFalse(matrix.isEmpty())
    }

    @Test
    fun checkIfMatrixIsInitializedWithNulls() {
        val matrix = ListMatrix(2, 2, null)
        assertNull(matrix[0, 0])
        assertNull(matrix[1, 0])
        assertNull(matrix[0, 1])
        assertNull(matrix[1, 1])
    }

    @Test
    fun checkIfMatrixIsInitializedWithDefaultValue() {
        val matrix = ListMatrix(2, 2, "test")
        assertEquals("test", matrix[0, 0])
        assertEquals("test", matrix[1, 0])
        assertEquals("test", matrix[0, 1])
        assertEquals("test", matrix[1, 1])
    }

    @Test
    fun insertObjectIntoMatrixAndCheckItsThere() {
        val matrix = ListMatrix<Any?>(3, 3, null)
        val any = Any()

        matrix[1, 2] = any
        assertSame(any, matrix[1, 2])
    }

    @Test
    fun checkIfMatrixContainsGivenElement() {
        val matrix = ListMatrix(2, 2, 0)
        val testValue = 7

        assertFalse(matrix.contains(testValue))

        matrix[1, 1] = testValue

        assertTrue(matrix.contains(testValue))
    }

    @Test
    fun checkIfMatrixIsEqualsWhenSizeIsEquals() {
        val smallMatrix = ListMatrix(2, 2, null)
        val otherSmallMatrix = ListMatrix(2, 2, null)
        val veryBigMatrix = ListMatrix(1000, 1000, null)

        assertEquals(smallMatrix, otherSmallMatrix)
        assertNotEquals(smallMatrix, veryBigMatrix)

        assertEquals(smallMatrix.hashCode(), otherSmallMatrix.hashCode())
        assertNotEquals(smallMatrix.hashCode(), veryBigMatrix.hashCode())
    }

    @Test
    fun checkIfMatrixIsEqualsWhenDataIsEquals() {
        val twinMatrix1 = ListMatrix(2, 2, "")
        twinMatrix1[0, 0] = "twin"

        val twinMatrix2 = ListMatrix(2, 2, "")
        twinMatrix2[0, 0] = "twin"

        val differentMatrix1 = ListMatrix(2, 2, "")
        differentMatrix1[0, 0] = "not twin"

        val differentMatrix2 = ListMatrix(2, 2, "")
        differentMatrix2[1, 1] = "twin"

        assertEquals(twinMatrix1, twinMatrix2)
        assertNotEquals(twinMatrix1, differentMatrix1)
        assertNotEquals(twinMatrix1, differentMatrix2)

        assertEquals(twinMatrix1.hashCode(), twinMatrix2.hashCode())
        assertNotEquals(twinMatrix1.hashCode(), differentMatrix1.hashCode())
        assertNotEquals(twinMatrix1.hashCode(), differentMatrix2.hashCode())
    }

    @Test
    fun checkIfCoordinatesByIndexWorks() {
        val matrix = ListMatrix(3, 2, "")

        val coordinates = (0 until matrix.size).toList()
            .map { index -> matrix.coordinatesOf(index) }

        assertEquals(
            listOf(
                0 to 0, 1 to 0, 2 to 0,
                0 to 1, 1 to 1, 2 to 1
            ), coordinates
        )
    }
}