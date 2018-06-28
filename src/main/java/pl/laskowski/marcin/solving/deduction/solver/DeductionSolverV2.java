package pl.laskowski.marcin.solving.deduction.solver;

import pl.laskowski.marcin.solving.deduction.algorithm.*;
import pl.laskowski.marcin.type.SudokuVariant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Laskowski.
 */

public class DeductionSolverV2 extends DeductionSolver {

    public DeductionSolverV2(SudokuVariant sudokuVariant) {
        super(sudokuVariant);
    }

    @Override
    protected List<DeductionAlgorithm.Factory> provideAlgorithms() {
        List<DeductionAlgorithm.Factory> algorithms = new ArrayList<>();
        algorithms.add(new HintEliminationAlgorithm.Factory());
        algorithms.add(new NakedSinglesAlgorithm.Factory());
        algorithms.add(new HiddenSinglesAlgorithm.Factory());
        for (int i = 2; i <= sudokuVariant.regionSize() / 2; i++) {
            algorithms.add(new NakedValuesAlgorithm.Factory(i));
            algorithms.add(new HiddenValuesAlgorithm.Factory(i));
        }
        return algorithms;
    }

}
