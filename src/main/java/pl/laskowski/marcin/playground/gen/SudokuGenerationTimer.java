package pl.laskowski.marcin.playground.gen;

import pl.laskowski.marcin.creating.SudokuGenerator;
import pl.laskowski.marcin.creating.SudokuRater;
import pl.laskowski.marcin.model.Sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuGenerationTimer {

    private final SudokuRater rater;
    private final SudokuGenerator generator;

    public SudokuGenerationTimer(SudokuRater rater, SudokuGenerator generator) {
        this.rater = rater;
        this.generator = generator;
    }

    public List<Double> measure(int times) {
        List<Double> results = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            new Timer2(generator::generate)
                    .measure((sudoku, time) -> {
                        results.add(time);
                        print(sudoku, time);
                    });
        }
        return results;
    }

    private void print(Sudoku sudoku, double time) {
        String difficulty = rater.rate(sudoku).name();
        double percentFilled  = rater.percentFilled(sudoku) * 100;
        String result = String.format("%.2f %s %.1f", time, difficulty, percentFilled);
        System.out.println(result);
    }

}
