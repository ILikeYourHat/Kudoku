package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.parsing.fromSingleLineString
import io.github.ilikeyourhat.kudoku.rating.defaultRater

class Rate : CliktCommand() {

    val input by argument()
    val type by sudokuTypeOption()

    override fun run() {
        val rater = Sudoku.defaultRater()
        val sudoku = type
            ?.let { Sudoku.fromSingleLineString(it, input) }
            ?: Sudoku.fromSingleLineString(input)

        val difficulty = rater.rate(sudoku)
        echo(difficulty.toString().lowercase())
    }
}
