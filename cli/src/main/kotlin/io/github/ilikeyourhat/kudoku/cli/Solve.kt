package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.parsing.fromSingleLineString
import io.github.ilikeyourhat.kudoku.parsing.toSingleLineString
import io.github.ilikeyourhat.kudoku.solving.defaultSolver

class Solve : CliktCommand() {

    val input by argument()
    val type by sudokuTypeOption()

    override fun run() {
        val sudoku = type
            ?.let { Sudoku.fromSingleLineString(it, input) }
            ?: Sudoku.fromSingleLineString(input)

        val solver = Sudoku.defaultSolver()
        val solution = solver.solve(sudoku)
        echo(solution.toSingleLineString())
    }
}
