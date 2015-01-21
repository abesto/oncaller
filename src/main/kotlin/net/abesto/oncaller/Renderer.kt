package net.abesto.oncaller

import kotlin.js.dom.html.HTMLElement
import kotlin.dom.first
import net.abesto.oncaller.model.Schedule


class Renderer(val container: HTMLElement) {
    fun render(schedule: Schedule) {
        container.innerHTML = table { table ->
            schedule.components.forEach { component ->
                table.tr { tr ->
                    schedule.weeks.forEach { week ->
                        tr.td {
                            + (schedule.get(component, week)?.name ?: "N/A")
                        }
                    }
                }
            }
        }.toString()
    }
}
