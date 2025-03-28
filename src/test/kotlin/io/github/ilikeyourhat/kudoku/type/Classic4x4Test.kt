package io.github.ilikeyourhat.kudoku.type

class Classic4x4Test : SudokuTypeTestTemplate() {

    override val puzzle = """
        classic_4x4
        _,_, 1,_
        3,_, _,_
        
        _,4, _,_
        1,_, 4,_
    """.trimIndent()

    override val solution = """
        classic_4x4
        4,2, 1,3
        3,1, 2,4
        
        2,4, 3,1
        1,3, 4,2
    """.trimIndent()
}
