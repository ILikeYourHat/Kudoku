package io.github.ilikeyourhat.kudoku.generating

import io.github.ilikeyourhat.kudoku.model.Field
import io.github.ilikeyourhat.kudoku.model.Region
import io.github.ilikeyourhat.kudoku.model.SudokuType
import kotlin.random.Random

class RandomRegionGenerator(
    private val random: Random = Random.Default
) {

    fun generateRandomRegions(
        type: SudokuType,
        fields: List<Field>
    ): List<Region> {
        val regionCount = fields.size / type.maxValue
        var regions = initialDivision(fields, regionCount)
        regions = adjustment(regions, type.maxValue)
        return regions.map { Region(it) }
    }

    private fun initialDivision(fields: List<Field>, regionSize: Int): List<MutableList<Field>> {
        val cells = fields.toMutableList()
        val seeds = cells.shuffled(random).take(regionSize)
        cells.removeAll(seeds)
        val regions = seeds.map { mutableListOf(it) }

        while (cells.isNotEmpty()) {
            val cell = cells.random(random)
            regions.shuffled(random)
            var found = false

            for (region in regions) {
                if (found) break
                val adjacent = region.any { it.isAdjacent(cell) }
                if (adjacent) {
                    cells.remove(cell)
                    region.add(cell)
                    found = true
                }
            }
        }
        return regions
    }

    private fun adjustment(regions: List<MutableList<Field>>, desiredRegionSize: Int): List<MutableList<Field>> {
        while (regions.any { it.size != desiredRegionSize }) {
            val region = regions.random(random)
            if (region.size < desiredRegionSize) {
                takeCellFromOtherRegion(region, regions)
            }
            if (region.size > desiredRegionSize) {
                giveCellToOtherRegion(region, regions)
            }
        }
        return regions
    }

    private fun takeCellFromOtherRegion(
        region: MutableList<Field>,
        regions: List<MutableList<Field>>
    ) {
        val candidates = regions.flatten()
            .filterNot { it in region }
            .filter { cell -> region.any { it.isAdjacent(cell) } }

        val candidate = candidates.shuffled(random)
            .firstOrNull { isRegionStillValidWithoutField(regions, it) }

        if (candidate != null) {
            regions.forEach { it.remove(candidate) }
            region.add(candidate)
        }
    }

    private fun giveCellToOtherRegion(
        region: MutableList<Field>,
        regions: List<MutableList<Field>>
    ) {
        val candidate = region.shuffled(random)
            .filter { isRegionStillValidWithoutField(regions, it) }
            .firstOrNull { cell ->
                regions.minusElement(region)
                    .any { it.any { field -> field.isAdjacent(cell) } }
            }

        if (candidate != null) {
            region.remove(candidate)
            regions.minusElement(region)
                .first { it.any { field -> field.isAdjacent(candidate) } }
                .add(candidate)
        }
    }

    private fun isRegionStillValidWithoutField(
        regions: List<MutableList<Field>>,
        cell: Field
    ): Boolean {
        val affectedRegion = regions.first { it.contains(cell) }.minus(cell)
        if (affectedRegion.isEmpty()) return false
        val group = mutableSetOf<Field>()
        val stack = ArrayDeque<Field>()
        stack.add(affectedRegion.first())

        while (stack.isNotEmpty()) {
            val value = stack.removeFirst()
            group.add(value)
            val notCheckedNeighbors = affectedRegion
                .filterNot { it in group }
                .filter { it.isAdjacent(value) }
            stack.addAll(notCheckedNeighbors)
        }

        return affectedRegion.size == group.size
    }
}
