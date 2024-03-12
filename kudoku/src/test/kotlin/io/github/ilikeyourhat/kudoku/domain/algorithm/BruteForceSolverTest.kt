package io.github.ilikeyourhat.kudoku.domain.algorithm

import io.github.ilikeyourhat.kudoku.model.Sudoku
import io.github.ilikeyourhat.kudoku.type.Square1x1
import io.github.ilikeyourhat.kudoku.type.Square2x2
import io.github.ilikeyourhat.kudoku.solving.bruteforce.BruteForceSolver
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BruteForceSolverTest {

    private val algorithm = BruteForceSolver()

    @Test
    fun shouldSolveAlreadySolvedGrid() {
        val expectedSolution = Sudoku(Square1x1, listOf(1))
        val foundSolution = algorithm.solve(expectedSolution)
        assertEquals(expectedSolution, foundSolution)
    }

    @Test
    fun shouldSolveEmptyGrid() {
        val input = Sudoku(Square1x1, listOf(0))
        val expectedSolution = Sudoku(Square1x1, listOf(1))
        val foundSolution = algorithm.solve(input)
        assertEquals(expectedSolution, foundSolution)
    }

    @Test
    fun shouldSolveIncompleteGrid() {
        val input = Sudoku(Square2x2, listOf(1, 2, 0, 0))
        val expectedSolution = Sudoku(Square2x2, listOf(1, 2, 2, 1))
        val foundSolution = algorithm.solve(input)
        assertEquals(expectedSolution, foundSolution)
    }

    @Test
    fun shouldSolveIncompleteGridAtTheEnd() {
        val input = Sudoku(Square2x2, listOf(0, 0, 1, 2))
        val expectedSolution = Sudoku(Square2x2, listOf(2, 1, 1, 2))
        val foundSolution = algorithm.solve(input)
        assertEquals(expectedSolution, foundSolution)
    }

    @Test
    fun shouldReturnOriginIfThereIsNoSolution() {
        val input = Sudoku(Square2x2, listOf(1, 1, 0, 0))
        val foundSolution = algorithm.solve(input)
        assertEquals(input, foundSolution)
    }
}
