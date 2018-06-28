package pl.laskowski.marcin.model.matrix;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin Laskowski.
 */

public class ArrayMatrixTest {

    @Test
    public void createEmptyMatrixAndCheckItsSize() {
        Matrix matrix = new ArrayMatrix(0, 0);

        assertEquals(0, matrix.sizeX());
        assertEquals(0, matrix.sizeY());
    }

    @Test
    public void createNonEmptyMatrixAndCheckItsSize() {
        Matrix<Object> matrix = new ArrayMatrix<>(4, 7);

        assertEquals(4, matrix.sizeX());
        assertEquals(7, matrix.sizeY());
    }

    @Test
    public void checkIfMatrixIsInitializedWithNulls() {
        Matrix<Object> matrix = new ArrayMatrix<>(2, 2);

        assertNull(matrix.get(0, 0));
        assertNull(matrix.get(1, 0));
        assertNull(matrix.get(0, 1));
        assertNull(matrix.get(1, 1));
    }

    @Test
    public void insertObjectIntoMatrixAndCheckItsThere() {
        Matrix<Object> matrix = new ArrayMatrix<>(3, 3);
        Object object = new Object();

        matrix.put(object, 1, 2);

        assertSame(object, matrix.get(1, 2));
    }

    @Test
    public void checkIfMatrixHandlesEmptyStateProperly() {
        Matrix<Object> matrix = new ArrayMatrix<>(3, 3);
        assertTrue(matrix.isEmpty());

        matrix.put(new Object(), 1, 1);
        assertFalse(matrix.isEmpty());

        matrix.put(null, 1, 1);
        assertTrue(matrix.isEmpty());
    }

    @Test
    public void checkIfClearingMatrixWorks() {
        Matrix<Object> matrix = new ArrayMatrix<>(3, 3);

        matrix.put(new Object(), 1, 1);
        matrix.clear();

        assertTrue(matrix.isEmpty());
    }

    @Test
    public void checkIfMatrixContainsGivenElement() {
        Matrix<Integer> matrix = new ArrayMatrix<>(2, 2);
        int testValue = 7;

        assertFalse(matrix.contains(testValue));

        matrix.put(testValue, 1, 1);
        assertTrue(matrix.contains(testValue));
    }

    @Test
    public void removeGivenElementFromMatrix() {
        Matrix<Integer> matrix = new ArrayMatrix<>(2, 2);
        int testValue = 7;

        matrix.put(testValue, 1, 1);
        matrix.remove(testValue);

        assertFalse(matrix.contains(testValue));
    }

    @Test
    public void checkIfGivenElementWasRemoved() {
        Matrix<Integer> matrix = new ArrayMatrix<>(2, 2);
        int testValue = 7;

        matrix.put(testValue, 1, 1);

        assertTrue(matrix.remove(testValue));
        assertFalse(matrix.remove(testValue));
    }

    @Test
    public void checkIfMatrixIsEqualsWhenSizeIsEquals() {
        Matrix<Object> smallMatrix = new ArrayMatrix<>(2, 2);
        Matrix<Object> otherSmallMatrix = new ArrayMatrix<>(2, 2);
        Matrix<Object> veryBigMatrix = new ArrayMatrix<>(1000, 1000);

        assertTrue(smallMatrix.equals(otherSmallMatrix));
        assertFalse(smallMatrix.equals(veryBigMatrix));

        assertTrue(smallMatrix.hashCode() == otherSmallMatrix.hashCode());
        assertFalse(smallMatrix.hashCode() == veryBigMatrix.hashCode());
    }

    @Test
    public void checkIfMatrixIsEqualsWhenDataIsEquals() {
        Matrix<String> twinMatrix1 = new ArrayMatrix<>(2, 2);
        twinMatrix1.put("twin", 0, 0);
        Matrix<String> twinMatrix2 = new ArrayMatrix<>(2, 2);
        twinMatrix2.put("twin", 0, 0);
        Matrix<String> differentMatrix1 = new ArrayMatrix<>(2, 2);
        differentMatrix1.put("not twin", 0, 0);
        Matrix<String> differentMatrix2 = new ArrayMatrix<>(2, 2);
        differentMatrix2.put("twin", 1, 1);

        assertTrue(twinMatrix1.equals(twinMatrix2));
        assertFalse(twinMatrix1.equals(differentMatrix1));
        assertFalse(twinMatrix1.equals(differentMatrix2));

        assertTrue(twinMatrix1.hashCode() == twinMatrix2.hashCode());
        assertFalse(twinMatrix1.hashCode() == differentMatrix1.hashCode());
        assertFalse(twinMatrix1.hashCode() == differentMatrix2.hashCode());
    }

}
