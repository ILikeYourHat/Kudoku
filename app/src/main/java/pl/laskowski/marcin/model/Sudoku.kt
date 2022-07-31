package pl.laskowski.marcin.model

import pl.laskowski.marcin.type.ISudokuVariant

data class Sudoku(
    val type: ISudokuVariant,
    val board: Board
) {

    val regions = type.divider().divide(board)

    constructor(type: ISudokuVariant) : this(
        type = type,
        board = Board(type.sizeX, type.sizeY) { x, y -> Field(x, y) }
    )

    constructor(type: ISudokuVariant, values: List<Int?>) : this(
        type = type,
        board = Board(type.sizeX, type.sizeY, values)
    )

    override fun toString(): String {
        return board.toString()
    }

    fun at(x: Int, y: Int): Field? {
        return board.at(x, y)
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

    fun at(p: Point): Field? {
        return board.at(p.x, p.y)
    }

    fun isSolved(): Boolean {
        return board.fields()
            .filterNotNull()
            .none { it.isEmpty }
    }

    fun isSolvedCorrectly(): Boolean {
        return isSolved() && regions.all { it.isValid() }
    }

    val allFields = board.fields()
        .filterNotNull()

    val firstEmptyField: Field?
        get() = board.fields()
            .filterNotNull()
            .firstOrNull { it.isEmpty }

    fun values(): List<Int?> {
        return board.fields().map { it?.value }
    }
}