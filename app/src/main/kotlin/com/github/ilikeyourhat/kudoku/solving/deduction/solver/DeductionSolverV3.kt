package com.github.ilikeyourhat.kudoku.solving.deduction.solver

import com.github.ilikeyourhat.kudoku.model.SudokuType
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.DeductionAlgorithm
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.HiddenSinglesAlgorithm
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.HiddenValuesAlgorithm
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.HintEliminationAlgorithm
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.NakedSinglesAlgorithm
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.NakedValuesAlgorithm
import com.github.ilikeyourhat.kudoku.solving.deduction.algorithm.RegionIntersectionAlgorithm

class DeductionSolverV3 : DeductionSolver() {

    override fun provideAlgorithms(type: SudokuType): List<DeductionAlgorithm.Factory> {
        val algorithms = mutableListOf(
            HintEliminationAlgorithm.Factory(),
            NakedSinglesAlgorithm.Factory(),
            HiddenSinglesAlgorithm.Factory()
        )
        for (i in 2..type.possibleValues / 2) {
            algorithms.add(NakedValuesAlgorithm.Factory(i))
            algorithms.add(HiddenValuesAlgorithm.Factory(i))
        }
        algorithms.add(RegionIntersectionAlgorithm.Factory())
        return algorithms
    }
}
