package pl.laskowski.marcin.domain.algorithm;

/**
 * Created by Marcin Laskowski.
 */

public class BruteForceSolverTest {

//    private BruteForceSolver algorithm;
//
//    @Before
//    public void setUp() {
//        SudokuDivider divider =new SingleRegionDivider();
//        algorithm = new BruteForceSolver(divider);
//    }
//
//    @Test
//    public void shouldSolveAlreadySolvedGrid() {
//        Sudoku expectedSolution = new Sudoku(1, 1, new Integer[] {1});
//
//        Sudoku foundSolution = algorithm.solve(expectedSolution);
//
//        assertEquals(expectedSolution, foundSolution);
//    }
//
//    @Test
//    public void shouldSolveEmptyGrid() {
//        Sudoku input = new Sudoku(1, 1, new Integer[]{0});
//        Sudoku expectedSolution = new Sudoku(1, 1, new Integer[] {1});
//
//        Sudoku foundSolution = algorithm.solve(input);
//
//        assertEquals(expectedSolution, foundSolution);
//    }
//
//    @Test
//    public void shouldSolveIncompleteGrid() {
//        Sudoku input = new Sudoku(2, 1, new Integer[] {1, 0});
//        Sudoku expectedSolution = new Sudoku(2, 1, new Integer[] {1, 2});
//
//        Sudoku foundSolution = algorithm.solve(input);
//
//        assertEquals(expectedSolution, foundSolution);
//    }
//
//    @Test
//    public void shouldSolveIncompleteGridAtTheEnd() {
//        Sudoku input = new Sudoku(2, 1, new Integer[] {0, 1});
//        Sudoku expectedSolution = new Sudoku(2, 1, new Integer[] {2, 1});
//
//        Sudoku foundSolution = algorithm.solve(input);
//
//        assertEquals(expectedSolution, foundSolution);
//    }
//
//    @Test
//    public void shouldReturnOriginIfThereIsNoSolution() {
//        Sudoku input = new Sudoku(5, 1, new Integer[] {1, 0, 0, 0, 0});
//
//        Sudoku foundSolution = algorithm.solve(input);
//
//        assertEquals(input, foundSolution);
//    }
//
//    @Test
//    public void shouldReturnToPreviousFields() {
//        SudokuDivider divider = new ComplexDivider(
//                new RowDivider(),
//                new ColumnDivider()
//        );
//
//        algorithm = new BruteForceSolver(divider, 3);
//
//        Sudoku input = new Sudoku(3, 2, new Integer[] {
//                0, 0, 0,
//                0, 0, 3
//        });
//        Sudoku expectedSolution = new Sudoku(3, 2, new Integer[] {
//                1, 3, 2,
//                2, 1, 3
//        });
//
//        Sudoku foundSolution = algorithm.solve(input);
//
//        assertEquals(expectedSolution, foundSolution);
//    }

}
