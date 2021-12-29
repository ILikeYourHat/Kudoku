package pl.laskowski.marcin.model

import pl.laskowski.marcin.type.SudokuVariant
import java.util.HashMap
import java.util.HashSet
import pl.laskowski.marcin.solving.deduction.algorithm.HintEliminationAlgorithm

/**
 * Created by Marcin Laskowski.
 */
class SudokuHintGrid(sudoku: Sudoku, sudokuVariant: SudokuVariant) {
    private val hintMap: MutableMap<Point, MutableSet<Int>>

    init {
        hintMap = HashMap()
        for (f in sudoku) {
            if (f.isEmpty) {
                hintMap[f.position] = allValuesSet(sudokuVariant)
            }
        }
    }

    private fun allValuesSet(sudokuVariant: SudokuVariant): MutableSet<Int> {
        val regionSize = sudokuVariant.regionSize()
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
        fun createAndReduce(sudoku: Sudoku, sudokuVariant: SudokuVariant): SudokuHintGrid {
            val grid = SudokuHintGrid(sudoku, sudokuVariant)
            val algorithm = HintEliminationAlgorithm.Factory()
                .instance(sudokuVariant.divideIntoRegions(sudoku), grid)
            algorithm.solve()
            return grid
        }
    }
}