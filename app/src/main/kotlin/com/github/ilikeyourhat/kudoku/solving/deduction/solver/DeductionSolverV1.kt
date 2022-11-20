package com.github.ilikeyourhat.kudoku.solving.deduction.solver

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.DeductionAlgorithm
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.HiddenSinglesAlgorithm
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.HintEliminationAlgorithm
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.NakedSinglesAlgorithm

class DeductionSolverV1 : DeductionSolver() {

    override fun provideAlgorithms(type: SudokuType): List<DeductionAlgorithm.Factory> {
        return listOf(
            HintEliminationAlgorithm.Factory(),
            NakedSinglesAlgorithm.Factory(),
            HiddenSinglesAlgorithm.Factory()
        )
    }
}