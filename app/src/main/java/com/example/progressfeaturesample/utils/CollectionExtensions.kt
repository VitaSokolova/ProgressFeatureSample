package com.example.progressfeaturesample.utils

fun <T> MutableCollection<T>.removeElem(predicate: (T) -> Boolean) {
    find { predicate(it) }?.let {
        remove(it)
    }
}

fun <T> MutableList<T>.replaceWith(predicate: (T) -> Boolean, newElem: T) {
    val index = this.indexOfFirst { predicate(it) }
    if (index != -1) {
        remove(elementAt(index))
        add(index, newElem)
    }
}

fun <T> MutableList<T>.addAfter(predicate: (T) -> Boolean, newElem: T) {
    val index = this.indexOfFirst { predicate(it) }
    if (index != -1) {
        if (index != lastIndex) {
            add(index + 1, newElem)
        } else {
            add(newElem)
        }
    }
}