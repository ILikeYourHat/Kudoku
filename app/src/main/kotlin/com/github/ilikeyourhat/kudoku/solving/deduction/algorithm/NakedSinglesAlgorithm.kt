package com.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import com.github.ilikeyourhat.kudoku.model.Region
import com.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid

class NakedSinglesAlgorithm (
    regions: List<Region>,
    possibilities: SudokuHintGrid
) : DeductionAlgorithm(regions, possibilities) {

    class Factory : DeductionAlgorithm.Factory {
        override fun instance(regions: List<Region>, possibilities: SudokuHintGrid): NakedSinglesAlgorithm {
            return NakedSinglesAlgorithm(regions, possibilities)
        }
    }

    override fun solve(region: Region): Boolean {
        for (field in region.emptyFields()) {
            val values = possibilities.forField(field)
            if (values.size == 1) {
                field.set(values.single())
                return true
            }
        }
        return false
    }
}