package com.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import com.github.ilikeyourhat.kudoku.model.Field
import com.github.ilikeyourhat.kudoku.model.Region
import com.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid

class HiddenSinglesAlgorithm(
    regions: List<Region>,
    possibilities: SudokuHintGrid
) : DeductionAlgorithm(regions, possibilities) {

    class Factory : DeductionAlgorithm.Factory {
        override fun instance(regions: List<Region>, possibilities: SudokuHintGrid): HiddenSinglesAlgorithm {
            return HiddenSinglesAlgorithm(regions, possibilities)
        }
    }

    override fun solve(region: Region) : Boolean {
        for (value in 1..region.size()) {
            val occurrences = mutableSetOf<Field>()
            for (field in region) {
                if (possibilities.contains(field, value)) {
                    occurrences.add(field)
                }
            }
            if (occurrences.size == 1) {
                occurrences.single().set(value)
                return true
            }
        }
        return false
    }
}
