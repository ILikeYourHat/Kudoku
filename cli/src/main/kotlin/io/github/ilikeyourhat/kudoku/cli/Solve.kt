package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import io.github.ilikeyourhat.kudoku.Kudoku
import io.github.ilikeyourhat.kudoku.parsing.SingleLineSudokuParser
import io.github.ilikeyourhat.kudoku.parsing.toSingleLineString

class Solve : CliktCommand() {

    val input by argument()

    override fun run() {
        val sudoku = SingleLineSudokuParser().fromText(input)
        val solver = Kudoku.defaultSolver()
        val solution = solver.solve(sudoku)
        echo(solution.toSingleLineString())
    }
}
