package net.abesto.oncaller.spawners

import net.abesto.oncaller.model.Developer
import net.abesto.oncaller.model.Component
import net.abesto.oncaller.model.Schedule

import net.abesto.oncaller.extensions.choose

class RandomSpawner(val developers: Set<Developer>, val components: Set<Component>, val weeks: Int): Spawner {
    override fun spawn(): Schedule {
        val s = Schedule(developers, components, weeks)
        s.weeks.forEach { week ->
            components.forEach { component ->
                s.set(component, week, developers.choose())
            }
        }
        return s
    }
}
