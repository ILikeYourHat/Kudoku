package pl.laskowski.marcin.domain.validator;

/**
 * Created by Marcin Laskowski.
 */

public class BlockValidatorTest {
//
//    @Test
//    public void shouldReturnSingleRegion() {
//        Sudoku grid = new Sudoku(1, 1);
//        BlockValidator validator = new BlockValidator(1, 1);
//        List<List<Field>> regions = validator.divideIntoRegions(grid);
//
//        assertEquals(1, regions.size());
//        assertEachRegionSize(1, regions);
//    }
//
//    @Test
//    public void shouldDivideGridIntoTwoRegionsWithOneFieldEach() {
//        Sudoku grid = new Sudoku(1, 2);
//        BlockValidator validator = new BlockValidator(1, 1);
//        List<List<Field>> regions = validator.divideIntoRegions(grid);
//
//        assertEquals(2, regions.size());
//        assertEachRegionSize(1, regions);
//    }
//
//    @Test
//    public void shouldDivideGridIntoTwoRegionsWithManyFieldsEach() {
//        Sudoku grid = new Sudoku(8, 8);
//        BlockValidator validator = new BlockValidator(2, 2);
//        List<List<Field>> regions = validator.divideIntoRegions(grid);
//
//        assertEquals(16, regions.size());
//        assertEachRegionSize(4, regions);
//    }
//
//    @Test
//    public void shouldDivideGridIntoManyRegions() {
//        Sudoku grid = new Sudoku(1, 2);
//        BlockValidator validator = new BlockValidator(1, 1);
//        List<List<Field>> regions = validator.divideIntoRegions(grid);
//
//        assertEquals(2, regions.size());
//        assertEachRegionSize(1, regions);
//    }
//
//
//    @Test
//    public void shouldNotAllowToCreateInvalidBlocks() {
//        try {
//            new BlockValidator(0, 5);
//            fail("IllegalArgumentException should be thrown");
//        } catch (IllegalArgumentException e) {
//            assertThat(e.getMessage(), is("Block x size must be greater than 0"));
//        }
//
//        try {
//            new BlockValidator(5, 0);
//            fail("IllegalArgumentException should be thrown");
//        } catch (IllegalArgumentException e) {
//            assertThat(e.getMessage(), is("Block y size must be greater than 0"));
//        }
//    }
//
//    @Test
//    public void shouldValidateGridsBeingChecked() {
//        BlockValidator validator = new BlockValidator(2, 2);
//        validator.validate(new Sudoku(2, 2));
//        validator.validate(new Sudoku(6, 4));
//        validator.validate(new Sudoku(2, 2));
//        validator.validate(new Sudoku(6, 4));
//
//        try {
//            validator.validate(new Sudoku(3, 2));
//            fail("IllegalArgumentException should be thrown");
//        } catch (IllegalArgumentException e) {
//            assertThat(e.getMessage(), is("Sudoku horizontal size must be divisible by 2, actual: 3"));
//        }
//
//        try {
//            validator.validate(new Sudoku(2, 3), 0, 0);
//            fail("IllegalArgumentException should be thrown");
//        } catch (IllegalArgumentException e) {
//            assertThat(e.getMessage(), is("Sudoku vertical size must be divisible by 2, actual: 3"));
//        }
//
//        try {
//            validator.validate(new Sudoku(5, 2));
//            fail("IllegalArgumentException should be thrown");
//        } catch (IllegalArgumentException e) {
//            assertThat(e.getMessage(), is("Sudoku horizontal size must be divisible by 2, actual: 5"));
//        }
//
//        try {
//            validator.validate(new Sudoku(2, 5), 0, 0);
//            fail("IllegalArgumentException should be thrown");
//        } catch (IllegalArgumentException e) {
//            assertThat(e.getMessage(), is("Sudoku vertical size must be divisible by 2, actual: 5"));
//        }
//
//    }
//
//    @Test
//    public void shouldSuccessIfGridIsEmpty() {
//        BlockValidator validator = new BlockValidator(2, 2);
//        Sudoku grid = new Sudoku(2, 2, new int[]{
//                0, 0,
//                0, 0
//        });
//        assertTrue(validator.validate(grid));
//    }
//
//    @Test
//    public void shouldFailIfThereAreDuplicatesInBlock() {
//        BlockValidator validator = new BlockValidator(2, 2);
//        Sudoku grid = new Sudoku(2, 2, new int[]{
//                2, 0,
//                0, 2
//        });
//
//        assertFalse(validator.validate(grid, 0, 0));
//    }
//
//    @Test
//    public void shouldSuccessIfThereAreNoDuplicatesInBlocks() {
//        BlockValidator validator = new BlockValidator(2, 2);
//        Sudoku grid = new Sudoku(4, 2, new int[]{
//                2, 0, 0, 2,
//                0, 0, 0, 0
//        });
//
//        assertTrue(validator.validate(grid));
//    }
//
//    @Test
//    public void shouldSuccessIfThereAreNoDuplicatesInGivenBlock() {
//        BlockValidator validator = new BlockValidator(2, 2);
//        Sudoku grid = new Sudoku(4, 2, new int[]{
//                2, 0, 0, 2,
//                0, 2, 0, 0
//        });
//
//        assertTrue(validator.validate(grid, 2, 1));
//    }
//
//    private void assertEachRegionSize(int expectedSize, List<List<Field>> regions) {
//        for (int i = 0; i < regions.size(); i++) {
//            List<Field> region = regions.get(i);
//            int regionSize = region.size();
//            String message = String.format("Expected size %d but was %d at index %d", expectedSize, regionSize, i);
//            assertEquals(message, expectedSize, region.size());
//        }
//    }

}
