package pl.laskowski.marcin.playground;


import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.model.type.*;
import pl.laskowski.marcin.parsing.text.SudokuTextFormatParser;
import pl.laskowski.marcin.type.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public enum TestSet implements Iterable<Sudoku> {
    PROJECT_EULER_9x9("/txt/9x9.txt") {
        @Override
        public SudokuType getSudokuVariant() {
            return ClassicSquare9x9.INSTANCE;
        }
    },
    TRIVIAL_9x9("/txt/9x9_trivial.txt") {
        @Override
        public SudokuType getSudokuVariant() {
            return ClassicSquare9x9.INSTANCE;
        }
    },
    DEDUCTION_EXTREME("/txt/9x9_deductionExtreme.txt") {
        @Override
        public SudokuType getSudokuVariant() {
            return ClassicSquare9x9.INSTANCE;
        }
    },
    BRUTE_FORCE_EXTREME("/txt/9x9_bruteForceExtreme.txt") {
        @Override
        public SudokuType getSudokuVariant() {
            return ClassicSquare9x9.INSTANCE;
        }
    },
    EXAMPLE_DIAGONAL("/txt/9x9_diagonal.txt") {
        @Override
        public SudokuType getSudokuVariant() {
            return DiagonalSquare9x9.INSTANCE;
        }
    },
    EXAMPLE_16x16("/txt/16x16.txt") {
        @Override
        public SudokuType getSudokuVariant() {
            return ClassicSquare16x16.INSTANCE;
        }
    },
    EXAMPLE_25x25("/txt/25x25.txt") {
        @Override
        public SudokuType getSudokuVariant() {
            return ClassicSquare25x25.INSTANCE;
        }
    },
    SMALL_SAMURAI("/txt/15x15_samuraiDiagonal.txt") {
        @Override
        public SudokuType getSudokuVariant() {
            return TripleBackslash15x15.INSTANCE;
        }
    },
    BIG_SAMURAI("/txt/21x21_samuraiCross.txt") {
        @Override
        public SudokuType getSudokuVariant() {
            return SamuraiClassic21x21.INSTANCE;
        }
    },
    COLOSSUS_SAMURAI("/txt/30x30_samuraiCross.txt") {
        @Override
        public SudokuType getSudokuVariant() {
            return SamuraiButterfly30x30.INSTANCE;
        }
    },
    TEST("/txt/test.txt") {
        public SudokuType getSudokuVariant() {
            return ClassicSquare9x9.INSTANCE;
        }
    };

    private final List<Sudoku> sudokus;

    TestSet(String path) {
        SudokuTextFormatParser parser = new SudokuTextFormatParser();
        this.sudokus = parser.parseMany(loadFile(path));
    }

    private String loadFile(String path) {
        InputStream is = TestSet.class.getResourceAsStream(path);
        return new BufferedReader(new InputStreamReader(is))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public abstract SudokuType getSudokuVariant();

    public List<Sudoku> getData() {
        return sudokus;
    }

    @Override
    public Iterator<Sudoku> iterator() {
        return sudokus.iterator();
    }

}
