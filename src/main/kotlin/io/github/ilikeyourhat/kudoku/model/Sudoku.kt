package io.github.ilikeyourhat.kudoku.model

data class Sudoku(
    val type: SudokuType,
    val board: Board,
    val regions: List<Region>
) {

    override fun toString(): String {
        return "${type.name} $board"
    }

    fun atFieldOrNull(x: Int, y: Int): Field? {
        return board.getOrNull(x, y)
    }

    fun atField(x: Int, y: Int): Field {
        return board.getOrNull(x, y) ?: throw NoSuchElementException("Missing field at position $x,$y")
    }

    fun copy(): Sudoku {
        val newBoard = board.copy()
        val newRegions = type.divider().divide(newBoard)
        return Sudoku(type, newBoard, newRegions)
    }

    fun sizeX(): Int {
        return board.sizeX()
    }

    fun sizeY(): Int {
        return board.sizeY()
    }

    fun isCompleted(): Boolean {
        return board.fields()
            .none { it.isEmpty }
    }

    fun isValid(): Boolean {
        return regions.all { it.isValid() }
    }

    fun isSolved(): Boolean {
        return isCompleted() && isValid()
    }

    fun isEmpty(): Boolean {
        return board.fields().all { it.isEmpty }
    }

    val allFields = board.fields()

    fun values(): List<Int> {
        return board.fields().map { it.value }
    }

    fun fill(values: List<Int>) {
        val fields = board.fields()
        require(fields.size == values.size) {
            "Incorrect values count, expected ${fields.size}, but was ${values.size}"
        }
        values.forEachIndexed { index, value ->
            require((0..type.maxValue).contains(value)) {
                "Value $value is not supported by type ${type.name}"
            }
            fields[index].set(value)
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
    }
}
