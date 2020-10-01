package com.example.progressfeaturesample.utils

fun <T> MutableCollection<T>.removeElem(condition: (T) -> Boolean) {
    find { condition(it) }?.let {
        remove(it)
    }
}

fun <T> MutableList<T>.replaceWith(condition: (T) -> Boolean, newElem: T) {
    val index = this.indexOfFirst { condition(it) }
    if (index != -1) {
        remove(elementAt(index))
        add(index, newElem)
    }
}

fun <T> MutableList<T>.addAfter(condition: (T) -> Boolean, newElem: T) {
    val index = this.indexOfFirst { condition(it) }
    if (index != -1) {
        if (index != lastIndex) {
            add(index + 1, newElem)
        } else {
            add(newElem)
        }
    }
}