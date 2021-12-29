package pl.laskowski.marcin.model.matrix

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Marcin Laskowski.
 */
class ArrayMatrixTest {

    @Test
    fun createEmptyMatrixAndCheckItsSize() {
        val matrix = ArrayMatrix<Any>(0, 0)
        assertEquals(0, matrix.sizeX())
        assertEquals(0, matrix.sizeY())
    }

    @Test
    fun createNonEmptyMatrixAndCheckItsSize() {
        val matrix = ArrayMatrix<Any>(4, 7)
        assertEquals(4, matrix.sizeX())
        assertEquals(7, matrix.sizeY())
    }

    @Test
    fun checkIfMatrixIsInitializedWithNulls() {
        val matrix = ArrayMatrix<Any>(2, 2)
        assertNull(matrix[0, 0])
        assertNull(matrix[1, 0])
        assertNull(matrix[0, 1])
        assertNull(matrix[1, 1])
    }

    @Test
    fun insertObjectIntoMatrixAndCheckItsThere() {
        val matrix = ArrayMatrix<Any>(3, 3)
        val any = Any()

        matrix.put(any, 1, 2)
        assertSame(any, matrix[1, 2])
    }

    @Test
    fun checkIfMatrixHandlesEmptyStateProperly() {
        val matrix = ArrayMatrix<Any>(3, 3)
        assertTrue(matrix.isEmpty)

        matrix.put(Any(), 1, 1)
        assertFalse(matrix.isEmpty)

        matrix.put(null, 1, 1)
        assertTrue(matrix.isEmpty)
    }

    @Test
    fun checkIfClearingMatrixWorks() {
        val matrix = ArrayMatrix<Any>(3, 3)

        matrix.put(Any(), 1, 1)
        matrix.clear()

        assertTrue(matrix.isEmpty)
    }

    @Test
    fun checkIfMatrixContainsGivenElement() {
        val matrix = ArrayMatrix<Int>(2, 2)
        val testValue = 7

        assertFalse(matrix.contains(testValue))

        matrix.put(testValue, 1, 1)

        assertTrue(matrix.contains(testValue))
    }

    @Test
    fun removeGivenElementFromMatrix() {
        val matrix = ArrayMatrix<Int>(2, 2)
        val testValue = 7

        matrix.put(testValue, 1, 1)
        matrix.remove(testValue)

        assertFalse(matrix.contains(testValue))
    }

    @Test
    fun checkIfGivenElementWasRemoved() {
        val matrix = ArrayMatrix<Int>(2, 2)
        val testValue = 7

        matrix.put(testValue, 1, 1)

        assertTrue(matrix.remove(testValue))
        assertFalse(matrix.remove(testValue))
    }

    @Test
    fun checkIfMatrixIsEqualsWhenSizeIsEquals() {
        val smallMatrix = ArrayMatrix<Any>(2, 2)
        val otherSmallMatrix = ArrayMatrix<Any>(2, 2)
        val veryBigMatrix = ArrayMatrix<Any>(1000, 1000)

        assertEquals(smallMatrix, otherSmallMatrix)
        assertNotEquals(smallMatrix, veryBigMatrix)

        assertEquals(smallMatrix.hashCode(), otherSmallMatrix.hashCode())
        assertNotEquals(smallMatrix.hashCode(), veryBigMatrix.hashCode())
    }

    @Test
    fun checkIfMatrixIsEqualsWhenDataIsEquals() {
        val twinMatrix1 = ArrayMatrix<String>(2, 2)
        twinMatrix1.put("twin", 0, 0)

        val twinMatrix2 = ArrayMatrix<String>(2, 2)
        twinMatrix2.put("twin", 0, 0)

        val differentMatrix1 = ArrayMatrix<String>(2, 2)
        differentMatrix1.put("not twin", 0, 0)

        val differentMatrix2 = ArrayMatrix<String>(2, 2)
        differentMatrix2.put("twin", 1, 1)

        assertEquals(twinMatrix1, twinMatrix2)
        assertNotEquals(twinMatrix1, differentMatrix1)
        assertNotEquals(twinMatrix1, differentMatrix2)

        assertEquals(twinMatrix1.hashCode(), twinMatrix2.hashCode())
        assertNotEquals(twinMatrix1.hashCode(), differentMatrix1.hashCode())
        assertNotEquals(twinMatrix1.hashCode(), differentMatrix2.hashCode())
    }
}