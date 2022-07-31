package pl.laskowski.marcin.model

import pl.laskowski.marcin.model.type.BoardCreator
import pl.laskowski.marcin.model.type.SudokuType

data class Sudoku(
    val type: SudokuType,
    val board: Board
) {

    val regions = type.divider().divide(board)

    constructor(type: SudokuType) : this(
        type = type,
        board = BoardCreator.createBoard(type)
    )

    constructor(type: SudokuType, values: List<Int?>) : this(
        type = type,
        board = BoardCreator.createBoard(type)
            .also {
                it.fields().zip(values)
                    .forEach { (field, value) ->
                        field?.set(value!!)
                    }
            }
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