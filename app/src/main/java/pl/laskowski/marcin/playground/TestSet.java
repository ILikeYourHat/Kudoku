package pl.laskowski.marcin.playground;


import pl.laskowski.marcin.model.Sudoku;
import pl.laskowski.marcin.model.type.ClassicSquare16x16;
import pl.laskowski.marcin.model.type.ClassicSquare25x25;
import pl.laskowski.marcin.model.type.ClassicSquare9x9;
import pl.laskowski.marcin.model.type.DiagonalSquare9x9;
import pl.laskowski.marcin.parsing.text.SudokuTextFormatParser;
import pl.laskowski.marcin.type.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public enum TestSet implements Iterable<Sudoku> {
    PROJECT_EULER_9x9("/txt/9x9.txt") {
        @Override
        public ISudokuVariant getSudokuVariant() {
            return new ClassicSquare9x9();
        }
    },
    TRIVIAL_9x9("/txt/9x9_trivial.txt") {
        @Override
        public ISudokuVariant getSudokuVariant() {
            return new ClassicSquare9x9();
        }
    },
    DEDUCTION_EXTREME("/txt/9x9_deductionExtreme.txt") {
        @Override
        public ISudokuVariant getSudokuVariant() {
            return new ClassicSquare9x9();
        }
    },
    BRUTE_FORCE_EXTREME("/txt/9x9_bruteForceExtreme.txt") {
        @Override
        public ISudokuVariant getSudokuVariant() {
            return new ClassicSquare9x9();
        }
    },
    EXAMPLE_DIAGONAL("/txt/9x9_diagonal.txt") {
        @Override
        public ISudokuVariant getSudokuVariant() {
            return new DiagonalSquare9x9();
        }
    },
    EXAMPLE_16x16("/txt/16x16.txt") {
        @Override
        public ISudokuVariant getSudokuVariant() {
            return new ClassicSquare16x16();
        }
    },
    EXAMPLE_25x25("/txt/25x25.txt") {
        @Override
        public ISudokuVariant getSudokuVariant() {
            return new ClassicSquare25x25();
        }
    },
    SMALL_SAMURAI("/txt/15x15_samuraiDiagonal.txt") {
        @Override
        public ISudokuVariant getSudokuVariant() {
            ISudokuVariant child = new ClassicSquare9x9();
            return new Slash(15, 15, child);
        }
    },
    BIG_SAMURAI("/txt/21x21_samuraiCross.txt") {
        @Override
        public ISudokuVariant getSudokuVariant() {
            ISudokuVariant child = new ClassicSquare9x9();
            return new Samurai(21, 21, child);
        }
    },
    COLOSSUS_SAMURAI("/txt/30x30_samuraiCross.txt") {
        @Override
        public ISudokuVariant getSudokuVariant() {
            ISudokuVariant child = new ClassicSquare9x9();
            ISudokuVariant subChild = new Butterfly(12, 12, child);
            return new Samurai(30, 30, subChild);
        }
    },
    TEST("/txt/test.txt") {
        public ISudokuVariant getSudokuVariant() {
            return new ClassicSquare9x9();
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

    public abstract ISudokuVariant getSudokuVariant();

    public List<Sudoku> getData() {
        return sudokus;
    }

    @Override
    public Iterator<Sudoku> iterator() {
        return sudokus.iterator();
    }

}
