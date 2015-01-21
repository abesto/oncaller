package net.abesto.oncaller.mutators

import net.abesto.oncaller.model.Schedule
import net.abesto.oncaller.extensions.choose
import net.abesto.oncaller.extensions.randInt

class RandomSwapMutator(): Mutator {
    override fun mutate(schedule: Schedule): Schedule {
        val c1 = schedule.components.choose()
        val c2 = schedule.components.choose()
        val w1 = schedule.weeks.choose()
        val w2 = schedule.weeks.choose()
        val tmp = schedule.get(c1, w1)
        schedule.set(c1, w1, schedule.get(c2, w2))
        schedule.set(c2, w2, tmp)
        return schedule
    }
}

