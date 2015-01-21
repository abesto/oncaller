package net.abesto.oncaller.optimizers

import net.abesto.oncaller.spawners.Spawner
import net.abesto.oncaller.mutators.Mutator
import net.abesto.oncaller.prices.Price
import net.abesto.oncaller.model.Schedule
import net.abesto.oncaller.extensions.choose
import net.abesto.oncaller.extensions.splice

class SimpleOptimizer(): Optimizer {
    override fun run(spawner: Spawner, mutators: Array<Mutator>, scorings: Array<Price>): Array<Pair<Schedule, Int>> {
        fun score(schedule: Schedule) = scorings.sumBy { scoring ->
            scoring.calculate(schedule)
        }
        var population = spawner.spawnMany(100)
        for (gen in 0 .. 20) {
            console.log("Starting generation ${gen}")
            for (n in 0 .. 10) {
                population.forEach { schedule ->
                    mutators.choose().mutate(schedule)
                }
            }
            population
                .sortDescendingBy{ score(it) }
                .copyToArray()
                .splice(50, 50, *spawner.spawnMany(50))
        }

        return population.map { Pair(it, score(it)) }.sortBy { it.second }.copyToArray()
    }
}
