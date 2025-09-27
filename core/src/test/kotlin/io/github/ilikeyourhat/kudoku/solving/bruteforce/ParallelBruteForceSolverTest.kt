package io.github.ilikeyourhat.kudoku.solving.bruteforce

import io.github.ilikeyourhat.kudoku.solving.SolverContractTestTemplate

class ParallelBruteForceSolverTest : SolverContractTestTemplate<ParallelBruteForceSolver>(
    solver = ParallelBruteForceSolver(concurrencyLevel = 4)
)
