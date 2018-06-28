package pl.laskowski.marcin.parsing.text;

import pl.laskowski.marcin.model.Sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Marcin Laskowski.
 */

public class SudokuTextFormatParser {

    private static final Pattern EMPTY_VALUE = Pattern.compile("^_*$");
    private static final Pattern NO_FIELD = Pattern.compile("^#*$");

    public Sudoku parseOne(String text) {
        return new Command(text).parseOne();
    }

    public List<Sudoku> parseMany(String text) {
        return new Command(text).parseMany();
    }

    public Sudoku parseOne(File file) throws FileNotFoundException {
        return new Command(file).parseOne();
    }

    public List<Sudoku> parseMany(File file) throws FileNotFoundException {
        return new Command(file).parseMany();
    }

    private class Command {

        private final Scanner scanner;
        private Scanner lineScanner;

        private int width;
        private int height;
        private Integer[] data;
        private int pointer;

        private Command(String text) {
            this.scanner = new Scanner(text);
        }

        private Command(File file) throws FileNotFoundException {
            this.scanner = new Scanner(file);
        }

        private List<Sudoku> parseMany() {
            List<Sudoku> sudokuList = new ArrayList<>();
            while (scanner.hasNext()) {
                Sudoku sudoku = parseOne();
                sudokuList.add(sudoku);
            }
            return sudokuList;
        }

        private Sudoku parseOne() {
            readSize();
            for (int y = 0; y < height; y++) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    y--;
                    continue;
                }
                lineScanner = new Scanner(line).useDelimiter(",");
                for (int x = 0; x < width; x++, pointer++) {
                    Integer value = parse(lineScanner.next().trim());
                    data[pointer] = value;
                }
            }
            return new Sudoku(width, height, data);
        }

        private void readSize() {
            width = scanner.nextInt();
            height = scanner.nextInt();
            data = new Integer[width * height];
            pointer = 0;
            scanner.nextLine();
        }

        private Integer parse(String input) {
            if (isEmptyValue(input)) {
                return 0;
            } else if (isNoField(input)) {
                return null;
            } else {
                return asNumber(input);
            }
        }

        private boolean isEmptyValue(String input) {
            return EMPTY_VALUE.matcher(input).matches();
        }

        private boolean isNoField(String input) {
            return NO_FIELD.matcher(input).matches();
        }

        private int asNumber(String input) {
            return Integer.parseInt(input, 10);
        }

    }

}
