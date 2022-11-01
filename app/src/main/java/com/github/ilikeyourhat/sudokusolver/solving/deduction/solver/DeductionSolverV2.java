package com.github.ilikeyourhat.sudokusolver.solving.deduction.solver;

import com.github.ilikeyourhat.sudokusolver.solving.deduction.algorithm.*;
import com.github.ilikeyourhat.sudokusolver.model.type.SudokuType;

import java.util.ArrayList;
import java.util.List;

public class DeductionSolverV2 extends DeductionSolver {

    @Override
    protected List<DeductionAlgorithm.Factory> provideAlgorithms(SudokuType type) {
        List<DeductionAlgorithm.Factory> algorithms = new ArrayList<>();
        algorithms.add(new HintEliminationAlgorithm.Factory());
        algorithms.add(new NakedSinglesAlgorithm.Factory());
        algorithms.add(new HiddenSinglesAlgorithm.Factory());
        for (int i = 2; i <= type.getRegionSize() / 2; i++) {
            algorithms.add(new NakedValuesAlgorithm.Factory(i));
            algorithms.add(new HiddenValuesAlgorithm.Factory(i));
        }
        return algorithms;
    }
}