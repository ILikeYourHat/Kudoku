package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.theme
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.arguments.help
import com.github.ajalt.clikt.parameters.options.help
import io.github.ilikeyourhat.kudoku.generating.defaultGenerator
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.parsing.toSingleLineString
import io.github.ilikeyourhat.kudoku.type.Classic9x9

class Generate : CliktCommand() {

    override fun help(context: Context): String {
        return context.theme.info(
            """
            Generate a Sudoku puzzle with given requirements.
            
            The output is a Sudoku in a single line format, with "0" as the empty cell indicator.
            """.trimIndent()
        )
    }

    val type by sudokuTypeArgument()
        .help("The type of the Sudoku to generate. Defaults to classic_9x9. Supported types are: $supportedTypes.")
        .default(Classic9x9)

    val difficulty by difficulty()
        .help {
            "The difficulty of the generated sudoku. If not provided, the difficulty will not be taken into " +
                "consideration when generating."
        }

    val count by count()
        .help { "Number of Sudokus to generate, defaults to 1" }

    val emptyIndicator by emptyCellIndicator()
        .help("The type of empty cell indicator in the output. Defaults to \"zero\" (0 character).")

    val random by random()
        .help { "The seed for the RNG implementation." }

    override fun run() {
        val generator = Sudoku.defaultGenerator(random)
        repeat(count) {
            val sudoku = generator.generate(type, difficulty)
            echo(sudoku.toSingleLineString(emptyIndicator))
        }
    }
}
