package pl.laskowski.marcin.creating;

import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.solving.SudokuSolver;
import pl.laskowski.marcin.solving.deduction.solver.DeductionSolverV1;
import pl.laskowski.marcin.solving.deduction.solver.DeductionSolverV2;
import pl.laskowski.marcin.solving.deduction.solver.DeductionSolverV3;
import pl.laskowski.marcin.type.SudokuVariant;

import java.util.List;

import static pl.laskowski.marcin.creating.SudokuRater.Difficulty.*;

/**
 * Created by Marcin Laskowski.
 */

public class SudokuRater {

    public float percentFilled(Sudoku s) {
        List<Field> fields = s.getAllFields();
        long total = fields.size();
        long empty = fields.stream().filter(Field::isEmpty).count();
        return (total - (float) empty) / total;
    }

    public enum Difficulty {EASY, MEDIUM, HARD, DIABOLIC;
        public boolean harderThan(Difficulty difficulty) {
            return this.ordinal() > difficulty.ordinal();
        }
    }

    private SudokuSolver easySolver;
    private SudokuSolver mediumSolver;
    private SudokuSolver hardSolver;

    public SudokuRater(SudokuVariant variant) {
        easySolver = new DeductionSolverV1(variant);
        mediumSolver = new DeductionSolverV2(variant);
        hardSolver = new DeductionSolverV3(variant);
    }

    public Difficulty rate(Sudoku sudoku) {
        if (easySolver.solve(sudoku).isSolved()) {
            return EASY;
        } else if (mediumSolver.solve(sudoku).isSolved()) {
            return MEDIUM;
        } else if (hardSolver.solve(sudoku).isSolved()) {
            return HARD;
        } else {
            return DIABOLIC;
        }
    }

}
