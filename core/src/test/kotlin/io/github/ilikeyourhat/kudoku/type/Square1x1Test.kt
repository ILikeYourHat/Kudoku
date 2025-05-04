package io.github.ilikeyourhat.kudoku.type

class Square1x1Test : SudokuTypeTestTemplate() {

    override val puzzle = """
        square_1x1
        _
    """.trimIndent()

    override val solution = """
        square_1x1
        1
    """.trimIndent()
}
