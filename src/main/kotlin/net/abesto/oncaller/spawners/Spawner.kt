package net.abesto.oncaller.spawners

import net.abesto.oncaller.model.Developer
import net.abesto.oncaller.model.Component
import net.abesto.oncaller.model.Schedule

public trait Spawner {
    fun spawn(): Schedule

    fun spawnMany(n: Int): Array<Schedule> = Array(n, { spawn() })
}