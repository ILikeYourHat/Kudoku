package pl.laskowski.marcin.model.hint

import pl.laskowski.marcin.model.Field
import pl.laskowski.marcin.model.Point
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.Sudoku
import java.util.HashMap
import java.util.HashSet
import pl.laskowski.marcin.solving.deduction.algorithm.HintEliminationAlgorithm
import pl.laskowski.marcin.model.type.SudokuType

class SudokuHintGrid(sudoku: Sudoku) {
    private val hintMap: MutableMap<Point, MutableSet<Int>>

    init {
        hintMap = HashMap()
        for (f in sudoku.allFields) {
            if (f.isEmpty) {
                hintMap[f.position] = allValuesSet(sudoku.type)
            }
        }
    }

    private fun allValuesSet(sudokuVariant: SudokuType): MutableSet<Int> {
        val regionSize = sudokuVariant.regionSize
        return (1..regionSize).toMutableSet()
    }

    fun forField(field: Field): Set<Int> {
        return HashSet(getFor(field))
    }

    fun forRegion(region: Region): Set<Int> {
        val set: MutableSet<Int> = HashSet()
        for (field in region) {
            set.addAll(getFor(field))
        }
        return set
    }

    fun isEmpty(field: Field): Boolean {
        return getFor(field).isEmpty()
    }

    fun clear(field: Field) {
        getFor(field).clear()
    }

    fun remove(field: Field, value: Int): Boolean {
        return getFor(field).remove(value)
    }

    fun remove(region: Region, value: Int): Boolean {
        var removed = false
        for (field in region) {
            removed = removed or getFor(field).remove(value)
        }
        return removed
    }

    fun contains(field: Field, value: Int): Boolean {
        return getFor(field).contains(value)
    }

    fun replace(field: Field, hints: Set<Int>) {
        val set = getFor(field)
        set.clear()
        set.addAll(hints)
    }

    fun removeAll(field: Field, hintsToRemove: Set<Int>) {
        getFor(field).removeAll(hintsToRemove)
    }

    private fun getFor(field: Field): MutableSet<Int> {
        return hintMap.getOrDefault(field.position(), mutableSetOf())
    }

    companion object {
        @JvmStatic
        fun createAndReduce(sudoku: Sudoku): SudokuHintGrid {
            val grid = SudokuHintGrid(sudoku)
            val algorithm = HintEliminationAlgorithm.Factory()
                .instance(sudoku.regions, grid)
            algorithm.solve()
            return grid
        }
    }
}