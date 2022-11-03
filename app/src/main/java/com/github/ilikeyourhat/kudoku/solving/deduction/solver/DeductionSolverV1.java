package com.github.ilikeyourhat.kudoku.solving.deduction.solver;

import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.DeductionAlgorithm;
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.HiddenSinglesAlgorithm;
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.HintEliminationAlgorithm;
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.NakedSinglesAlgorithm;
import com.github.ilikeyourhat.kudoku.model.SudokuType;

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
