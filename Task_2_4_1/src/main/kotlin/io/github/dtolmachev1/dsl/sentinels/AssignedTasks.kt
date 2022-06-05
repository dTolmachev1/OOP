package io.github.dtolmachev1.dsl.sentinels

import io.github.dtolmachev1.dsl.builders.AssignedTaskBuilder
import io.github.dtolmachev1.dsl.constructors.AssignedTask

class AssignedTasks : ArrayList<AssignedTask>() {
    fun assignedTask(block: AssignedTaskBuilder.() -> Unit) {
        add(AssignedTaskBuilder().apply(block).build())
    }
}
