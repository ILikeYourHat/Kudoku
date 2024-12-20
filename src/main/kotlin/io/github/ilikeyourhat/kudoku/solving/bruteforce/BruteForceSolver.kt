package io.github.ilikeyourhat.kudoku.solving.bruteforce

import io.github.ilikeyourhat.kudoku.model.Field
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.model.SudokuType
import io.github.ilikeyourhat.kudoku.solving.SudokuSolver
import kotlin.random.Random

class BruteForceSolver(
    private val random: Random? = null
) : SudokuSolver {

    override fun solve(sudoku: Sudoku): Sudoku {
        val result = sudoku.copy()
        val lookup = RegionLookup(result)
        val fields = result.allFields
            .filter { it.isEmpty }
            .applyRandomOrder()

        runAlgorithm(result.type, fields, lookup)
        return result
    }

    private fun runAlgorithm(
        type: SudokuType,
        fields: List<Field>,
        lookup: RegionLookup
    ) {
        var position = 0
        loop@ while (fields.indices.contains(position)) {
            val field = fields[position]
            for (value in field.value..<type.maxValue) {
                field.set(value + 1)
                if (lookup.isGridCorrectAfterChange(field)) {
                    position++
                    continue@loop
                }
            }
            field.clear()
            position--
        }
    }

    private fun List<Field>.applyRandomOrder(): List<Field> {
        return if (random != null) {
            this.shuffled(random)
        } else {
            this
        }
    }

    private class RegionLookup(sudoku: Sudoku) {

        private val regionsMap = sudoku.allFields
            .associate { field ->
                field.position to sudoku.regions.filter { region ->
                    region.contains(field)
                }
            }

        fun isGridCorrectAfterChange(field: Field): Boolean {
            return regionsMap.getValue(field.position)
                .all { it.isValid() }
        }
    }
}
