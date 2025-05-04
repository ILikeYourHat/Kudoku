package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import io.github.ilikeyourhat.kudoku.Kudoku
import io.github.ilikeyourhat.kudoku.parsing.toSingleLineString

class Generate : CliktCommand() {

    val type by sudokuType()
    val difficulty by difficulty()
    val count by count()

    override fun run() {
        repeat(count) {
            val sudoku = Kudoku.create(type, difficulty)
            echo(sudoku.toSingleLineString())
        }
    }
}
