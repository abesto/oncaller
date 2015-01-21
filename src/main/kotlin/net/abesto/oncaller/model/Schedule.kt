package net.abesto.oncaller.model

import java.util.HashMap

val debug = false

class Schedule(
    val developers: Set<Developer>,
    val components: Set<Component>,
    weekCount: Int
) {
    protected val assignments: HashMap<Component, HashMap<Int, Developer>> = HashMap()
    public val weeks: Array<Week> = Array(weekCount, { n -> Week(n + 1) })

    protected fun validateComponent(component: Component) {
        if (!debug) return
        if (!components.contains(component)) {
            throw Exception("Schedule doesn't know about specified component ${component.name}")
        }
    }

    protected fun validateWeek(week: Int) {
        if (!debug) return
        if (week < 1 || week > weeks.size()) {
            throw Exception ("Week ${week} is out of range 1 - ${weeks.size()}")
        }
    }

    protected fun validateDeveloper(developer: Developer?) {
        if (!debug) return
        if (developer != null && !developers.contains(developer)) {
            throw Exception("Schedule doesn't know about specified developer ${developer.name}")
        }
    }

    public fun get(component: Component, week: Int): Developer? {
        validateComponent(component)
        validateWeek(week)
        return assignments.get(component)?.get(week)
    }

    public fun get(component: Component, week: Week): Developer? = get(component, week.number)

    public fun set(component: Component, week: Int, developer: Developer?) {
        validateComponent(component)
        validateWeek(week)
        validateDeveloper(developer)
        val byComponent = assignments.getOrPut(component, { HashMap() })
        if (developer == null) {
            byComponent.remove(week)
        } else {
            byComponent.set(week, developer)
        }
    }

    public fun set(component: Component, week: Week, developer: Developer?): Unit = set(component, week.number, developer)
}