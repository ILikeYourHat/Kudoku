package pl.laskowski.marcin.solving.sat;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;
import org.sat4j.tools.SingleSolutionDetector;
import pl.laskowski.marcin.creating.SudokuSolutionCount;
import pl.laskowski.marcin.model.Field;
import pl.laskowski.marcin.model.Point;
import pl.laskowski.marcin.model.Region;
import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.solving.SudokuSolver;
import pl.laskowski.marcin.type.SudokuVariant;

import java.util.Set;

/**
 * Created by Marcin Laskowski.
 */

public class SatSolver implements SudokuSolver {

    private final SudokuVariant sudokuVariant;

    public SatSolver(SudokuVariant sudokuVariant) {
        this.sudokuVariant = sudokuVariant;
    }

    @Override
    public SudokuVariant getSudokuVariant() {
        return sudokuVariant;
    }

    @Override
    public Sudoku solve(Sudoku sudoku) {
        return new Command(sudoku).solve();
    }

    public SudokuSolutionCount checkSolutions(Sudoku sudoku) {
        return new Command(sudoku).checkSolutions();
    }

    private class Command {

        private final Sudoku sudoku;
        private final IndexEncoder ie;
        private final Set<Region> regions;
        private final ISolver solver;

        Command(Sudoku sudoku) {
            this.sudoku = sudoku;
            this.ie = new IndexEncoder(sudoku.sizeX(), sudoku.sizeY());
            this.regions = sudokuVariant.divideIntoRegions(sudoku);
            this.solver = SolverFactory.newDefault();
            this.solver.setTimeout(60);
        }

        Sudoku solve() {
            try {
                initSolver();
                int[] model = solver.findModel();
                return applyResult(model);
            } catch (ContradictionException | TimeoutException e) {
                return null;
            } finally {
                solver.reset();
            }
        }

        SudokuSolutionCount checkSolutions() {
            try {
                initSolver();
                SingleSolutionDetector solutionDetector = new SingleSolutionDetector(solver);
                if (solutionDetector.isSatisfiable()) {
                    if (solutionDetector.hasASingleSolution()) {
                        return SudokuSolutionCount.ONE;
                    } else {
                        return SudokuSolutionCount.MANY;
                    }
                } else {
                    return SudokuSolutionCount.NONE;
                }
            } catch (ContradictionException | TimeoutException e) {
                return SudokuSolutionCount.NONE;
            } finally {
                solver.reset();
            }
        }

        private void initSolver() throws ContradictionException {
            addCausesForRegions();
            addCausesForFields();
        }


        private Sudoku applyResult(int[] result) {
            Sudoku solution = sudoku.copy();
            for (int index : result) {
                if (index > 0) {
                    Point p = ie.decodePoint(index);
                    int v = ie.decodeValue(index);
                    solution.at(p).set(v);
                }
            }
            return solution;
        }

        private void addCausesForFields() throws ContradictionException {
            for (Field field : sudoku.getAllFields()) {
                solver.addExactly(createValues(field), 1);
                if (!field.isEmpty()) {
                    int index = ie.encode(field.position(), field.value());
                    solver.addClause(new VecInt(new int[]{index}));
                }
            }
        }

        private void addCausesForRegions() throws ContradictionException {
            for (int possibleValue = 1; possibleValue <= sudokuVariant.regionSize(); possibleValue++) {
                for (Region region : regions) {
                    VecInt vec = new VecInt(region.size());
                    for (Field field : region) {
                        int index = ie.encode(field.position(), possibleValue);
                        vec.push(index);
                    }
                    solver.addExactly(vec, 1);
                }
            }
        }

        private VecInt createValues(Field field) {
            VecInt vec = new VecInt(sudokuVariant.regionSize());
            for (int value = 1; value <= sudokuVariant.regionSize(); value++) {
                int index = ie.encode(field.position(), value);
                vec.push(index);
            }
            return vec;
        }

    }
}
