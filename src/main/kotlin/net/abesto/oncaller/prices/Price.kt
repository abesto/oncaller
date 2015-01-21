package net.abesto.oncaller.prices

import net.abesto.oncaller.model.Schedule

public trait Price {
    fun calculate(schedule: Schedule): Int
}