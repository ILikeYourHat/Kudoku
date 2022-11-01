package com.github.ilikeyourhat.sudokusolver.model.type

import com.github.ilikeyourhat.sudokusolver.model.Board

object BoardCreator {

    fun createBoard(type: SudokuType): Board {
        val rows = type.template().lines()
            .filter { it.isNotBlank() }
        val values = rows.map { it.trim().split(" ", ",") }
        require(values.distinctBy { it.size }.count() == 1)
        val columnCount = values[0].size

        val fieldMap = values.flatten().map { symbol ->
            when (symbol) {
                "_" -> 0
                "#" -> null
                else -> throw IllegalArgumentException()
            }
        }

        return Board(columnCount, rows.size, fieldMap)
    }
}