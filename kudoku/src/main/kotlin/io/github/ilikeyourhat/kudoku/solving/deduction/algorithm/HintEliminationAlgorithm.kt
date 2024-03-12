package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Field
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
        for (field in region.fullFields()) {
            changed = changed or
                    ensureFullFieldHaveNoHints(field) or
                    removeHintFromRegion(region, field.value())
        }
        return changed
    }

    private fun ensureFullFieldHaveNoHints(field: Field): Boolean {
        if (!possibilities.isEmpty(field)) {
            possibilities.clear(field)
            return true
        }
        return false
    }

    private fun removeHintFromRegion(region: Region, value: Int): Boolean {
        var changed = false
        for (field in region) {
            changed = changed or possibilities.remove(field, value)
        }
        return changed
    }
}
