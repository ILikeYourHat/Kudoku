package pl.laskowski.marcin.solving.bruteforce

import pl.laskowski.marcin.model.Sudoku

class UnexpectedSuccessException(val solution: Sudoku) : RuntimeException()