package com.github.ilikeyourhat.sudokusolver.solving.bruteforce

import com.github.ilikeyourhat.sudokusolver.model.Sudoku

class UnexpectedSuccessException(val solution: Sudoku) : RuntimeException()