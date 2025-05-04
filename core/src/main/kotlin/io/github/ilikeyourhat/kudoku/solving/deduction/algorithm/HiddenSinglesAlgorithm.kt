package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Cell
import io.github.ilikeyourhat.kudoku.model.Region
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid

class HiddenSinglesAlgorithm(
    regions: List<Region>,
    possibilities: SudokuHintGrid
) : DeductionAlgorithm(regions, possibilities) {

    class Factory : DeductionAlgorithm.Factory {
        override fun instance(regions: List<Region>, possibilities: SudokuHintGrid): HiddenSinglesAlgorithm {
            return HiddenSinglesAlgorithm(regions, possibilities)
        }
    }

    override fun solve(region: Region): Boolean {
        for (value in 1..region.size()) {
            val occurrences = mutableSetOf<Cell>()
            for (cell in region) {
                if (possibilities.contains(cell, value)) {
                    occurrences.add(cell)
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
