package io.github.dtolmachev1.dsl.builders

import io.github.dtolmachev1.dsl.constructors.Task
import io.github.dtolmachev1.dsl.constructors.TaskList
import io.github.dtolmachev1.dsl.sentinels.Tasks

class TaskListBuilder {
    val items: MutableList<Task> = mutableListOf()
    fun items(block: Tasks.() -> Unit) {
        this.items.addAll(Tasks().apply(block))
    }

    fun build(): TaskList {
        return TaskList(this.items)
    }
}
