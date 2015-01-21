package net.abesto.oncaller.extensions

fun randInt(maxEx: Int): Int = Math.floor(Math.random() * maxEx)
fun <T> Iterable<T>.choose(): T = elementAt(randInt(collectionSizeOrDefault(0)))
fun <T> Array<T>.choose(): T = get(randInt(size()))

