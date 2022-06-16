package io.github.dtolmachev1.dsl.builders

import io.github.dtolmachev1.dsl.constructors.AssignedTask
import io.github.dtolmachev1.dsl.constructors.Lesson
import io.github.dtolmachev1.dsl.constructors.Mark
import io.github.dtolmachev1.dsl.constructors.Student
import io.github.dtolmachev1.dsl.sentinels.AssignedTasks
import io.github.dtolmachev1.dsl.sentinels.Lessons
import io.github.dtolmachev1.dsl.sentinels.Marks

class StudentBuilder {
    var name: String = ""
    var userName: String = ""
    var repo: String = ""
    var group: String = ""
    val tasks: MutableList<AssignedTask> = mutableListOf()
    fun tasks(block: AssignedTasks.() -> Unit) {
        this.tasks.addAll(AssignedTasks().apply(block))
    }
    val lessons: MutableList<Lesson> = mutableListOf()
    fun lessons(block: Lessons.() -> Unit) {
        this.lessons.addAll(Lessons().apply(block))
    }
    val marks: MutableList<Mark> = mutableListOf()
    fun marks(block: Marks.() -> Unit) {
        this.marks.addAll(Marks().apply(block))
    }

    fun build(): Student {
        if(this.name == "") {
            throw IllegalArgumentException("Name not found")
        }
        if(this.userName == "") {
            throw IllegalArgumentException("Username not found")
        }
        if(this.repo == "") {
            throw IllegalArgumentException("URL not found")
        }
        if(this.group == "") {
            throw IllegalArgumentException("Group not found")
        }
        return Student(this.name, this.userName, this.repo, this.group, this.tasks, this.lessons, this.marks)
    }
}
