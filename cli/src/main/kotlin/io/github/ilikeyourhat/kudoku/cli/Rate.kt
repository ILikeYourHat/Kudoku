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
import io.github.ilikeyourhat.kudoku.rating.Difficulty
import io.github.ilikeyourhat.kudoku.rating.defaultRater

class Rate : CliktCommand() {

    override fun help(context: Context): String {
        return context.theme.info(
            """
            Rate a given Sudoku puzzle.
            
            The input should be in a single line format, where digits 1-9 and letters A-P indicate the values, and
            characters [${EmptyCellIndicator.entries.joinToString { it.value.toString() }}] indicate the empty cells.
            
            For all the classic Sudoku types the type parameter is optional, because it can be deduced from the input
            length. For other types it must be passed explicitly.
            
            The output is the evaluated difficulty of the Sudoku: ${Difficulty.entries.joinToString { it.name }}
            """.trimIndent()
        )
    }

    val input by argument()
        .help("The Sudoku puzzle to rate")

    val type by sudokuTypeOption()
        .help(
            "The type of input Sudoku puzzle. Required when the type cannot be deduced from the input format. " +
                "Supported types are: $supportedTypes."
        )

    override fun run() {
        val rater = Sudoku.defaultRater()
        val sudoku = type
            ?.let { Sudoku.fromSingleLineString(it, input) }
            ?: Sudoku.fromSingleLineString(input)

        val difficulty = rater.rate(sudoku)
        echo(difficulty.toString())
    }
}
