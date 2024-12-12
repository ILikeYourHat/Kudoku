package io.github.ilikeyourhat.kudoku.integration.type

class Classic9x9Test : SudokuTypesTest() {

    override val puzzle = """
        classic_9x9
        _,_,3 ,_,2,_, 6,_,_
        9,_,_ ,3,_,5, _,_,1
        _,_,1 ,8,_,6, 4,_,_
        
        _,_,8, 1,_,2, 9,_,_
        7,_,_, _,_,_, _,_,8
        _,_,6, 7,_,8, 2,_,_
        
        _,_,2, 6,_,9, 5,_,_
        8,_,_, 2,_,3, _,_,9
        _,_,5, _,1,_, 3,_,_
    """.trimIndent()

    override val solution = """
        classic_9x9
        4,8,3, 9,2,1, 6,5,7
        9,6,7, 3,4,5, 8,2,1
        2,5,1, 8,7,6, 4,9,3
        
        5,4,8, 1,3,2, 9,7,6
        7,2,9, 5,6,4, 1,3,8
        1,3,6, 7,9,8, 2,4,5
        
        3,7,2, 6,8,9, 5,1,4
        8,1,4, 2,5,3, 7,6,9
        6,9,5, 4,1,7, 3,8,2
    """.trimIndent()
}
