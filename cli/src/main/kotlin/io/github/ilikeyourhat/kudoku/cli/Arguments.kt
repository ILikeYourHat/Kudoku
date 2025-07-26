package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.convert
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.long
import com.github.ajalt.clikt.parameters.types.restrictTo
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.rating.Difficulty
import io.github.ilikeyourhat.kudoku.solving.defaultSolver
import io.github.ilikeyourhat.kudoku.type.TypesRegistry
import kotlin.random.Random

fun CliktCommand.sudokuTypeArgument() = argument()
    .convert { type ->
        TypesRegistry.getTypeByName(type)
            ?: fail("$type. Run `help types` to see supported Sudoku types.") // TODO no such help exists yet
    }

fun CliktCommand.sudokuTypeOption() = option()
    .convert { type ->
        TypesRegistry.getTypeByName(type)
            ?: fail("$type. Run `help types` to see supported Sudoku types.") // TODO no such help exists yet
    }

fun CliktCommand.solverTypeOption() = option()
    .convert { type ->
        CommandLineRegistry.getSolver(type)
            ?: fail("$type. Run `help solvers` to see supported Sudoku solvers.") // TODO no such help exists yet
    }
    .default(Sudoku.defaultSolver())

fun CliktCommand.difficulty() = option()
    .enum<Difficulty>()

fun CliktCommand.count() = option()
    .int()
    .restrictTo(min = 0)
    .default(1)

fun CliktCommand.random() = option("--seed")
    .long()
    .convert { Random(it) }
    .default(Random.Default)
