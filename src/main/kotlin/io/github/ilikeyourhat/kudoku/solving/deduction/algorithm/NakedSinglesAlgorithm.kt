package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Region
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid

class NakedSinglesAlgorithm(
    regions: List<Region>,
    possibilities: SudokuHintGrid
) : DeductionAlgorithm(regions, possibilities) {

    class Factory : DeductionAlgorithm.Factory {
        override fun instance(regions: List<Region>, possibilities: SudokuHintGrid): NakedSinglesAlgorithm {
            return NakedSinglesAlgorithm(regions, possibilities)
        }
    }

    override fun solve(region: Region): Boolean {
        for (cell in region.emptyCells()) {
            val values = possibilities.forCell(cell)
            if (values.size == 1) {
                cell.set(values.single())
                return true
            }
        }
        return false
    }
}
