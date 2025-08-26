package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.convert
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.long
import com.github.ajalt.clikt.parameters.types.restrictTo
import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.parsing.EmptyCellIndicator
import io.github.ilikeyourhat.kudoku.rating.Difficulty
import io.github.ilikeyourhat.kudoku.solving.bruteforce.BruteForceSolver
import io.github.ilikeyourhat.kudoku.solving.deduction.solver.DeductionSolverV3
import io.github.ilikeyourhat.kudoku.solving.defaultSolver
import io.github.ilikeyourhat.kudoku.solving.sat.SatSolver
import io.github.ilikeyourhat.kudoku.type.TypesRegistry
import kotlin.random.Random

fun CliktCommand.sudokuTypeOption() = option()
    .convert { type ->
        TypesRegistry.getTypeByName(type)
            ?: fail("$type. Supported types are: $supportedTypes")
    }

fun CliktCommand.sudokuTypeArgument() = argument()
    .convert { type ->
        TypesRegistry.getTypeByName(type)
            ?: fail("$type. Supported types are: $supportedTypes")
    }

enum class SolverArgument { SAT, BRUTEFORCE, DEDUCTION }

fun CliktCommand.solverTypeOption() = option()
    .enum<SolverArgument> { it.name.lowercase() }
    .convert { type ->
        when (type) {
            SolverArgument.SAT -> SatSolver()
            SolverArgument.BRUTEFORCE -> BruteForceSolver()
            SolverArgument.DEDUCTION -> DeductionSolverV3()
        }
    }
    .default(Sudoku.defaultSolver())

fun CliktCommand.difficulty() = option()
    .choice(
        Difficulty.EASY.toString(),
        Difficulty.MEDIUM.toString(),
        Difficulty.HARD.toString(),
        Difficulty.VERY_HARD.toString()
    )
    .enum<Difficulty> { it.name.lowercase() }

fun CliktCommand.count() = option()
    .int()
    .restrictTo(min = 0)
    .default(1)

fun CliktCommand.random() = option("--seed")
    .long()
    .convert { Random(it) }
    .default(Random.Default)

fun CliktCommand.emptyCellIndicator() = option()
    .enum<EmptyCellIndicator> { it.name.lowercase() }
    .default(EmptyCellIndicator.DEFAULT)

val supportedTypes: String
    get() = TypesRegistry.getTypes()
        .map { it.name }
        .sorted()
        // Jigsaw types are currently not supported, because there is no way to pass the custom region info
        .filterNot { it.contains("jigsaw") }
        .joinToString()
