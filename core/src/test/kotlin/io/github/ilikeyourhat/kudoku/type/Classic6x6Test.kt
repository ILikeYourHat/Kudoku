package io.github.ilikeyourhat.kudoku.type

class Classic6x6Test : SudokuTypeTestTemplate() {

    override val puzzle = """
        classic_6x6
        _,5,_, 2,_,_
        _,2,_, _,4,_
        
        _,_,1, _,_,_
        4,_,_, _,6,_
        
        _,_,6, _,_,_
        2,_,_, _,_,1
    """.trimIndent()

    override val solution = """
        classic_6x6
        6,5,4, 2,1,3
        1,2,3, 5,4,6
        
        5,6,1, 3,2,4
        4,3,2, 1,6,5
        
        3,1,6, 4,5,2
        2,4,5, 6,3,1
    """.trimIndent()
}
