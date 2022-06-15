package io.github.dtolmachev1.dsl.builders

import io.github.dtolmachev1.dsl.constructors.Task

class TaskBuilder {
    var title: String = ""
    var description: String = ""
    var score: Int = -1

    fun build(): Task {
        if(this.title == "") {
            throw IllegalArgumentException("Title not found")
        }
        if(this.description == "") {
            throw IllegalArgumentException("Description not found")
        }
        if(this.score == -1) {
            throw IllegalArgumentException("Score not found")
        }
        return Task(this.title, this.description, this.score)
    }
}
