package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Cell
import io.github.ilikeyourhat.kudoku.model.Region
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid

class HintEliminationAlgorithm(
    regions: List<Region>,
    possibilities: SudokuHintGrid
) : DeductionAlgorithm(regions, possibilities) {

    class Factory : DeductionAlgorithm.Factory {
        override fun instance(regions: List<Region>, possibilities: SudokuHintGrid): HintEliminationAlgorithm {
            return HintEliminationAlgorithm(regions, possibilities)
        }
    }

    override fun solve(): Boolean {
        return regions
            .map { solve(it) }
            .any { it }
    }

    override fun solve(region: Region): Boolean {
        var changed = false
        for (cell in region.fullCells()) {
            changed = changed or
                ensureFullCellHaveNoHints(cell) or
                removeHintFromRegion(region, cell.value())
        }
        return changed
    }

    private fun ensureFullCellHaveNoHints(cell: Cell): Boolean {
        if (!possibilities.isEmpty(cell)) {
            possibilities.clear(cell)
            return true
        }
        return false
    }

    private fun removeHintFromRegion(region: Region, value: Int): Boolean {
        var changed = false
        for (cell in region) {
            changed = changed or possibilities.remove(cell, value)
        }
        return changed
    }
}
