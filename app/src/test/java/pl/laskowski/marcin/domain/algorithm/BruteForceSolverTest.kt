package pl.laskowski.marcin.domain.algorithm

import org.junit.Assert.*
import org.junit.Test
import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.model.type.ClassicSquare1x1
import pl.laskowski.marcin.model.type.ClassicSquare2x2
import pl.laskowski.marcin.solving.bruteforce.BruteForceSolver

class BruteForceSolverTest {

    private val algorithm = BruteForceSolver()

    @Test
    fun shouldSolveAlreadySolvedGrid() {
        val expectedSolution = Sudoku(ClassicSquare1x1, listOf(1))
        val foundSolution = algorithm.solve(expectedSolution)
        assertEquals(expectedSolution, foundSolution)
    }

    @Test
    fun shouldSolveEmptyGrid() {
        val input = Sudoku(ClassicSquare1x1, listOf(0))
        val expectedSolution = Sudoku(ClassicSquare1x1, listOf(1))
        val foundSolution = algorithm.solve(input)
        assertEquals(expectedSolution, foundSolution)
    }

    @Test
    fun shouldSolveIncompleteGrid() {
        val input = Sudoku(ClassicSquare2x2, listOf(1, 2, 0, 0))
        val expectedSolution = Sudoku(ClassicSquare2x2, listOf(1, 2, 2, 1))
        val foundSolution = algorithm.solve(input)
        assertEquals(expectedSolution, foundSolution)
    }

    @Test
    fun shouldSolveIncompleteGridAtTheEnd() {
        val input = Sudoku(ClassicSquare2x2, listOf(0, 0, 1, 2))
        val expectedSolution = Sudoku(ClassicSquare2x2, listOf(2, 1, 1, 2))
        val foundSolution = algorithm.solve(input)
        assertEquals(expectedSolution, foundSolution)
    }

    @Test
    fun shouldReturnOriginIfThereIsNoSolution() {
        val input = Sudoku(ClassicSquare2x2, listOf(1, 1, 0, 0))
        val foundSolution = algorithm.solve(input)
        assertEquals(input, foundSolution)
    }



//    @Test
//    fun shouldReturnToPreviousFields() {
//        val input = Sudoku(
//            3, 2, arrayOf(
//                0, 0, 0,
//                0, 0, 3
//            )
//        )
//        val expectedSolution = Sudoku(
//            3, 2, arrayOf(
//                1, 3, 2,
//                2, 1, 3
//            )
//        )
//        val foundSolution = algorithm.solve(input)
//        assertEquals(expectedSolution, foundSolution)
//    }
}