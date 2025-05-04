package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import io.github.ilikeyourhat.kudoku.generating.defaultGenerator
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.parsing.toSingleLineString

class Generate : CliktCommand() {

    val type by sudokuType()
    val difficulty by difficulty()
    val count by count()

    override fun run() {
        val generator = Sudoku.defaultGenerator()
        repeat(count) {
            val sudoku = generator.generate(type, difficulty)
            echo(sudoku.toSingleLineString())
        }
    }
}
