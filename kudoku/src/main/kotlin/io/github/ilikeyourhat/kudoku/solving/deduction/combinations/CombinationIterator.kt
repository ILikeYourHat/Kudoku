@file:Suppress("UnsafeCallOnNullableType") // This should be rewritten anyway

package io.github.ilikeyourhat.kudoku.solving.deduction.combinations

internal class CombinationIterator(sampleSize: Int, collectionSize: Int) : MutableIterator<IntArray> {
    private val collectionSize: Int
    private val current: IntArray = IntArray(sampleSize)
    private var next: IntArray?

    init {
        next = IntArray(sampleSize)
        this.collectionSize = collectionSize
        for (i in next!!.indices) {
            next!![i] = i
        }
    }

    override fun hasNext(): Boolean {
        return next != null
    }

    override fun next(): IntArray {
        val next = next ?: throw NoSuchElementException()
        System.arraycopy(next, 0, current, 0, next.size)
        incrementIndexes()
        return current
    }

    override fun remove() {
        TODO("Not yet implemented")
    }

    private fun incrementIndexes() {
        next!![next!!.size - 1]++
        var wrongIndex = findFirstTooBigIndex()
        while (wrongIndex > 0) {
            fixIndex(wrongIndex)
            wrongIndex = findFirstTooBigIndex()
        }
        if (wrongIndex == 0) next = null
    }

    private fun fixIndex(index: Int) {
        next!![index - 1]++
        for (i in index until next!!.size) {
            val previousValue = next!![i - 1]
            next!![i] = previousValue + 1
        }
    }

    private fun findFirstTooBigIndex(): Int {
        for (i in next!!.indices) {
            if (next!![i] > biggestIndexFor(next!!, i)) return i
        }
        return -1
    }

    private fun biggestIndexFor(array: IntArray, position: Int): Int {
        return collectionSize - array.size + position
    }
}
