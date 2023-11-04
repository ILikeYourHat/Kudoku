package com.github.ilikeyourhat.kudoku.solving.deduction.combinations

class CollectionCombinator(private val combinationSize: Int) {

    fun <T> iterate(collection: Collection<T>, consumer: CombinationConsumer<T>) {
        if (collection.size < combinationSize) return
        val iterator = CombinationIterator(combinationSize, collection.size)
        while (iterator.hasNext()) {
            val values: MutableList<T> = ArrayList()
            for (index in iterator.next()) {
                val value = collection.toList()[index]
                values.add(value)
            }
            consumer.onNextCombination(values)
        }
    }

    fun interface CombinationConsumer<T> {
        fun onNextCombination(values: List<T>)
    }
}
