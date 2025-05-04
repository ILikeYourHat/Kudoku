package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands

class Main : NoOpCliktCommand()

// expose the command for testing
val rootCommand = Main()
    .subcommands(Solve(), Generate(), Rate())

fun main(args: Array<String>) = rootCommand.main(args)
