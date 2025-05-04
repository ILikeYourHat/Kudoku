package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import io.github.ilikeyourhat.kudoku.Kudoku
import io.github.ilikeyourhat.kudoku.parsing.SingleLineSudokuParser

class Rate : CliktCommand() {

    val input by argument()

    override fun run() {
        val sudoku = SingleLineSudokuParser().fromText(input)
        val difficulty = Kudoku.rate(sudoku)
        echo(difficulty.toString().lowercase())
    }
}
