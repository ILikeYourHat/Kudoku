package pl.laskowski.marcin.domain;

import org.junit.Before;
import org.junit.Test;
import pl.laskowski.marcin.creating.SudokuTransformations;
import pl.laskowski.marcin.model.Sudoku;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marcin Laskowski.
 */

public class SudokuTransformationsTest {
//
//    private final Sudoku input = new Sudoku(3, 3, new Integer[]{
//            0, 3, 4,
//            1, 0, 0,
//            2, 0, 5
//    });
//    private SudokuTransformations shuffler;
//
//    @Before
//    public void setUp() {
//        shuffler = new SudokuTransformations();
//    }
//
//    @Test
//    public void shouldRotateLeft() {
//        Sudoku output = new Sudoku(3, 3, new Integer[]{
//                4, 0, 5,
//                3, 0, 0,
//                0, 1, 2
//        });
//        assertEquals(output, shuffler.rotateLeft(input));
//    }
//
//    @Test
//    public void shouldRotateLeftAndChangeSize() {
//        Sudoku input = new Sudoku(3, 1, new Integer[]{
//                1, 2, 3
//        });
//        Sudoku output = new Sudoku(1, 3, new Integer[]{
//                3,
//                2,
//                1
//        });
//        assertEquals(output, shuffler.rotateLeft(input));
//    }
//
//    @Test
//    public void shouldRotateRight() {
//        Sudoku output = new Sudoku(3, 3, new Integer[]{
//                2, 1, 0,
//                0, 0, 3,
//                5, 0, 4
//        });
//        assertEquals(output, shuffler.rotateRight(input));
//    }
//
//    @Test
//    public void shouldRotateRightAndChangeSize() {
//        Sudoku input = new Sudoku(3, 1, new Integer[]{
//                1, 2, 3
//        });
//        Sudoku output = new Sudoku(1, 3, new Integer[]{
//                1,
//                2,
//                3
//        });
//        assertEquals(output, shuffler.rotateRight(input));
//    }
//
//    @Test
//    public void shouldRotate180() {
//        Sudoku output = new Sudoku(3, 3, new Integer[]{
//                5, 0, 2,
//                0, 0, 1,
//                4, 3, 0
//        });
//        assertEquals(output, shuffler.rotate180(input));
//    }
//
//    @Test
//    public void shouldMirrorByYAxis() {
//        Sudoku output = new Sudoku(3, 3, new Integer[]{
//                4, 3, 0,
//                0, 0, 1,
//                5, 0, 2
//        });
//        assertEquals(output, shuffler.mirrorByYAxis(input));
//    }
//
//    @Test
//    public void shouldMirrorByXAxis() {
//        Sudoku output = new Sudoku(3, 3, new Integer[]{
//                2, 0, 5,
//                1, 0, 0,
//                0, 3, 4
//        });
//        assertEquals(output, shuffler.mirrorByXAxis(input));
//    }
//
//    @Test
//    public void shouldMirrorByFirstDiagonal() {
//        Sudoku output = new Sudoku(3, 3, new Integer[]{
//                0, 1, 2,
//                3, 0, 0,
//                4, 0, 5
//        });
//        assertEquals(output, shuffler.mirrorByFirstDiagonal(input));
//    }
//
//    @Test
//    public void shouldMirrorByFirstDiagonalAndChangeSize() {
//        Sudoku input = new Sudoku(3, 2, new Integer[]{
//                1, 2, 3,
//                4, 5, 6
//        });
//        Sudoku output = new Sudoku(2, 3, new Integer[]{
//                1, 4,
//                2, 5,
//                3, 6
//        });
//        assertEquals(output, shuffler.mirrorByFirstDiagonal(input));
//    }
//
//    @Test
//    public void shouldMirrorBySecondDiagonal() {
//        Sudoku output = new Sudoku(3, 3, new Integer[]{
//                5, 0, 4,
//                0, 0, 3,
//                2, 1, 0
//        });
//        assertEquals(output, shuffler.mirrorBySecondDiagonal(input));
//    }
//
//    @Test
//    public void shouldMirrorBySecondDiagonalAndChangeSize() {
//        Sudoku input = new Sudoku(3, 2, new Integer[]{
//                1, 2, 3,
//                4, 5, 6
//        });
//        Sudoku output = new Sudoku(2, 3, new Integer[]{
//                6, 3,
//                5, 2,
//                4, 1
//        });
//        assertEquals(output, shuffler.mirrorBySecondDiagonal(input));
//    }
//
//    @Test
//    public void shouldSwapGivenRows() {
//        Sudoku output = new Sudoku(3, 3, new Integer[]{
//                0, 3, 4,
//                2, 0, 5,
//                1, 0, 0
//        });
//        assertEquals(output, shuffler.swapRow(input, 1, 2));
//    }
//
//    @Test
//    public void shouldSwapGivenColumns() {
//        Sudoku output = new Sudoku(3, 3, new Integer[]{
//                0, 4, 3,
//                1, 0, 0,
//                2, 5, 0
//        });
//        assertEquals(output, shuffler.swapColumn(input, 1, 2));
//    }
//
//    @Test
//    public void shouldSwapMultipleRows() {
//        Sudoku input = new Sudoku(6, 6, new Integer[]{
//                1, 1, 2, 2, 3, 3,
//                1, 1, 2, 2, 3, 3,
//                4, 4, 5, 5, 6, 6,
//                4, 4, 5, 5, 6, 6,
//                7, 7, 8, 8, 9, 9,
//                7, 7, 8, 8, 9, 9
//        });
//        Sudoku output = new Sudoku(6, 6, new Integer[]{
//                1, 1, 2, 2, 3, 3,
//                1, 1, 2, 2, 3, 3,
//                7, 7, 8, 8, 9, 9,
//                7, 7, 8, 8, 9, 9,
//                4, 4, 5, 5, 6, 6,
//                4, 4, 5, 5, 6, 6
//        });
//        assertEquals(output, shuffler.swapRows(input, 2, 4, 2));
//    }
//
//    @Test
//    public void shouldSwapMultipleColumns() {
//        Sudoku input = new Sudoku(6, 6, new Integer[]{
//                1, 1, 2, 2, 3, 3,
//                1, 1, 2, 2, 3, 3,
//                4, 4, 5, 5, 6, 6,
//                4, 4, 5, 5, 6, 6,
//                7, 7, 8, 8, 9, 9,
//                7, 7, 8, 8, 9, 9
//        });
//        Sudoku output = new Sudoku(6, 6, new Integer[]{
//                1, 1, 3, 3, 2, 2,
//                1, 1, 3, 3, 2, 2,
//                4, 4, 6, 6, 5, 5,
//                4, 4, 6, 6, 5, 5,
//                7, 7, 9, 9, 8, 8,
//                7, 7, 9, 9, 8, 8
//        });
//        assertEquals(output, shuffler.swapColumns(input, 2, 4, 2));
//    }

}
