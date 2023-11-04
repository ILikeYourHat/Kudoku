package com.github.ilikeyourhat.kudoku.rating

enum class Difficulty {
    EASY, MEDIUM, HARD, VERY_HARD, INVALID;

    fun isHarderThan(difficulty: Difficulty): Boolean {
        return ordinal > difficulty.ordinal
    }

    fun isEasierThan(difficulty: Difficulty): Boolean {
        return ordinal < difficulty.ordinal
    }
}
