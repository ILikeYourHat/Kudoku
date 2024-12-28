package io.github.ilikeyourhat.kudoku.parsing

enum class EmptyFieldIndicator(val value: Char) {
    ZERO('0'),
    DOT('.'),
    X('X'),
    ASTERISK('*'),
    UNDERSCORE('_'),
    SPACE(' ')
}
