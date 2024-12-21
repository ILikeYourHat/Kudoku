package io.github.ilikeyourhat.kudoku.type

class Square2x2Test : SudokuTypeTestTemplate() {

    override val puzzle = """
        square_2x2
        2,1
        _,_
    """.trimIndent()

    override val solution = """
        square_2x2
        2,1
        1,2
    """.trimIndent()
}
