package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.parsing.fromSingleLineString
import io.github.ilikeyourhat.kudoku.parsing.toSingleLineString

class Solve : CliktCommand() {

    val input by argument()
    val type by sudokuTypeOption()
    val solver by solverTypeOption()

    override fun run() {
        val sudoku = type
            ?.let { Sudoku.fromSingleLineString(it, input) }
            ?: Sudoku.fromSingleLineString(input)

        val solution = solver.solve(sudoku)
        echo(solution.toSingleLineString())
    }
}
