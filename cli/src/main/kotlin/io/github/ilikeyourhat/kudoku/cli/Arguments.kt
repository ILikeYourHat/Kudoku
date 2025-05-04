package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.restrictTo
import io.github.ilikeyourhat.kudoku.rating.Difficulty
import io.github.ilikeyourhat.kudoku.type.BUILD_IN_TYPES

fun CliktCommand.sudokuType() = argument()
    .choice(BUILD_IN_TYPES.associateBy { it.name })

fun CliktCommand.difficulty() = option()
    .enum<Difficulty>()

fun CliktCommand.count() = option()
    .int()
    .restrictTo(min = 0)
    .default(1)
