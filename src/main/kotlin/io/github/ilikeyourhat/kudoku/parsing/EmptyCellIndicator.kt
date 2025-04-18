package io.github.ilikeyourhat.kudoku.parsing

enum class EmptyCellIndicator(val value: Char) {
    ZERO('0'),
    DOT('.'),
    X('X'),
    ASTERISK('*'),
    UNDERSCORE('_'),
    SPACE(' ')
}
