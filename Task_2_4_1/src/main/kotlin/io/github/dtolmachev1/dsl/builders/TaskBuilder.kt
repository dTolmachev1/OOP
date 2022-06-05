package io.github.dtolmachev1.dsl.builders

import io.github.dtolmachev1.dsl.constructors.Task

class TaskBuilder {
    val id: Int = -1
    val title: String = ""
    val description: String = ""
    val score: Int = -1

    fun build(): Task {
        if(this.id == -1) {
            throw IllegalArgumentException("ID not found")
        }
        if(this.title == "") {
            throw IllegalArgumentException("Title not found")
        }
        if(this.description == "") {
            throw IllegalArgumentException("Description not found")
        }
        if(this.score == -1) {
            throw IllegalArgumentException("Score not found")
        }
        return Task(this.id, this.title, this.description, this.score)
    }
}
