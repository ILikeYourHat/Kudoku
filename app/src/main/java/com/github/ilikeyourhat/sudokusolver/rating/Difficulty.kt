package com.github.ilikeyourhat.sudokusolver.rating

enum class Difficulty {
    EASY, MEDIUM, HARD, VERY_HARD;

    fun isHarderThan(difficulty: Difficulty): Boolean {
        return ordinal > difficulty.ordinal
    }

    fun isEasierThan(difficulty: Difficulty): Boolean {
        return ordinal < difficulty.ordinal
    }
}