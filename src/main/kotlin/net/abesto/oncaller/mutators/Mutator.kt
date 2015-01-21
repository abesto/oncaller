package net.abesto.oncaller.mutators

import net.abesto.oncaller.model.Schedule

public trait Mutator {
    fun mutate(schedule: Schedule): Schedule
}