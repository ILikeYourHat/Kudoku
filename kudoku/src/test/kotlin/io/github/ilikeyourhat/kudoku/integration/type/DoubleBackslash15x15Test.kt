package io.github.ilikeyourhat.kudoku.integration.type

class DoubleBackslash15x15Test : SudokuTypesTest() {

    override val puzzle = """
        double_backslash_15x15
        _,_,_, 7,_,_, _,5,_, #,#,#, #,#,#
        _,9,8, _,_,_, _,6,_, #,#,#, #,#,#
        3,_,_, _,_,_, _,_,_, #,#,#, #,#,#
        
        _,_,_, _,5,4, _,_,_, #,#,#, #,#,#
        8,5,6, 9,_,3, _,_,7, #,#,#, #,#,#
        _,2,_, _,_,8, _,_,_, #,#,#, #,#,#
        
        _,8,7, _,_,_, _,2,_, _,_,_, _,_,_
        9,_,_, _,8,_, _,_,_, _,_,_, _,_,_
        _,_,2, _,_,_, 7,3,_, _,4,_, 2,_,_
        
        #,#,#, #,#,#, _,_,_, _,_,_, _,_,9
        #,#,#, #,#,#, 8,_,9, _,_,5, _,1,_
        #,#,#, #,#,#, _,_,_, 4,_,9, _,2,_
        
        #,#,#, #,#,#, _,_,_, _,_,_, _,_,5
        #,#,#, #,#,#, 5,_,_, 9,_,7, _,_,_
        #,#,#, #,#,#, _,8,4, _,_,3, _,_,6
    """.trimIndent()

    override val solution = """
        double_backslash_15x15
        2,1,4, 7,6,9, 8,5,3, #,#,#, #,#,#
        7,9,8, 3,1,5, 2,6,4, #,#,#, #,#,#
        3,6,5, 8,4,2, 1,7,9, #,#,#, #,#,#
        
        1,7,9, 6,5,4, 3,8,2, #,#,#, #,#,#
        8,5,6, 9,2,3, 4,1,7, #,#,#, #,#,#
        4,2,3, 1,7,8, 5,9,6, #,#,#, #,#,#
        
        5,8,7, 4,3,6, 9,2,1, 7,3,8, 6,5,4
        9,3,1, 2,8,7, 6,4,5, 1,9,2, 3,8,7
        6,4,2, 5,9,1, 7,3,8, 5,4,6, 2,9,1
        
        #,#,#, #,#,#, 4,5,2, 3,8,1, 7,6,9
        #,#,#, #,#,#, 8,7,9, 6,2,5, 4,1,3
        #,#,#, #,#,#, 3,1,6, 4,7,9, 5,2,8
        
        #,#,#, #,#,#, 2,9,7, 8,6,4, 1,3,5
        #,#,#, #,#,#, 5,6,3, 9,1,7, 8,4,2
        #,#,#, #,#,#, 1,8,4, 2,5,3, 9,7,6
    """.trimIndent()
}
