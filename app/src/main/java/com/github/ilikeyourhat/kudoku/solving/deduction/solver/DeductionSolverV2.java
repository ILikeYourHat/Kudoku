package com.github.ilikeyourhat.kudoku.solving.deduction.solver;

import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.*;
import com.github.ilikeyourhat.kudoku.model.SudokuType;

import java.util.ArrayList;
import java.util.List;

public class DeductionSolverV2 extends DeductionSolver {

    @Override
    protected List<DeductionAlgorithm.Factory> provideAlgorithms(SudokuType type) {
        List<DeductionAlgorithm.Factory> algorithms = new ArrayList<>();
        algorithms.add(new HintEliminationAlgorithm.Factory());
        algorithms.add(new NakedSinglesAlgorithm.Factory());
        algorithms.add(new HiddenSinglesAlgorithm.Factory());
        for (int i = 2; i <= type.getPossibleValues() / 2; i++) {
            algorithms.add(new NakedValuesAlgorithm.Factory(i));
            algorithms.add(new HiddenValuesAlgorithm.Factory(i));
        }
        return algorithms;
    }
}
