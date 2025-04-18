package io.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import io.github.ilikeyourhat.kudoku.model.Region
import io.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid
import io.github.ilikeyourhat.kudoku.solving.deduction.combinations.CollectionCombinator

class NakedValuesAlgorithm(
    regions: List<Region>,
    possibilities: SudokuHintGrid,
    private val size: Int
) : DeductionAlgorithm(regions, possibilities) {

    class Factory(private val size: Int) : DeductionAlgorithm.Factory {
        override fun instance(regions: List<Region>, possibilities: SudokuHintGrid): NakedValuesAlgorithm {
            return NakedValuesAlgorithm(regions, possibilities, size)
        }
    }

    override fun solve(region: Region): Boolean {
        val list = mutableListOf<Set<Int>>()
        var changed = false
        for (cell in region) {
            val hints = possibilities.forCell(cell)
            if (hints.isNotEmpty()) {
                list.add(hints)
            }
        }
        val combinator = CollectionCombinator(size)
        combinator.iterate(list) { values: List<Set<Int>> ->
            val sum = mutableSetOf<Int>()
            for (set in values) {
                sum.addAll(set)
            }
            if (sum.size == size) {
                changed = changed or clearFromRegion(region, sum)
            }
        }
        return changed
    }

    private fun clearFromRegion(region: Region, hintsToRemove: Set<Int>): Boolean {
        var changed = false
        for (cell in region) {
            val hints = possibilities.forCell(cell)
            if (hints.isNotEmpty() && shouldBeCleared(hints, hintsToRemove)) {
                possibilities.removeAll(cell, hintsToRemove)
                changed = true
            }
        }
        return changed
    }

    private fun shouldBeCleared(currentHints: Set<Int>, hintsToRemove: Set<Int>): Boolean {
        val tempHints = currentHints.toMutableSet()
        tempHints.removeAll(hintsToRemove)
        return tempHints.isNotEmpty() && currentHints.size > tempHints.size
    }
}
