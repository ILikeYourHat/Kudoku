# Kudoku

[![Build](https://github.com/ilikeyourhat/kudoku/actions/workflows/build.yml/badge.svg)](https://github.com/ILikeYourHat/Kudoku/actions/workflows/build.yml)
[![Codecov](https://codecov.io/gh/ILikeYourHat/Kudoku/graph/badge.svg?token=WGDOE6PGCW)](https://codecov.io/gh/ILikeYourHat/Kudoku)
[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.ilikeyourhat.kudoku/kudoku)](https://central.sonatype.com/artifact/io.github.ilikeyourhat.kudoku/kudoku)
[![Renovate enabled](https://img.shields.io/badge/renovate-enabled-brightgreen.svg)](https://renovatebot.com/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/ilikeyourhat/kudoku/blob/master/LICENSE.md)

A powerful sudoku engine written in Kotlin.

## Usage

Add Kudoku to your project:

```kotlin
dependencies {
    implementation("io.github.ilikeyourhat.kudoku:kudoku:{version}")
}
```

Create a sudoku board providing the type and values:

```kotlin
val sudoku = Sudoku(
    Classic9x9,
    listOf(
        0, 5, 7, 7, 6, 0, 0, 0, 4,
        8, 0, 4, 0, 9, 5, 2, 0, 0,
        0, 0, 3, 0, 0, 7, 5, 8, 1,
        0, 0, 0, 7, 4, 2, 0, 1, 9,
        7, 6, 1, 0, 0, 0, 8, 4, 0,
        0, 2, 9, 1, 8, 0, 0, 7, 0,
        1, 4, 0, 9, 0, 3, 7, 0, 0,
        5, 0, 0, 6, 0, 0, 4, 9, 3,
        9, 3, 0, 0, 7, 4, 0, 0, 8
    )
)
```

...or using a string notation for improved readability:

```kotlin
val sudoku = Sudoku.createFromString(
    """
    classic_9x9
    _,5,7, 8,6,_, _,_,4
    8,_,4, _,9,5, 2,_,_
    _,_,3, _,_,7, 5,8,1

    _,_,_, 7,4,2, _,1,9
    7,6,1, _,_,_, 8,4,_
    _,2,9, 1,8,_, _,7,_

    1,4,_, 9,_,3, 7,_,_
    5,_,_, 6,_,_, 4,9,3
    9,3,_, _,7,4, _,_,8
    """.trimIndent()
)
```

Create a solver instance and solve the board:

```kotlin
val solver = Sudoku.defaultSolver()
val solution = solver.solve(sudoku)
println(solution)
```

Choose from multiple solver implementations:

```kotlin
val solver1 = SatSolver()
val solver2 = BruteForceSolver()
val solver3 = DeductionSolverV3()
```

Support for popular text formats:

```kotlin
val string = "003020600900305001001806400008102900700000008006708200002609500800203009005010300"
val sudoku = Sudoku.fromSingleLineString(string)
val encoded = sudoku.toSingleLineString(emptyCellIndicator = EmptyCellIndicator.DOT)
```

Generate a random Sudoku with a given difficulty:

```kotlin
val generator = Sudoku.defaultGenerator()
val sudoku = generator.generate(SudokuType.Classic9x9, Difficulty.VERY_HARD)
println(sudoku)
```

Check how hard is a given sudoku:

```kotlin
val rater = Sudoku.defaultRater()
val difficulty = rater.rate(sudoku) // can be EASY, MEDIUM, HARD, VERY_HARD or UNSOLVABLE
```

Check if a given sudoku has one solution:

```kotlin
val solutionChecker = Sudoku.defaultSolutionChecker()
val solutionCount = solutionChecker.checkSolutions(sudoku) // can be ZERO, ONE or MANY
```

Kudoku can handle many different types of sudokus, in a reasonable time:

```kotlin
val samurai21x21 = Sudoku.createFromString(
    """
    samurai_classic_21x21
    2,_,_, _,_,8, 9,_,_, #,#,#, 9,_,_, _,3,1, _,7,8
    _,_,7, _,9,_, _,_,6, #,#,#, _,3,_, _,_,5, _,_,_
    3,_,_, 5,_,_, 2,_,_, #,#,#, 4,_,_, _,_,_, _,_,3
    
    _,_,_, 8,_,_, 6,_,_, #,#,#, _,_,_, _,_,2, _,_,9
    5,_,9, 1,_,_, _,_,_, #,#,#, _,7,6, _,_,_, 8,_,_
    1,_,_, _,_,6, _,5,_, #,#,#, _,_,_, _,_,_, _,_,_
    
    _,4,5, _,_,_, 3,_,_, _,_,_, _,_,7, _,_,3, _,1,_
    _,1,_, _,_,_, 4,_,_, _,2,3, _,_,5, 4,_,_, _,3,_
    6,_,_, _,_,_, _,_,_, _,7,_, _,_,_, _,_,6, 5,_,_
    
    #,#,#, #,#,#, _,8,_, _,_,_, _,_,2, #,#,#, #,#,#
    #,#,#, #,#,#, _,_,_, _,_,1, _,_,_, #,#,#, #,#,#
    #,#,#, #,#,#, _,_,_, _,_,4, _,7,_, #,#,#, #,#,#
    
    _,2,_, _,_,_, _,_,_, _,_,_, _,_,1, 7,_,_, _,_,_
    _,_,_, _,_,_, _,_,_, _,_,_, _,_,_, _,2,9, _,5,_
    9,5,_, 7,_,_, _,_,_, _,5,_, _,8,_, _,_,3, _,_,_
    
    _,_,_, _,_,1, _,_,_, #,#,#, _,_,_, 9,_,_, _,_,_
    3,4,_, _,7,_, _,_,_, #,#,#, _,_,_, _,_,_, 4,6,_
    6,_,_, _,3,_, 7,_,_, #,#,#, _,9,_, _,1,_, _,8,_
    
    _,_,_, 2,5,8, _,_,4, #,#,#, 4,2,3, _,_,_, _,_,_
    _,_,3, 6,_,_, _,5,_, #,#,#, _,6,_, _,5,_, 2,_,_
    4,_,_, _,_,_, 9,_,_, #,#,#, _,_,_, 3,_,_, 9,_,7
    """.trimIndent()
)

val classic25x25 = Sudoku.createFromString(
    """
    classic_25x25
    __,__,12,06,__, __,07,__,18,__, 05,24,__,10,01, __,__,04,__,__, __,__,__,__,__ 
    02,__,19,__,13, __,__,__,10,__, __,__,__,__,__, __,__,18,05,__, __,__,__,__,01
    __,__,__,__,__, __,__,22,__,__, __,__,03,__,02, __,__,14,12,__, 16,08,25,__,__
    __,16,__,__,__, 02,23,__,__,13, 12,22,__,__,__, 21,15,19,03,__, __,__,__,14,__
    23,__,24,__,__, __,__,__,25,08, 04,__,16,19,21, __,__,07,__,__, __,03,12,__,09
    
    __,04,__,02,__, __,__,__,__,__, __,10,__,24,12, 17,16,__,__,__, 05,__,__,__,__
    __,__,09,__,__, 06,25,__,__,__, 08,__,05,03,__, __,__,__,__,__, 20,__,__,18,19
    15,__,10,11,__, __,__,18,12,19, __,__,__,__,__, __,__,23,__,__, 07,__,__,04,__
    __,__,__,__,__, __,__,14,__,22, __,__,18,16,20, __,06,11,13,__, __,__,__,__,__
    __,22,__,25,__, __,01,17,05,04, 07,__,__,14,__, 08,03,21,__,__, 11,__,__,__,06
    
    __,20,13,15,__, __,__,__,__,__, 09,__,__,02,__, 25,__,01,08,__, __,05,__,21,__
    __,01,__,__,__, __,16,10,__,07, __,__,04,20,__, __,09,__,__,14, __,24,__,17,__
    25,02,05,__,__, __,__,__,13,__, __,__,__,__,22, __,__,__,__,__, 19,01,08,__,__
    __,__,07,21,__, __,12,__,02,17, __,__,__,18,06, 16,__,__,15,__, __,13,__,10,__
    08,10,18,12,16, 09,__,__,__,05, __,__,__,__,19, __,__,17,__,21, __,15,__,__,22
    
    __,08,__,__,15, __,03,__,06,__, 21,__,__,07,__, 18,14,05,__,01, __,__,__,__,__
    __,__,__,19,__, 01,__,16,11,__, __,__,10,22,25, 15,__,__,__,__, __,__,21,__,__
    __,03,01,__,21, __,__,04,__,__, __,__,02,__,13, __,24,25,__,__, 14,__,__,06,__
    __,__,__,__,__, __,__,15,__,12, 14,__,06,17,24, __,__,__,__,__, __,__,13,__,__
    __,05,23,16,04, __,13,24,07,02, __,09,__,__,15, 03,__,22,__,__, __,__,__,__,08
    
    __,__,25,20,02, __,19,__,__,__, __,01,__,__,__, __,21,03,__,__, 12,__,__,__,__
    16,12,__,05,__, 11,21,__,23,__, __,15,__,__,__, __,19,09,__,__, __,__,__,25,10
    __,__,__,__,09, 20,22,07,04,__, 03,__,14,25,18, __,11,__,__,__, __,__,01,__,15
    24,__,06,__,22, 08,__,25,14,__, 10,11,__,09,__, 20,01,16,__,07, __,23,__,__,13
    14,13,21,01,__, __,05,__,__,__, 06,__,22,__,23, 10,__,__,__,02, __,__,18,07,11
    """.trimIndent()
)
```

Create own sudoku types by extending `SudokuType` class:

```kotlin
object MyCustomType : SudokuType() {
    override val name = "my_custom_type"
    override val sizeX = 4
    override val sizeY = 4
    override val maxValue = 4
    override val dividers = listOf(
        RowsDivider(),
        ColumnsDivider()
    )
}

TypesRegistry.register(MyCustomType)
```
