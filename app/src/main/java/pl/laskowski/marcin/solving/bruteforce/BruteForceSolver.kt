package pl.laskowski.marcin.solving.bruteforce

import pl.laskowski.marcin.model.Field
import pl.laskowski.marcin.model.Region
import pl.laskowski.marcin.model.Sudoku
import pl.laskowski.marcin.solving.SudokuSolver
import pl.laskowski.marcin.type.SudokuVariant

class BruteForceSolver(
    override val sudokuVariant: SudokuVariant
) : SudokuSolver {

    internal enum class Direction {
        FORWARD, BACKWARD
    }

    override fun solve(sudoku: Sudoku): Sudoku {
        return if (Thread.interrupted()) {
            sudoku.copy()
        } else {
            Command(sudoku).findSolution()
        }
    }

    private inner class Command(private val origin: Sudoku) {
        private val sudoku: Sudoku = origin.copy()
        private val iterator: ListIterator<Field> = sudoku.allFields.listIterator()
        private val regions: Set<Region> = sudokuVariant.divideIntoRegions(sudoku)

        private var currentDirection = Direction.FORWARD
        private lateinit var currentField: Field

        fun findSolution(): Sudoku {
            while (canMove()) {
                if (Thread.interrupted()) return sudoku
                move()
                if (isOriginFieldEmpty()) {
                    setNextValuesAndCheckGrid()
                }
            }
            return sudoku
        }

        private fun canMove(): Boolean {
            return canMoveForward() || canMoveBackward()
        }

        private fun canMoveForward(): Boolean {
            return currentDirection == Direction.FORWARD && iterator.hasNext()
        }

        private fun canMoveBackward(): Boolean {
            return currentDirection == Direction.BACKWARD && iterator.hasPrevious()
        }

        private fun move() {
            currentField = if (currentDirection == Direction.FORWARD) {
                iterator.next()
            } else {
                iterator.previous()
            }
        }

        private fun changeDirection(newDirection: Direction) {
            if (currentDirection != newDirection) {
                currentDirection = newDirection
                /*
                 * Alternating calls to ListIterator.next() and ListIterator.previous() will return the same element
                 * repeatedly. So to compensate this and return the real next/previous element in the next move, we must
                 * move one step further after every direction change.
                 */
                move()
            }
        }

        private fun isOriginFieldEmpty(): Boolean {
            val x = currentField.x
            val y = currentField.y
            return origin.at(x, y)!!.isEmpty
        }

        private fun setNextValuesAndCheckGrid() {
            val currentNumber = currentField.value()
            for (i in currentNumber + 1..sudokuVariant.regionSize()) {
                currentField.set(i)
                if (isGridCorrectAfterChange()) {
                    changeDirection(Direction.FORWARD)
                    return
                }
            }
            currentField.clear()
            changeDirection(Direction.BACKWARD)
        }

        private fun isGridCorrectAfterChange(): Boolean {
            for (region in regions) {
                if (region.containsFieldWithSamePosition(currentField)) {
                    if (!region.isValid()) return false
                }
            }
            return true
        }
    }
}