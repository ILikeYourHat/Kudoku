package io.github.ilikeyourhat.kudoku.solving.bruteforce

import io.github.ilikeyourhat.kudoku.solving.SolverContractTestTemplate
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test

class ParallelBruteForceSolverTest : SolverContractTestTemplate<ParallelBruteForceSolver>(
    solver = ParallelBruteForceSolver(concurrencyLevel = 4)
) {

    @Test
    fun `should throw exception on invalid concurrency argument`() {
        shouldThrow<IllegalArgumentException> {
            ParallelBruteForceSolver(concurrencyLevel = 0)
        }.shouldHaveMessage("Concurrency level must be at least 1")
    }
}
