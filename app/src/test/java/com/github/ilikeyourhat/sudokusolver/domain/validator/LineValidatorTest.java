package com.github.ilikeyourhat.sudokusolver.domain.validator;

public class LineValidatorTest {
//
//    private Validator horizontalValidator;
//    private Validator verticalValidator;
//
//    @Before
//    public void setUp() {
//        horizontalValidator = new HorizontalLinesValidator();
//        verticalValidator = new ColumnDivider();
//    }
//
//    @Test
//    public void shouldSuccessWhenGridHaveNoFields() {
//        Sudoku grid = new Sudoku(0, 0);
//
//        assertTrue(horizontalValidator.validate(grid));
//        assertTrue(verticalValidator.validate(grid));
//    }
//
//    @Test
//    public void shouldSuccessIfGridIsEmpty() {
//        Sudoku grid = new Sudoku(2,2);
//
//        assertTrue(horizontalValidator.validate(grid));
//        assertTrue(verticalValidator.validate(grid));
//    }
//
//    @Test
//    public void horizontalValidatorShouldFailIfThereAreEqualFieldsInAnyRow() {
//        Sudoku grid = new Sudoku(2, 2, new int[]{
//                1, 2,
//                3, 3
//        });
//
//        assertFalse(horizontalValidator.validate(grid));
//        assertTrue(verticalValidator.validate(grid));
//    }
//
//    @Test
//    public void verticalValidatorShouldFailIfThereAreEqualFieldsInAnyColumn() {
//        Sudoku grid = new Sudoku(2, 2, new int[]{
//                3, 2,
//                3, 1
//        });
//
//        assertTrue(horizontalValidator.validate(grid));
//        assertFalse(verticalValidator.validate(grid));
//    }
//
//    @Test
//    public void bothValidatorsShouldSuccessIfThereAreEqualFieldsNotInLine() {
//        Sudoku grid = new Sudoku(2, 2, new int[]{
//                3, 2,
//                1, 3
//        });
//
//        assertTrue(horizontalValidator.validate(grid));
//        assertTrue(verticalValidator.validate(grid));
//    }
//
//    @Test
//    public void shouldValidateOnlySelectedField() {
//        Sudoku grid = new Sudoku(2, 2, new int[]{
//                1, 2,
//                2, 2
//        });
//
////        assertTrue(horizontalValidator.validate(grid, 0, 0));
////        assertTrue(verticalValidator.validate(grid, 0, 0));
//    }
//
//    @Test
//    public void shouldFailForGivenField() {
//        Sudoku grid = new Sudoku(2, 2, new int[]{
//                1, 2,
//                2, 2
//        });
//
////        assertFalse(horizontalValidator.validate(grid, 1, 1));
////        assertFalse(verticalValidator.validate(grid, 1, 1));
//    }

}
