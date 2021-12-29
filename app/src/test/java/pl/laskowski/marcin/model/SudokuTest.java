package pl.laskowski.marcin.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin Laskowski.
 */

public class SudokuTest {

    @Test
    public void createGridWithNoFieldsAndCheckSize() {
        Sudoku sudoku = createGridWithSize(0, 0);

        assertEquals(0, sudoku.sizeX());
        assertEquals(0, sudoku.sizeY());
    }

    @Test
    public void createGridWithGivenSize() {
        Sudoku sudoku = createGridWithSize(4, 8);

        assertEquals(4, sudoku.sizeX());
        assertEquals(8, sudoku.sizeY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenTryingToCreateGridWithNegativeWidth() {
        createGridWithSize(-1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenTryingToCreateGridWithNegativeHeight() {
        createGridWithSize(2, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenTryingToCreateGridWithIncorrectData() {
        new Sudoku(2, 2, new Integer[]{
                2, 2,
                3, 4,
                7
        });
    }

    @Test
    public void checkIfGridFieldsHaveNoValuesAfterCreation() {
        Sudoku sudoku = createGridWithSize(2, 2);
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                assertTrue(sudoku.at(x, y) == null);
            }
        }
    }

    @Test
    public void checkIfFieldsAreInitializedCorrectWhenValuesAreGiven() {
        Integer[] values = {
                1, 2,
                3, 4
        };
        Sudoku sudoku = new Sudoku(2, 2, values);

        assertFalse(sudoku.at(0, 0).isEmpty());
        assertEquals(1, sudoku.at(0, 0).value());

        assertFalse(sudoku.at(1, 0).isEmpty());
        assertEquals(2, sudoku.at(1, 0).value());

        assertFalse(sudoku.at(0, 1).isEmpty());
        assertEquals(3, sudoku.at(0, 1).value());

        assertFalse(sudoku.at(1, 1).isEmpty());
        assertEquals(4, sudoku.at(1, 1).value());
    }

    @Test
    public void changeValueAtGivenPosition() {
        Integer[] values = {
                0, 0, 0,
                0, 0, 1,
                0, 0, 0
        };
        Sudoku sudoku = new Sudoku(3, 3, values);

        assertEquals(1, sudoku.at(2, 1).value());
        sudoku.at(2, 1).set(2);
        assertEquals(2, sudoku.at(2, 1).value());
    }

    @Test
    public void iterateOverGrid() {
        Integer[] values = {
                0, 1, 2, 3,
                4, 5, 6, 7,
                8, 9, 10, 11
        };
        Sudoku sudoku = new Sudoku(4, 3, values);

        int currentValue = 0;
        for (Field field : sudoku) {
            assertEquals(currentValue, field.value());
            currentValue++;
        }
    }

    @Test
    public void shouldBeEqualsIfSizeIsEquals() {
        Sudoku testSudoku = createGridWithSize(2, 3);
        Sudoku sameSudoku = createGridWithSize(2, 3);
        Sudoku differentSudoku = createGridWithSize(3, 2);

        assertEquals(testSudoku, sameSudoku);
        assertNotEquals(testSudoku, differentSudoku);
    }

    private Sudoku createGridWithSize(int x, int y) {
        return new Sudoku(x, y);
    }

}
