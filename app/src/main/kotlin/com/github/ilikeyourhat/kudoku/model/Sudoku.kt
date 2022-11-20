package com.github.ilikeyourhat.kudoku.model

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
        return "${type.name} $board"
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

    fun isCompleted(): Boolean {
        return board.fields()
            .filterNotNull()
            .none { it.isEmpty }
    }

    fun isValid(): Boolean {
        return regions.all { it.isValid() }
    }

    fun isSolved(): Boolean {
        return isCompleted() && isValid()
    }

    val allFields = board.fields()
        .filterNotNull()

    fun values(): List<Int?> {
        return board.fields().map { it?.value }
    }
}