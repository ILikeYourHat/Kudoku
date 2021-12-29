package pl.laskowski.marcin.creating;

import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.playground.TestSet;
import pl.laskowski.marcin.type.ClassicSquare;

/**
 * Created by Marcin Laskowski.
 */

public class ClassicSudokuGenerator extends SudokuGenerator {

    private final SudokuShuffler shuffler;
    private final Sudoku template;

    public ClassicSudokuGenerator(SudokuRater.Difficulty difficulty, Float percentFilled) {
        super(new ClassicSquare(9), difficulty, percentFilled);
        this.shuffler = new SudokuShuffler();
        this.template = TestSet.TEST.getData().get(0);
    }

    @Override
    protected Sudoku getFilledSudoku() {
        return shuffler.shuffleFull(template.copy(), new ClassicSquare(9));
    }

}
