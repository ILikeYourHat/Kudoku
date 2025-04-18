package io.github.ilikeyourhat.kudoku.generating

import io.github.ilikeyourhat.kudoku.model.Board
import io.github.ilikeyourhat.kudoku.model.Cell
import io.github.ilikeyourhat.kudoku.model.Region
import io.github.ilikeyourhat.kudoku.model.SudokuType
import kotlin.random.Random

class RandomRegionGenerator(
    private val random: Random = Random.Default
) {

    fun generateRandomRegions(
        type: SudokuType,
        board: Board
    ): List<Region> {
        val cells = board.cells()
        val regionCount = cells.size / type.maxValue
        var regions = initialDivision(cells, regionCount)
        regions = adjustment(regions, type.maxValue)
        return regions.map { Region(it) }
    }

    private fun initialDivision(cells: List<Cell>, regionSize: Int): List<MutableList<Cell>> {
        val cellsList = cells.toMutableList()
        val seeds = cellsList.shuffled(random).take(regionSize)
        cellsList.removeAll(seeds)
        val regions = seeds.map { mutableListOf(it) }

        while (cellsList.isNotEmpty()) {
            val cell = cellsList.random(random)
            regions.shuffled(random)
            var found = false

            for (region in regions) {
                if (found) break
                val adjacent = region.any { it.isAdjacent(cell) }
                if (adjacent) {
                    cellsList.remove(cell)
                    region.add(cell)
                    found = true
                }
            }
        }
        return regions
    }

    private fun adjustment(regions: List<MutableList<Cell>>, desiredRegionSize: Int): List<MutableList<Cell>> {
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
        region: MutableList<Cell>,
        regions: List<MutableList<Cell>>
    ) {
        val candidates = regions.flatten()
            .filterNot { it in region }
            .filter { cell -> region.any { it.isAdjacent(cell) } }

        val candidate = candidates.shuffled(random)
            .firstOrNull { isRegionStillValidWithoutCell(regions, it) }

        if (candidate != null) {
            regions.forEach { it.remove(candidate) }
            region.add(candidate)
        }
    }

    private fun giveCellToOtherRegion(
        region: MutableList<Cell>,
        regions: List<MutableList<Cell>>
    ) {
        val candidate = region.shuffled(random)
            .filter { isRegionStillValidWithoutCell(regions, it) }
            .firstOrNull { cell ->
                regions.minusElement(region)
                    .any { it.any { otherCell -> cell.isAdjacent(otherCell) } }
            }

        if (candidate != null) {
            region.remove(candidate)
            regions.minusElement(region)
                .first { it.any { cell -> cell.isAdjacent(candidate) } }
                .add(candidate)
        }
    }

    private fun isRegionStillValidWithoutCell(
        regions: List<MutableList<Cell>>,
        cell: Cell
    ): Boolean {
        val affectedRegion = regions.first { it.contains(cell) }.minus(cell)
        if (affectedRegion.isEmpty()) return false
        val group = mutableSetOf<Cell>()
        val stack = ArrayDeque<Cell>()
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
