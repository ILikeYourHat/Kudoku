package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Region
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid
import io.github.ilikeyourhat.kudoku.solving.deduction.combinations.CollectionCombinator
import java.util.Collections

class HiddenValuesAlgorithm(
    regions: List<Region>,
    possibilities: SudokuHintGrid,
    private val size: Int
) : DeductionAlgorithm(regions, possibilities) {

    class Factory(private val size: Int) : DeductionAlgorithm.Factory {
        override fun instance(regions: List<Region>, possibilities: SudokuHintGrid): HiddenValuesAlgorithm {
            return HiddenValuesAlgorithm(regions, possibilities, size)
        }
    }

    private val combinator = CollectionCombinator(size)

    override fun solve(region: Region): Boolean {
        var changed = false
        val allHintValues = possibilities.forRegion(region)
        combinator.iterate(allHintValues) { result ->
            val values = result.toSet()
            val fields = countFieldsThatContainsValues(region, values)
            if (fields == size) {
                changed = changed or removeAllOtherValues(region, values)
            }
        }
        return changed
    }

    private fun countFieldsThatContainsValues(region: Region, values: Set<Int>): Int {
        var sum = 0
        for (field in region) {
            val hints = possibilities.forField(field)
            if (!Collections.disjoint(hints, values)) {
                sum++
            }
        }
        return sum
    }

    private fun removeAllOtherValues(region: Region, values: Set<Int>): Boolean {
        var changed = false
        for (field in region) {
            val hints = possibilities.forField(field).toMutableSet()
            val initialSize = hints.size
            if (!Collections.disjoint(hints, values)) {
                hints.removeIf { !values.contains(it) }
            }
            if (initialSize != hints.size) {
                possibilities.replace(field, hints)
                changed = true
            }
        }
        return changed
    }
}
