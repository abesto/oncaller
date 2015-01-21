package net.abesto.oncaller

import kotlin.js.dom.html.document
import kotlin.js.dom.html.HTMLElement
import net.abesto.oncaller.model.Component
import net.abesto.oncaller.model.Developer
import net.abesto.oncaller.model.Schedule
import net.abesto.oncaller.spawners.RandomSpawner
import net.abesto.oncaller.mutators.RandomSwapMutator
import net.abesto.oncaller.prices.OncallWeekPrice
import net.abesto.oncaller.optimizers.SimpleOptimizer
import net.abesto.oncaller.mutators.Mutator
import net.abesto.oncaller.prices.Price

fun main(args: Array<String>) {
    val renderer = Renderer(document.getElementById("container") as HTMLElement)
    val developers = Array(8, { Developer("Dev ${it}")}).toSet()
    val components = Array(10, { Component("Component ${it}") }).toSet()

    val spawner = RandomSpawner(developers, components, 12)
    console.log(spawner.spawn())
    val mutators: Array<Mutator> = array(RandomSwapMutator())
    val prices: Array<Price> = array(OncallWeekPrice())

    val optimizer = SimpleOptimizer()
    val schedules = optimizer.run(spawner, mutators, prices)
    val top = schedules.first().first

    console.log(top)
    renderer.render(top)
}
