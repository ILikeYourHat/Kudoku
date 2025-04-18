package io.github.ilikeyourhat.kudoku.model

data class Region(
    val cells: List<Cell>
) : Iterable<Cell> {

    override fun iterator() = cells.iterator()

    fun size() = cells.size

    fun isEmpty() = cells.isEmpty()

    fun intersect(other: Region): Region {
        val result = cells.filter { other.cells.contains(it) }
        return Region(result)
    }

    fun subtract(other: Region): Region {
        val result = cells.filterNot { other.cells.contains(it) }
        return Region(result)
    }

    fun isValid(): Boolean {
        val set = mutableSetOf<Int>()
        for (cell in cells) {
            if (!cell.isEmpty && !set.add(cell.value)) {
                return false
            }
        }
        return true
    }

    fun fullCells(): List<Cell> {
        return cells.filterNot { it.isEmpty }
    }

    fun emptyCells(): List<Cell> {
        return cells.filter { it.isEmpty }
    }
}
