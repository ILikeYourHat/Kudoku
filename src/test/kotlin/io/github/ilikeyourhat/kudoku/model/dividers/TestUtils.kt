package io.github.ilikeyourhat.kudoku.model.dividers

import io.github.ilikeyourhat.kudoku.model.Region
import io.kotest.matchers.collections.shouldContainOnly

fun List<Region>.assertRegionCoordinates(vararg expected: Set<Pair<Int, Int>>) {
    map { region -> region.cells.map { cell -> cell.x to cell.y }.toSet() }
        .shouldContainOnly(*expected)
}
