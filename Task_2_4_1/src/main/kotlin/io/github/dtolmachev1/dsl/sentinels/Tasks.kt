package io.github.dtolmachev1.dsl.sentinels

import io.github.dtolmachev1.dsl.builders.TaskBuilder
import io.github.dtolmachev1.dsl.constructors.Task

class Tasks : ArrayList<Task>() {
    fun task(block: TaskBuilder.() -> Unit) {
        add(TaskBuilder().apply(block).build())
    }
}
