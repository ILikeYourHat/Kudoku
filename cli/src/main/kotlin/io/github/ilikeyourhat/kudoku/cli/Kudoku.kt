package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.command.SuspendingCliktCommand
import com.github.ajalt.clikt.command.main
import com.github.ajalt.clikt.core.subcommands

class Kudoku : SuspendingCliktCommand() {
    override suspend fun run() = Unit
}

// expose the command for testing
val rootCommand = Kudoku()
    .subcommands(Solve(), Generate(), Rate())

suspend fun main(args: Array<String>) {
    rootCommand.main(args)
}
