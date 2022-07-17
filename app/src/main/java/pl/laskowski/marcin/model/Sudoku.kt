package pl.laskowski.marcin.model

import pl.laskowski.marcin.type.SudokuVariant

data class Sudoku(
    val board: Board,
    val type: SudokuVariant
) {

    val regions = type.divideIntoRegions(this)

    constructor(type: SudokuVariant) : this(
        type = type,
        board = Board(type.sizeX, type.sizeY) { x, y -> Field(x, y) }
    )

    constructor(type: SudokuVariant, values: List<Int?>) : this(
        type = type,
        board = Board(type.sizeY, type.sizeY, values)
    )

    override fun toString(): String {
        return board.toString()
    }

    fun at(x: Int, y: Int): Field? {
        return board.at(x, y)
    }

    fun copy(): Sudoku {
        return Sudoku(board.copy(), type)
    }

//    fun copyWithIndexMapping(mapper: (Point) -> Point): Sudoku {
//        val board = Board(
//            sizeX(),
//            sizeY(),
//
//        )
//        val copy = Sudoku(sizeX(), sizeY())
//        return mapValues(copy, mapper)
//    }
//
//    fun copyRotatedWithIndexMapping(mapper: (Point) -> Point): Sudoku {
//        val copy = Sudoku(sizeY(), sizeX())
//        return mapValues(copy, mapper)
//    }

//    private fun mapValues(target: Sudoku, mapper: (Point) -> Point): Sudoku {
//        target.board.fields()
//
//        return Sudoku(
//            Board(
//
//            )
//        )
//        fields.forEach { x: Int, y: Int ->
//            val (x1, y1) = mapper(Point(x, y))
//            val field = board.at(x, y)
//            if (field != null) {
//                val mappedField = Field(x1, y1)
//                mappedField.set(field.value)
//                target.fields.put(mappedField, x1, y1)
//            }
//        }
//        return target
//    }

    fun subSudoku(startX: Int, startY: Int, endX: Int, endY: Int): Sudoku {
        TODO()
//        return Sudoku(
//            board.fragment(startX, startY, endX, endY),
//        )
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Sudoku

        if (board != other.board) return false

        return true
    }

    override fun hashCode(): Int {
        return board.hashCode()
    }

    val isSolved: Boolean
        get() = board.fields()
                .filterNotNull()
                .none { it.isEmpty }

    val allFields = board.fields()
        .filterNotNull()

    val firstEmptyField: Field?
        get() = board.fields()
            .filterNotNull()
            .firstOrNull { it.isEmpty }

//    fun append(other: Sudoku, anchor: Point) {
//        other.fields.forEachIndexed { x, y, field ->
//            val destinationX = anchor.x + x
//            val destinationY = anchor.y + y
//            if (field == null) {
//                fields.put(null, destinationX, destinationY)
//            } else {
//                var destination = fields[destinationX, destinationY]
//                if (destination == null) {
//                    destination = Field(destinationX, destinationY)
//                }
//                destination.set(field.value)
//                fields.put(destination, destinationX, destinationY)
//            }
//        }
//    }

    fun values(): List<Int?> {
        return board.fields().map { it?.value }
    }
}