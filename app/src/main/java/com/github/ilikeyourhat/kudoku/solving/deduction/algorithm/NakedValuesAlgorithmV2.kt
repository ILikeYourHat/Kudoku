package com.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import com.github.ilikeyourhat.kudoku.model.Region
import com.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid

class NakedValuesAlgorithmV2(
    regions: List<Region>,
    possibilities: SudokuHintGrid,
    private val size: Int
) : DeductionAlgorithm(regions, possibilities) {

    class Factory(private val size: Int) : DeductionAlgorithm.Factory {
        override fun instance(regions: List<Region>, possibilities: SudokuHintGrid): NakedValuesAlgorithmV2 {
            return NakedValuesAlgorithmV2(regions, possibilities, size)
        }
    }

    override fun solve(region: Region): Boolean {
        val nakedValues = region.fields
            .map { possibilities.forField(it) }
            .groupingBy { it }
            .eachCount()
            .filterValues { it == size }
            .keys
            .firstOrNull()

        var changed = false
        if (nakedValues != null) {
           for (field in region.fields) {
               if (possibilities.forField(field) != nakedValues) {
                   changed = changed or possibilities.removeAll(field, nakedValues)
               }
           }
        }
        return changed
    }
}