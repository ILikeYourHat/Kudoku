package io.github.ilikeyourhat.kudoku.model

data class Sudoku(
    val type: SudokuType,
    val board: Board
) {

    val regions = type.divider().divide(board)

    constructor(type: SudokuType) : this(
        type = type,
        board = BoardCreator.createBoard(type)
    )

    constructor(type: SudokuType, values: List<Int>) : this(
        type = type,
        board = BoardCreator.createBoard(type)
            .also {
                val fields = it.fields()
                require(fields.size == values.size) {
                    "Incorrect values count, expected ${fields.size}, but was ${values.size}"
                }
                values.forEachIndexed { index, value ->
                    require(value == 0 || type.allPossibleValues().contains(value)) {
                        "Value $value is not supported by type ${type.name}"
                    }
                    fields[index].set(value)
                }
            }
    )

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
        return Sudoku(type, board.copy())
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
}
