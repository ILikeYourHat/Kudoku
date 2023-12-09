package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Region
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid

abstract class DeductionAlgorithm(
    val regions: List<Region>,
    val possibilities: SudokuHintGrid
) {

    interface Factory {
        fun instance(regions: List<Region>, possibilities: SudokuHintGrid): DeductionAlgorithm
    }

    open fun solve(): Boolean {
        return regions.asSequence()
            .map { solve(it) }
            .find { it } ?: false
    }

    abstract fun solve(region: Region): Boolean
}
