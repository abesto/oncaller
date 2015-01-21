package net.abesto.oncaller.prices

import net.abesto.oncaller.model.Schedule

class OncallWeekPrice() : Price {
    override fun calculate(schedule: Schedule): Int =
        schedule.developers.map { developer ->
            schedule.weeks.count { week ->
                schedule.components.any { component ->
                    schedule.get(component, week) == developer
                }
            }
        }.max() ?: 0
}
