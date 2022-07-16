package pl.laskowski.marcin.playground;

import pl.laskowski.marcin.creating.ClassicSudokuGenerator;
import pl.laskowski.marcin.creating.SudokuGenerator;
import pl.laskowski.marcin.creating.SudokuRater;
import pl.laskowski.marcin.playground.gen.SudokuGenerationTimer;
import pl.laskowski.marcin.type.ClassicSquare;
import pl.laskowski.marcin.type.DiagonalSquare;
import pl.laskowski.marcin.type.SudokuVariant;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String... args) throws InvocationTargetException, IllegalAccessException {
//        TestPlayground playground = new TestPlayground();
//        Class curClass = TestPlayground.class;
//        Method[] allMethods = curClass.getMethods();
//        for(Method method : allMethods) {
//            if (method.getParameterCount() == 0) {
//                System.out.println(method.getName());
//                method.invoke(playground);
//            }
//        }
        test();
    }

    private static void test() {
        testClassic();
        testClassicOptimized();
        testDiagonal();
        testClassic16();
        testClassic25();
        testSamurai15();
        testSamurai30();
    }

    private static void testClassic() {
        SudokuRater rater = new SudokuRater();

        System.out.println("CLASSIC");
        SudokuGenerator generator1 = new SudokuGenerator(new ClassicSquare(9), null, null);
        new SudokuGenerationTimer(rater, generator1).measure(100);

        System.out.println("CLASSIC_EASY");
        SudokuGenerator generator2 = new SudokuGenerator(new ClassicSquare(9), SudokuRater.Difficulty.EASY, null);
        new SudokuGenerationTimer(rater, generator2).measure(100);

        System.out.println("CLASSIC_MEDIUM");
        SudokuGenerator generator3 = new SudokuGenerator(new ClassicSquare(9), SudokuRater.Difficulty.MEDIUM, null);
        new SudokuGenerationTimer(rater, generator3).measure(100);

        System.out.println("CLASSIC_HARD");
        SudokuGenerator generator4 = new SudokuGenerator(new ClassicSquare(9), SudokuRater.Difficulty.HARD, null);
        new SudokuGenerationTimer(rater, generator4).measure(100);

        System.out.println("CLASSIC_35");
        SudokuGenerator generator5 = new SudokuGenerator(new ClassicSquare(9), null, 0.35f);
        new SudokuGenerationTimer(rater, generator5).measure(100);

        System.out.println("CLASSIC_50");
        SudokuGenerator generator6 = new SudokuGenerator(new ClassicSquare(9), null, 0.5f);
        new SudokuGenerationTimer(rater, generator6).measure(100);

        System.out.println("CLASSIC_75");
        SudokuGenerator generator7 = new SudokuGenerator(new ClassicSquare(9), null, 0.75f);
        new SudokuGenerationTimer(rater, generator7).measure(100);
    }

    private static void testClassicOptimized() {
        SudokuRater rater = new SudokuRater();

        System.out.println("CLASSIC_OPTIMIZED");
        SudokuGenerator generator1 = new ClassicSudokuGenerator(null, null);
        new SudokuGenerationTimer(rater, generator1).measure(100);

        System.out.println("CLASSIC_OPTIMIZED_EASY");
        SudokuGenerator generator2 = new ClassicSudokuGenerator(SudokuRater.Difficulty.EASY, null);
        new SudokuGenerationTimer(rater, generator2).measure(100);

        System.out.println("CLASSIC_OPTIMIZED_MEDIUM");
        SudokuGenerator generator3 = new ClassicSudokuGenerator(SudokuRater.Difficulty.MEDIUM, null);
        new SudokuGenerationTimer(rater, generator3).measure(100);

        System.out.println("CLASSIC_OPTIMIZED_HARD");
        SudokuGenerator generator4 = new ClassicSudokuGenerator(SudokuRater.Difficulty.HARD, null);
        new SudokuGenerationTimer(rater, generator4).measure(100);

        System.out.println("CLASSIC_OPTIMIZED_35");
        SudokuGenerator generator5 = new ClassicSudokuGenerator(null, 0.35f);
        new SudokuGenerationTimer(rater, generator5).measure(100);

        System.out.println("CLASSIC_OPTIMIZED_50");
        SudokuGenerator generator6 = new ClassicSudokuGenerator(null, 0.5f);
        new SudokuGenerationTimer(rater, generator6).measure(100);

        System.out.println("CLASSIC_OPTIMIZED_75");
        SudokuGenerator generator7 = new ClassicSudokuGenerator(null, 0.75f);
        new SudokuGenerationTimer(rater, generator7).measure(100);
    }

    public static void testDiagonal() {
        System.out.println("CLASSIC_DIAGONAL");
        SudokuRater rater = new SudokuRater();
        SudokuGenerator generator = new SudokuGenerator(new DiagonalSquare(9), null, null);
        new SudokuGenerationTimer(rater, generator).measure(10);
    }

    public static void testClassic16() {
        System.out.println("CLASSIC_16x16");
        SudokuRater rater = new SudokuRater();
        SudokuGenerator generator = new SudokuGenerator(new ClassicSquare(16), null, null);
        new SudokuGenerationTimer(rater, generator).measure(10);
    }

    public static void testClassic25() {
        System.out.println("CLASSIC_25x25");
        SudokuRater rater = new SudokuRater();
        SudokuGenerator generator = new SudokuGenerator(new ClassicSquare(25), null, null);
        new SudokuGenerationTimer(rater, generator).measure(10);
    }

    public static void testSamurai15() {
        System.out.println("SAMURAI_15");
        SudokuVariant type = TestSet.SMALL_SAMURAI.getSudokuVariant();
        SudokuRater rater = new SudokuRater();
        SudokuGenerator generator = new SudokuGenerator(type, null, null);
        new SudokuGenerationTimer(rater, generator).measure(10);
    }

    public static void testSamurai30() {
        System.out.println("SAMURAI_30");
        SudokuVariant type = TestSet.COLOSSUS_SAMURAI.getSudokuVariant();
        SudokuRater rater = new SudokuRater();
        SudokuGenerator generator = new SudokuGenerator(type, null, null);
        new SudokuGenerationTimer(rater, generator).measure(10);
    }

}
