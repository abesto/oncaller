package net.abesto.oncaller.extensions

native fun <T> Array<T>.splice(start: Int, deleteCount: Int, vararg items: T): Array<T>
