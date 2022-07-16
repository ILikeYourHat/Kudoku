package pl.laskowski.marcin.solving.bruteforce;


import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.solving.SudokuSolver;
import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.type.SudokuVariant;

import java.util.ListIterator;
import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public class BruteForceSolver implements SudokuSolver {

    private final SudokuVariant sudokuVariant;

    enum Direction {
        FORWARD, BACKWARD
    }

    public BruteForceSolver(SudokuVariant sudokuVariant) {
        this.sudokuVariant = sudokuVariant;
    }

    @Override
    public SudokuVariant getSudokuVariant() {
        return sudokuVariant;
    }

    @Override
    public Sudoku solve(Sudoku origin) {
        if (Thread.interrupted()) return null;
        return new Command(origin).findSolution();
    }

    private class Command {

        private final Sudoku origin;
        private final Sudoku sudoku;
        private Direction currentDirection = Direction.FORWARD;
        private ListIterator<Field> iterator;
        private Field currentField;
        private Set<Region> regions;

        private Command(Sudoku origin) {
            this.origin = origin;
            this.sudoku = origin.copy();
            this.iterator = sudoku.getAllFields().listIterator();
            this.regions = sudokuVariant.divideIntoRegions(sudoku);
        }

        private Sudoku findSolution() {
            while (canMove()) {
                if (Thread.interrupted()) return null;
                move();
                if (isOriginFieldEmpty()) {
                    setNextValuesAndCheckGrid();
                }
            }
            return sudoku;
        }

        private boolean canMove() {
            return canMoveForward() || canMoveBackward();
        }

        private boolean canMoveForward() {
            return currentDirection.equals(Direction.FORWARD) && iterator.hasNext();
        }

        private boolean canMoveBackward() {
            return currentDirection.equals(Direction.BACKWARD) && iterator.hasPrevious();
        }

        private void move() {
            if (currentDirection.equals(Direction.FORWARD)) {
                currentField = iterator.next();
            } else {
                currentField = iterator.previous();
            }
        }

        private void changeDirection(Direction newDirection) {
            if (currentDirection != newDirection) {
                currentDirection = newDirection;
                /*
                 * Alternating calls to ListIterator.next() and ListIterator.previous() will return the same element
                 * repeatedly. So to compensate this and return the real next/previous element in the next move, we must
                 * move one step further after every direction change.
                 *
                 * See java.util.ListIterator for more details.
                 */
                move();
            }
        }

        private boolean isOriginFieldEmpty() {
            int x = currentField.getX();
            int y = currentField.getY();
            return origin.at(x, y).isEmpty();
        }

        private void setNextValuesAndCheckGrid() {
            int currentNumber = currentField.value();
            for (int i = currentNumber + 1; i <= sudokuVariant.regionSize(); i++) {
                currentField.set(i);
                if (isGridCorrectAfterChange()) {
                    changeDirection(Direction.FORWARD);
                    return;
                }
            }
            currentField.clear();
            changeDirection(Direction.BACKWARD);
        }

        private boolean isGridCorrectAfterChange() {
            for (Region region : regions) {
                if (region.containsFieldWithSamePosition(currentField)) {
                    if (!region.isValid()) return false;
                }
            }
            return true;
        }

    }

}
