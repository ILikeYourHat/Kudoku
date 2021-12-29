package pl.laskowski.marcin.model

import pl.laskowski.marcin.model.matrix.ArrayMatrix
import java.lang.StringBuilder

/**
 * Created by Marcin Laskowski.
 */
class Sudoku(sizeX: Int, sizeY: Int) : Iterable<Field?> {

    private val fields = ArrayMatrix<Field>(sizeX, sizeY)

    constructor(sizeX: Int, sizeY: Int, values: Array<Int?>) : this(sizeX, sizeY) {
        require(sizeX * sizeY == values.size) { "Not enough data" }
        fields.forEach { x: Int, y: Int ->
            val value = values[y * sizeX + x]
            if (value != null) {
                val field = Field(x, y)
                field.set(value)
                fields.put(field, x, y)
            }
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append('|')
        for (row in rows()) {
            for (field in row) {
                if (field == null) {
                    sb.append('#')
                } else if (field.isEmpty) {
                    sb.append('_')
                } else {
                    sb.append(field.value)
                }
                sb.append(',')
            }
            sb.deleteCharAt(sb.length - 1)
            sb.append('|')
        }
        return sb.toString()
    }

    fun rows(): List<List<Field?>> {
        val rows: MutableList<List<Field?>> = mutableListOf()
        fields.forEachY { y: Int -> rows.add(row(y)) }
        return rows
    }

    fun columns(): List<List<Field?>> {
        val columns: MutableList<List<Field?>> = mutableListOf()
        fields.forEachX { x: Int -> columns.add(column(x)) }
        return columns
    }

    fun row(y: Int): List<Field?> {
        val row: MutableList<Field?> = mutableListOf()
        fields.forEachX { x: Int -> row.add(at(x, y)) }
        return row
    }

    fun column(x: Int): List<Field?> {
        val column: MutableList<Field?> = mutableListOf()
        fields.forEachY { y: Int -> column.add(at(x, y)) }
        return column
    }

    fun copy(): Sudoku {
        val copy = Sudoku(sizeX(), sizeY())
        fields.forEach { x: Int, y: Int ->
            val field = fields[x, y]
            if (field != null) {
                copy.fields.put(field.copy(), x, y)
            } else {
                copy.fields.put(null, x, y)
            }
        }
        return copy
    }

    fun copyWithIndexMapping(mapper: Point.Mapper): Sudoku {
        val copy = Sudoku(sizeX(), sizeY())
        return mapValues(copy, mapper)
    }

    fun copyRotatedWithIndexMapping(mapper: Point.Mapper): Sudoku {
        val copy = Sudoku(sizeY(), sizeX())
        return mapValues(copy, mapper)
    }

    private fun mapValues(target: Sudoku, mapper: Point.Mapper): Sudoku {
        fields.forEach { x: Int, y: Int ->
            val (x1, y1) = mapper.map(Point(x, y))
            val field = at(x, y)
            if (field != null) {
                val mappedField = Field(x1, y1)
                mappedField.set(field.value)
                target.fields.put(mappedField, x1, y1)
            }
        }
        return target
    }

    fun subSudoku(startX: Int, startY: Int, endX: Int, endY: Int): Sudoku {
        val sudokuFragment = Sudoku(endX - startX, endY - startY)
        fields.forEach { x: Int, y: Int ->
            if (startX <= x && endX > x && startY <= y && endY > y) {
                val field = at(x, y)
                sudokuFragment.fields.put(field, x - startX, y - startY)
            }
        }
        return sudokuFragment
    }

    fun sizeX(): Int {
        return fields.sizeX()
    }

    fun sizeY(): Int {
        return fields.sizeY()
    }

    fun at(x: Int, y: Int): Field? {
        return fields[x, y]
    }

    fun at(p: Point): Field? {
        return fields[p.x, p.y]
    }

    override fun iterator(): MutableListIterator<Field> {
        return allFields.listIterator()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val sudoku = other as Sudoku
        return fields == sudoku.fields
    }

    override fun hashCode(): Int {
        return fields.hashCode()
    }

    val isSolved: Boolean
        get() {
            for (f in this) {
                if (f.isEmpty) return false
            }
            return true
        }
    val allFields: MutableList<Field>
        get() {
            val allFields: MutableList<Field> = mutableListOf()
            fields.forEach { x: Int, y: Int ->
                val f = fields[x, y]
                if (f != null) {
                    allFields.add(f)
                }
            }
            return allFields
        }
    val firstEmptyField: Field?
        get() {
            for (f in this) {
                if (f.isEmpty) {
                    return f
                }
            }
            return null
        }

    fun append(other: Sudoku, anchor: Point) {
        other.fields.forEach { x: Int, y: Int ->
            val destinationX = anchor.x + x
            val destinationY = anchor.y + y
            val source = other.fields[x, y]
            if (source == null) {
                fields.put(null, destinationX, destinationY)
            } else {
                var destination = fields[destinationX, destinationY]
                if (destination == null) {
                    destination = Field(destinationX, destinationY)
                }
                destination.set(source.value)
                fields.put(destination, destinationX, destinationY)
            }
        }
    }

    companion object {
        @JvmStatic
        fun blank(sizeX: Int, sizeY: Int): Sudoku {
            val sudoku = Sudoku(sizeX, sizeY)
            sudoku.fields.forEach { x: Int, y: Int ->
                val f = Field(x, y)
                sudoku.fields.put(f, x, y)
            }
            return sudoku
        }
    }
}