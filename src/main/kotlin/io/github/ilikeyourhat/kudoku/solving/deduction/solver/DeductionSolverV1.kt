package io.github.ilikeyourhat.kudoku.solving.deduction.solver

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.solving.deduction.algorithm.DeductionAlgorithm
import io.github.ilikeyourhat.kudoku.solving.deduction.algorithm.HiddenSinglesAlgorithm
import io.github.ilikeyourhat.kudoku.solving.deduction.algorithm.HintEliminationAlgorithm
import io.github.ilikeyourhat.kudoku.solving.deduction.algorithm.NakedSinglesAlgorithm

class DeductionSolverV1 : DeductionSolver() {

    override fun provideAlgorithms(type: SudokuType): List<DeductionAlgorithm.Factory> {
        return listOf(
            HintEliminationAlgorithm.Factory(),
            NakedSinglesAlgorithm.Factory(),
            HiddenSinglesAlgorithm.Factory()
        )
    }
}
