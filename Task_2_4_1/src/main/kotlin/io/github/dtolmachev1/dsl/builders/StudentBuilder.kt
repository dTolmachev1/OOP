package io.github.dtolmachev1.dsl.builders

import io.github.dtolmachev1.dsl.constructors.AssignedTask
import io.github.dtolmachev1.dsl.constructors.Lesson
import io.github.dtolmachev1.dsl.constructors.Mark
import io.github.dtolmachev1.dsl.constructors.Student
import io.github.dtolmachev1.dsl.sentinels.AssignedTasks
import io.github.dtolmachev1.dsl.sentinels.Lessons
import io.github.dtolmachev1.dsl.sentinels.Marks

class StudentBuilder {
    val name: String = ""
    val userName: String = ""
    val repo: String = ""
    val group: String = ""
    var assignedTasks: MutableList<AssignedTask> = mutableListOf()
    fun assignedTasks(block: AssignedTasks.() -> Unit) {
        this.assignedTasks.addAll(AssignedTasks().apply(block))
    }
    var lessons: MutableList<Lesson> = mutableListOf()
    fun lessons(block: Lessons.() -> Unit) {
        this.lessons.addAll(Lessons().apply(block))
    }
    var marks: MutableList<Mark> = mutableListOf()
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
        return Student(this.name, this.userName, this.repo, this.group, this.assignedTasks, this.lessons, this.marks)
    }
}
