package pl.laskowski.marcin.model

import pl.laskowski.marcin.type.ISudokuVariant

data class Sudoku(
    val type: ISudokuVariant,
    val board: Board
) {

    val regions = type.divideIntoRegions(board)

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