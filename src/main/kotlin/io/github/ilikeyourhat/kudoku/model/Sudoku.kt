package io.github.ilikeyourhat.kudoku.model

data class Sudoku(
    val type: SudokuType,
    val board: Board,
    private val constantRegions: List<Region>,
    private val randomizedRegions: List<Region>
) {

    val regions = constantRegions + randomizedRegions

    override fun toString(): String {
        return if (randomizedRegions.isEmpty()) {
            "${type.name} $board"
        } else {
            "${type.name} $board ${randomizedRegionsString()}"
        }
    }

    private fun randomizedRegionsString(): String {
        val sb = StringBuilder()
        for (y in 0 until board.sizeY()) {
            sb.append('|')
            for (x in 0 until board.sizeX()) {
                val cell = board.getOrNull(x, y)
                if (cell == null) {
                    sb.append('#')
                } else {
                    sb.append(randomizedRegions.indexOfFirst { it.contains(cell) } + 1)
                }
                sb.append(',')
            }
            sb.deleteCharAt(sb.length - 1)
        }
        sb.append('|')
        return sb.toString()
    }

    fun atCellOrNull(x: Int, y: Int): Cell? {
        return board.getOrNull(x, y)
    }

    fun atCell(x: Int, y: Int): Cell {
        return board.getOrNull(x, y) ?: throw NoSuchElementException("Missing cell at position $x,$y")
    }

    fun copy(): Sudoku {
        val newBoard = board.copy()
        val newConstantRegions = constantRegions.copyRegionsFrom(newBoard)
        val newRandomizedRegions = randomizedRegions.copyRegionsFrom(newBoard)
        return Sudoku(type, newBoard, newConstantRegions, newRandomizedRegions)
    }

    private fun List<Region>.copyRegionsFrom(newBoard: Board): List<Region> {
        return map { oldRegion ->
            val newCells = oldRegion.map { oldCell ->
                newBoard.get(oldCell.x, oldCell.y)
            }
            Region(newCells)
        }
    }

    fun sizeX(): Int {
        return board.sizeX()
    }

    fun sizeY(): Int {
        return board.sizeY()
    }

    fun isCompleted(): Boolean {
        return board.cells()
            .none { it.isEmpty }
    }

    fun isValid(): Boolean {
        return regions.all { it.isValid() }
    }

    fun isSolved(): Boolean {
        return isCompleted() && isValid()
    }

    fun isEmpty(): Boolean {
        return board.cells().all { it.isEmpty }
    }

    val allCells = board.cells()

    fun values(): List<Int> {
        return board.cells().map { it.value }
    }

    fun fill(values: List<Int>) {
        val cells = board.cells()
        require(cells.size == values.size) {
            "Incorrect values count, expected ${cells.size}, but was ${values.size}"
        }
        values.forEachIndexed { index, value ->
            require((0..type.maxValue).contains(value)) {
                "Value $value is not supported by type ${type.name}"
            }
            cells[index].set(value)
        }
    }

    companion object {
        operator fun invoke(type: SudokuType): Sudoku {
            return type.createEmpty()
        }

        operator fun invoke(type: SudokuType, values: List<Int>): Sudoku {
            return type.createEmpty()
                .apply { fill(values) }
        }

        operator fun invoke(type: SudokuType, values: List<Int>, regionIds: List<Int>): Sudoku {
            require(type is JigsawSudokuType)
            return type.createEmpty(regionIds)
                .apply { fill(values) }
        }
    }
}
