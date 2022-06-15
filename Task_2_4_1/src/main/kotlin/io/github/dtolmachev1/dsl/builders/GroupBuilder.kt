package io.github.dtolmachev1.dsl.builders

import io.github.dtolmachev1.dsl.constructors.Group
import io.github.dtolmachev1.dsl.constructors.Student
import io.github.dtolmachev1.dsl.sentinels.Students

class GroupBuilder {
    var id: String = ""
    val students: MutableList<Student> = mutableListOf()
    fun students(block: Students.() -> Unit) {
        this.students.addAll(Students().apply(block))
    }

    fun build(): Group {
        if(this.id == "") {
            throw IllegalArgumentException("ID not found")
        }
        return Group(this.id, this.students)
    }
}
