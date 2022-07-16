package pl.laskowski.marcin.playground;

import pl.laskowski.marcin.solving.bruteforce.BruteForceConcurrentSolver;
import pl.laskowski.marcin.solving.bruteforce.BruteForcePermutationSolver;
import pl.laskowski.marcin.solving.bruteforce.BruteForceSolver;
import pl.laskowski.marcin.solving.deduction.solver.*;
import pl.laskowski.marcin.solving.sat.SatSolver;

/**
 * Created by Marcin Laskowski.
 */

public class TestPlayground {

//    public void play() {
//        TestSet set = TestSet.PROJECT_EULER_9x9;
//        SudokuRater rater = new SudokuRater(set.getSudokuVariant());
//        SudokuGenerator generator = new SudokuGenerator(set.getSudokuVariant(), SudokuRater.Difficulty.EASY, null);
//        for (int i = 0; i < 1000; i++) {
//            Sudoku s = generator.generate();
//            System.out.println(s);
//            System.out.println(rater.rate(s));
//            float percent = rater.percentFilled(s);
//            System.out.println(String.format("%.2f%%", percent * 100));
//        }
//    }
//
//    public void create() {
//        TestSet set = TestSet.TEST;
//        SudokuShuffler shuffler = new SudokuShuffler();
//        for (int i = 0; i < 2000; i++) {
//            Sudoku s = shuffler.shuffle(set.getData().get(0), (ClassicRectangle) set.getSudokuVariant());
//            System.out.println(s);
//        }
//    }

    public void bruteForceComparision() {
        TestSet set = TestSet.PROJECT_EULER_9x9;

        new TimeComparator(set)
                .add("BF", () -> new BruteForceSolver())
                .add("BFP", () -> new BruteForcePermutationSolver(5))
                .add("BFC", () -> new BruteForceConcurrentSolver( 6, 6))
                .run();
    }

    public void bruteForceSingleThreadComparision() {
        TestSet set = TestSet.PROJECT_EULER_9x9;

        new TimeComparator(set)
                .add("BF", () -> new BruteForceSolver())
                .add("BFP", () -> new BruteForcePermutationSolver(1))
                .add("BFC", () -> new BruteForceConcurrentSolver(1, 6))
                .run();
    }

    public void bruteForcePermutationComparision() {
        TestSet set = TestSet.PROJECT_EULER_9x9;

        new TimeComparator(set)
                .add("BFP_1", () -> new BruteForcePermutationSolver(1))
                .add("BFP_2", () -> new BruteForcePermutationSolver(2))
                .add("BFP_3", () -> new BruteForcePermutationSolver(3))
                .add("BFP_4", () -> new BruteForcePermutationSolver(4))
                .add("BFP_5", () -> new BruteForcePermutationSolver(5))
                .add("BFP_6", () -> new BruteForcePermutationSolver(6))
                .add("BFP_7", () -> new BruteForcePermutationSolver(7))
                .add("BFP_8", () -> new BruteForcePermutationSolver(8))
                .add("BFP_9", () -> new BruteForcePermutationSolver(9))
                .add("BFP_10", () -> new BruteForcePermutationSolver( 10))
                .run();
    }

    public void bruteForceConcurrentComparision() {
        TestSet set = TestSet.PROJECT_EULER_9x9;

        new TimeComparator(set)
                .add("BFC_1", () -> new BruteForceConcurrentSolver(1, 5))
                .add("BFC_2", () -> new BruteForceConcurrentSolver(2, 5))
                .add("BFC_3", () -> new BruteForceConcurrentSolver(3, 5))
                .add("BFC_4", () -> new BruteForceConcurrentSolver(4, 5))
                .add("BFC_5", () -> new BruteForceConcurrentSolver(5, 5))
                .add("BFC_6", () -> new BruteForceConcurrentSolver(6, 5))
                .add("BFC_7", () -> new BruteForceConcurrentSolver(7, 5))
                .add("BFC_8", () -> new BruteForceConcurrentSolver( 8, 5))
                .add("BFC_9", () -> new BruteForceConcurrentSolver(9, 5))
                .add("BFC_10", () -> new BruteForceConcurrentSolver(10, 5))
                .run();
    }

    public void bruteForceConcurrentComparision2() {
        TestSet set = TestSet.PROJECT_EULER_9x9;

        new TimeComparator(set)
                .add("BFC_2", () -> new BruteForceConcurrentSolver(6, 2))
                .add("BFC_4", () -> new BruteForceConcurrentSolver( 6, 4))
                .add("BFC_6", () -> new BruteForceConcurrentSolver( 6, 6))
                .add("BFC_8", () -> new BruteForceConcurrentSolver( 6, 8))
                .add("BFC_10", () -> new BruteForceConcurrentSolver( 6, 10))
                .add("BFC_12", () -> new BruteForceConcurrentSolver( 6, 12))
                .add("BFC_14", () -> new BruteForceConcurrentSolver( 6, 14))
                .add("BFC_16", () -> new BruteForceConcurrentSolver( 6, 16))
                .add("BFC_18", () -> new BruteForceConcurrentSolver( 6, 18))
                .add("BFC_20", () -> new BruteForceConcurrentSolver( 6, 20))
                .run();
    }

    public void deductionComparision() {
        TestSet set = TestSet.PROJECT_EULER_9x9;

        new TimeComparator(set)
                .add("DED_V1", () -> new DeductionSolverV1())
                .add("DED_V2", () -> new DeductionSolverV2())
                .add("DED_V3", () -> new DeductionSolverV3())
                .run();
    }

    public void parallelDeductionComparision() {
        TestSet set = TestSet.PROJECT_EULER_9x9;

        new TimeComparator(set)
                .add("DED_V1", () -> new ParallelDeductionSolver2(new DeductionSolverV1()))
                .add("DED_V2", () -> new ParallelDeductionSolver2(new DeductionSolverV2()))
                .add("DED_V3", () -> new ParallelDeductionSolver2(new DeductionSolverV3()))
                .run();
    }

    public void satComparision() {
        TestSet set = TestSet.PROJECT_EULER_9x9;

        new TimeComparator(set)
                .add("SAT", () -> new SatSolver())
                .add("DED_S3", () -> new ParallelDeductionSolver2(new DeductionSolverV3()))
                .add("BFP", () -> new BruteForcePermutationSolver(5))
                .run();
    }

    public void worstCase1() {
        TestSet set = TestSet.BRUTE_FORCE_EXTREME;

        new TimeComparator(set)
                .add("SAT", () -> new SatSolver())
                .add("DED_S3", () -> new ParallelDeductionSolver2(new DeductionSolverV3()))
                .add("BFP", () -> new BruteForcePermutationSolver(5))
                .add("BF", () -> new BruteForceSolver())
                .run();
    }

    public void worstCase2() {
        TestSet set = TestSet.DEDUCTION_EXTREME;

        new TimeComparator(set)
                .add("SAT", () -> new SatSolver())
                .add("DED_S3", () -> new ParallelDeductionSolver2(new DeductionSolverV3()))
                .add("BFP", () -> new BruteForcePermutationSolver(5))
                .add("BF", () -> new BruteForceSolver())
                .run();
    }

}
