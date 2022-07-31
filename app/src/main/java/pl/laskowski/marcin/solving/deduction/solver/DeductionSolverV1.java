package pl.laskowski.marcin.solving.deduction.solver;

import pl.laskowski.marcin.solving.deduction.algorithm.DeductionAlgorithm;
import pl.laskowski.marcin.solving.deduction.algorithm.HiddenSinglesAlgorithm;
import pl.laskowski.marcin.solving.deduction.algorithm.HintEliminationAlgorithm;
import pl.laskowski.marcin.solving.deduction.algorithm.NakedSinglesAlgorithm;
import pl.laskowski.marcin.model.type.SudokuType;

import java.util.ArrayList;
import java.util.List;

public class DeductionSolverV1 extends DeductionSolver {

    @Override
    protected List<DeductionAlgorithm.Factory> provideAlgorithms(SudokuType type) {
        List<DeductionAlgorithm.Factory> algorithms = new ArrayList<>();
        algorithms.add(new HintEliminationAlgorithm.Factory());
        algorithms.add(new NakedSinglesAlgorithm.Factory());
        algorithms.add(new HiddenSinglesAlgorithm.Factory());
        return algorithms;
    }

}
