package pl.laskowski.marcin.solving.deduction.solver;

import pl.laskowski.marcin.solving.deduction.algorithm.DeductionAlgorithm;
import pl.laskowski.marcin.solving.deduction.algorithm.HiddenSinglesAlgorithm;
import pl.laskowski.marcin.solving.deduction.algorithm.HintEliminationAlgorithm;
import pl.laskowski.marcin.solving.deduction.algorithm.NakedSinglesAlgorithm;
import pl.laskowski.marcin.type.SudokuVariant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Laskowski.
 */

public class DeductionSolverV1 extends DeductionSolver {

    @Override
    protected List<DeductionAlgorithm.Factory> provideAlgorithms(SudokuVariant type) {
        List<DeductionAlgorithm.Factory> algorithms = new ArrayList<>();
        algorithms.add(new HintEliminationAlgorithm.Factory());
        algorithms.add(new NakedSinglesAlgorithm.Factory());
        algorithms.add(new HiddenSinglesAlgorithm.Factory());
        return algorithms;
    }

}
