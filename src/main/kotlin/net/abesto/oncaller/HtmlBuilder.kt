package net.abesto.oncaller

import java.util.ArrayList
import java.util.HashMap

// based on http://confluence.jetbrains.com/display/Kotlin/Type-safe+Groovy-style+builders#Type-safeGroovy-stylebuilders-declarations

trait Element {
    fun render(builder: StringBuilder, indent: String)

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

class TextElement(val text: String): Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

abstract class Tag(val name: String): Element {
    val children: ArrayList<Element> = ArrayList<Element>()
    val attributes = HashMap<String, String>()

    protected fun initTag<T: Element>(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    protected fun initTag<T: Element>(tag: T, init: (T) -> Unit): T {
        init(tag)
        children.add(tag)
        return tag
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, indent + "  ")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String? {
        val builder = StringBuilder()
        for (a in attributes.keySet()) {
            builder.append(" $a=\"${attributes[a]}\"")
        }
        return builder.toString()
    }
}

abstract class TagWithText(name: String): Tag(name) {
    fun String.plus() {
        children.add(TextElement(this))
    }
}

class Table(): Tag("table") {
    fun tr(init: (Tr) -> Unit) = initTag(Tr(), init)
}

class Tr(): Tag("tr") {
    fun td(init: Td.() -> Unit) = initTag(Td(), init)
    fun th(init: (Th) -> Unit) = initTag(Th(), init)
}

class Td(): TagWithText("td")
class Th(): TagWithText("th")

fun table(init: (Table) -> Unit): Table {
    val table = Table()
    init(table)
    return table
}

