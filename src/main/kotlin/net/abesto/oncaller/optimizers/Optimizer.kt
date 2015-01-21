package net.abesto.oncaller.optimizers

import net.abesto.oncaller.model.Schedule
import net.abesto.oncaller.mutators.Mutator
import net.abesto.oncaller.prices.Price
import net.abesto.oncaller.spawners.Spawner

public trait Optimizer {
    fun run(spawner: Spawner, mutators: Array<Mutator>, scorings: Array<Price>): Array<Pair<Schedule, Int>>
}