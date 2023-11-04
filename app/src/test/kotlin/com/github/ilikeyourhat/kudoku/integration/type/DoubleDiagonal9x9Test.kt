package com.github.ilikeyourhat.kudoku.integration.type

class DoubleDiagonal9x9Test: SudokuTypesTest() {

    override val puzzle = """
            double_diagonal_9x9
            9,_,_, 6,_,3, _,7,_
            8,_,_, _,5,_, _,3,_
            _,_,_, 4,_,_, _,_,_
            
            7,_,_, 3,_,_, _,_,5
            3,_,_, _,_,4, _,_,_
            _,_,6, 9,_,_, _,_,_
            
            _,_,_, _,_,_, _,_,_
            _,7,_, 1,_,_, _,_,3
            _,6,_, _,_,8, 4,_,2
        """.trimIndent()

    override val solution = """
            double_diagonal_9x9
            9,5,2, 6,1,3, 8,7,4
            8,4,7, 2,5,9, 6,3,1
            6,3,1, 4,8,7, 2,5,9
            
            7,8,4, 3,2,1, 9,6,5
            3,9,5, 8,6,4, 1,2,7
            1,2,6, 9,7,5, 3,4,8
            
            4,1,8, 5,3,2, 7,9,6
            2,7,9, 1,4,6, 5,8,3
            5,6,3, 7,9,8, 4,1,2
        """.trimIndent()
}
