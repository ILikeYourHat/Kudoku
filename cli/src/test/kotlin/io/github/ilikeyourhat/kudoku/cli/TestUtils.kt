package io.github.ilikeyourhat.kudoku.cli

import com.github.ajalt.clikt.testing.CliktCommandTestResult
import com.github.ajalt.clikt.testing.test
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.string.shouldMatch

fun runCommand(argv: String): CliktCommandTestResult {
    return rootCommand.test(argv)
}

fun CliktCommandTestResult.shouldSucceedWith(message: String) {
    statusCode.shouldBeEqual(0)
    output.trim().shouldBeEqual(message)
}

fun CliktCommandTestResult.shouldSucceedWith(regex: Regex) {
    statusCode.shouldBeEqual(0)
    output.trim().shouldMatch(regex)
}

fun CliktCommandTestResult.shouldFailWith(message: String) {
    statusCode.shouldNotBeEqual(0)
    output.trim().lines()
        .shouldContain(message)
}

const val UNSOLVED_CLASSIC = "000000907000420180000705026100904000050000040000507009920108000034059000507000000"
const val SOLVED_CLASSIC = "462831957795426183381795426173984265659312748248567319926178534834259671517643892"

const val UNSOLVED_DD = "080010400900000030006700000070006000004500000000000040200070800000000307000091000"
const val SOLVED_DD = "387912465951684732426753189573146928814529673692837541239475816145268397768391254"

const val UNSOLVED_CLASSIC_4X4 = "0010300004001040"
const val SOLVED_CLASSIC_4X4 = "4213312424311342"

val classic4x4Regex = """^([0-4]){16}$""".toRegex()
val classic9x9Regex = """^([0-9]){81}$""".toRegex()
