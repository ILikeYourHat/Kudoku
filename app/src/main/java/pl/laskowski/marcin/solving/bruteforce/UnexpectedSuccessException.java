package pl.laskowski.marcin.solving.bruteforce;

import pl.laskowski.marcin.model.Sudoku;

public class UnexpectedSuccessException extends RuntimeException {

    private Sudoku solution;

    public UnexpectedSuccessException(Sudoku solution) {
        this.solution = solution;
    }

    public Sudoku getSolution() {
        return solution;
    }

}
