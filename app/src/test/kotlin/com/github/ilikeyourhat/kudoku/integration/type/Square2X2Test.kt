package com.github.ilikeyourhat.kudoku.integration.type

class Square2X2Test: SudokuTypesTest() {

    override val puzzle = """
            classic_2x2
            2,1
            _,_
        """.trimIndent()

    override val solution = """
            classic_2x2
            2,1
            1,2
        """.trimIndent()
}