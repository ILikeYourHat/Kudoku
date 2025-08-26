package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.theme
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.help
import com.github.ajalt.clikt.parameters.options.help
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.parsing.EmptyCellIndicator
import io.github.ilikeyourhat.kudoku.parsing.fromSingleLineString
import io.github.ilikeyourhat.kudoku.parsing.toSingleLineString

class Solve : CliktCommand() {

    override fun help(context: Context): String {
        return context.theme.info(
            """
            Solve a given Sudoku puzzle.
            
            The input should be in a single line format, where digits 1-9 and letters A-P indicate the values, and
            characters [${EmptyCellIndicator.entries.joinToString { it.value.toString() }}] indicate the empty cells.
            
            For all the classic Sudoku types the type parameter is optional, because it can be deduced from the input
            length. For other types it must be passed explicitly.
            
            The output is a Sudoku in a single line format.
            """.trimIndent()
        )
    }

    val input by argument()
        .help("The Sudoku puzzle to solve")

    val type by sudokuTypeOption()
        .help(
            "The type of input Sudoku puzzle. Required when the type cannot be deduced from the input format. " +
                "Supported types are: $supportedTypes."
        )

    val emptyIndicator by emptyCellIndicator()
        .help("The type of empty cell indicator in the output. Defaults to \"zero\" (0 character).")

    val solver by solverTypeOption()
        .help(
            """
            The solver to use for solving the Sudoku puzzle. Currently supported solvers are:
            
            * sat - solver based on the SAT (Boolean Satisfiability Problem) algorithm. Fast and guarantees a solution.
            
            * bruteforce - solver based on the Brute Force algorithm. Slow, but guarantees a solution.
            
            * deduction - solver based on the Deduction algorithm. Fast, but does not guarantee the solution.
            
            If not specified, the default recommended solver would be used (currently: SAT).
            """.trimIndent()
        )

    override fun run() {
        val sudoku = type
            ?.let { Sudoku.fromSingleLineString(it, input) }
            ?: Sudoku.fromSingleLineString(input)

        val solution = solver.solve(sudoku)
        echo(solution.toSingleLineString(emptyIndicator))
    }
}
