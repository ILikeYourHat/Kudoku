package io.github.ilikeyourhat.kudoku.solving.deduction.solver

import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.solving.deduction.algorithm.DeductionAlgorithm
import io.github.ilikeyourhat.kudoku.solving.deduction.algorithm.HiddenSinglesAlgorithm
import io.github.ilikeyourhat.kudoku.solving.deduction.algorithm.HiddenValuesAlgorithm
import io.github.ilikeyourhat.kudoku.solving.deduction.algorithm.HintEliminationAlgorithm
import io.github.ilikeyourhat.kudoku.solving.deduction.algorithm.NakedSinglesAlgorithm
import io.github.ilikeyourhat.kudoku.solving.deduction.algorithm.NakedValuesAlgorithm

class DeductionSolverV2 : DeductionSolver() {
    override fun provideAlgorithms(type: SudokuType): List<DeductionAlgorithm.Factory> {
        val algorithms = mutableListOf(
            HintEliminationAlgorithm.Factory(),
            NakedSinglesAlgorithm.Factory(),
            HiddenSinglesAlgorithm.Factory()
        )
        for (i in 2..type.maxValue / 2) {
            algorithms.add(NakedValuesAlgorithm.Factory(i))
            algorithms.add(HiddenValuesAlgorithm.Factory(i))
        }
        return algorithms
    }
}
