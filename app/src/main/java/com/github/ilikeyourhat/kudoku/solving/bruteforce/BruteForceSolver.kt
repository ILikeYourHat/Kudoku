package com.github.ilikeyourhat.kudoku.solving.bruteforce

import com.github.ilikeyourhat.kudoku.model.Field
import com.github.ilikeyourhat.kudoku.model.Sudoku
import com.github.ilikeyourhat.kudoku.solving.SudokuSolver

class BruteForceSolver: SudokuSolver {

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
            for (i in currentNumber + 1..sudoku.type.possibleValues) {
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
            return sudoku.regions
                .filter { it.contains(currentField) }
                .all { it.isValid() }
        }
    }
}