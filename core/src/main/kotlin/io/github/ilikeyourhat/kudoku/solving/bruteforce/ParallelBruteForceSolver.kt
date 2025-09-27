package io.github.ilikeyourhat.kudoku.solving.bruteforce

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.solving.SudokuSolver
import kotlin.random.Random
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.selects.select

// Warning! This algorithm is currently much slower than raw BruteForceSolver due to the negative effect of shuffling
// fields in random order.
class ParallelBruteForceSolver(
    private val concurrencyLevel: Int,
    private val random: Random = Random.Default,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : SudokuSolver {

    init {
        require(concurrencyLevel > 0) { "Concurrency level must be at least 1" }
    }

    override suspend fun solve(sudoku: Sudoku) = coroutineScope {
        val jobs = List(concurrencyLevel) { spawnSolveJob(sudoku) }
        val result = select { jobs.forEach { job -> job.onAwait { it } } }
        jobs.forEach { it.cancel() }
        result
    }

    private fun CoroutineScope.spawnSolveJob(sudoku: Sudoku): Deferred<Sudoku> {
        return async(dispatcher) {
            BruteForceSolver(random).solve(sudoku)
        }
    }
}
