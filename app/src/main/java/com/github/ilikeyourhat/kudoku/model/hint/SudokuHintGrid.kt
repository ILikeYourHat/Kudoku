package com.github.ilikeyourhat.kudoku.model.hint

import com.github.ilikeyourhat.kudoku.model.Field
import com.github.ilikeyourhat.kudoku.model.Point
import com.github.ilikeyourhat.kudoku.model.Region
import com.github.ilikeyourhat.kudoku.model.Sudoku

class SudokuHintGrid(sudoku: Sudoku) {

    private val hintMap: Map<Point, MutableSet<Int>>

    init {
        val possibleValues = sudoku.type.allPossibleValues()

        hintMap = sudoku.allFields
            .filter { it.isEmpty }
            .associate { it.position to possibleValues.toMutableSet() }
    }

    fun forField(field: Field): Set<Int> {
        return getFor(field).toSet()
    }

    fun forRegion(region: Region): Set<Int> {
        return region.fields
            .fold(emptySet()) { set, field -> set + getFor(field) }
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
        getFor(field).apply {
            clear()
            addAll(hints)
        }
    }

    fun removeAll(field: Field, hintsToRemove: Set<Int>) {
        getFor(field).removeAll(hintsToRemove)
    }

    private fun getFor(field: Field): MutableSet<Int> {
        return hintMap[field.position()] ?: mutableSetOf()
    }
}