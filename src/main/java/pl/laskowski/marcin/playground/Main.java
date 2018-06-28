package pl.laskowski.marcin.playground;

import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.solving.SudokuSolver;
import pl.laskowski.marcin.solving.sat.SatSolver;

public class Main {

    public static void main(String... args) {
        TestSet set = TestSet.SMALL_SAMURAI;
        SudokuSolver solver = new SatSolver(set.getSudokuVariant());
        for (Sudoku s : set.getData()) {
            System.out.println(solver.solve(s));
        }
    }

}
