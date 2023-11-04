package com.github.ilikeyourhat.kudoku.solving.deduction.algorithm

import com.github.ilikeyourhat.kudoku.model.Region
import com.github.ilikeyourhat.kudoku.model.hint.SudokuHintGrid
import com.github.ilikeyourhat.kudoku.solving.deduction.combinations.CollectionCombinator

class RegionIntersectionAlgorithm(
    regions: List<Region>,
    possibilities: SudokuHintGrid
) : DeductionAlgorithm(regions, possibilities) {

    class Factory : DeductionAlgorithm.Factory {
        override fun instance(regions: List<Region>, possibilities: SudokuHintGrid): RegionIntersectionAlgorithm {
            return RegionIntersectionAlgorithm(regions, possibilities)
        }
    }

    override fun solve(): Boolean {
        var changed = false
        val combinator = CollectionCombinator(2)
        combinator.iterate(regions) { values: List<Region> ->
            val first = values[0]
            val second = values[1]
            changed = changed or solve(first, second)
        }
        return changed
    }

    override fun solve(region: Region): Boolean {
        throw UnsupportedOperationException("This algorithm operates on two regions")
    }

    private fun solve(region1: Region, region2: Region): Boolean {
        var changed = false
        val intersection = region1.intersect(region2)
        if (!intersection.isEmpty()) {
            val regionSub1 = region1.subtract(intersection)
            val regionSub2 = region2.subtract(intersection)
            changed = changed or solve(regionSub1, regionSub2, intersection)
        }
        return changed
    }

    private fun solve(region1: Region, region2: Region, intersection: Region): Boolean {
        var changed = false
        val hintsFromIntersection = possibilities.forRegion(intersection)
        val hintsFromRegion1 = possibilities.forRegion(region1)
        val hintsFromRegion2 = possibilities.forRegion(region2)
        for (value in hintsFromIntersection) {
            if (hintsFromRegion1.contains(value) && !hintsFromRegion2.contains(value)) {
                if (possibilities.remove(region1, value)) {
                    changed = true
                }
            } else if (!hintsFromRegion1.contains(value) && hintsFromRegion2.contains(value)) {
                if (possibilities.remove(region2, value)) {
                    changed = true
                }
            }
        }
        return changed
    }
}
