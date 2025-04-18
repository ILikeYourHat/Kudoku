package io.github.ilikeyourhat.kudoku.model

data class Sudoku(
    val type: SudokuType,
    val board: Board,
    val regions: List<Region>
) {

    override fun toString(): String {
        return "${type.name} $board"
    }

    fun atCellOrNull(x: Int, y: Int): Cell? {
        return board.getOrNull(x, y)
    }

    fun atCell(x: Int, y: Int): Cell {
        return board.getOrNull(x, y) ?: throw NoSuchElementException("Missing cell at position $x,$y")
    }

    fun copy(): Sudoku {
        val newBoard = board.copy()
        val newRegions = regions
            .map { oldRegion ->
                val newCells = oldRegion.map { oldCell ->
                    newBoard.get(oldCell.x, oldCell.y)
                }
                Region(newCells)
            }
        return Sudoku(type, newBoard, newRegions)
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
