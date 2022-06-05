package io.github.dtolmachev1.dsl.sentinels

import io.github.dtolmachev1.dsl.builders.StudentBuilder
import io.github.dtolmachev1.dsl.constructors.Student

class Students : ArrayList<Student>() {
    fun student(block: StudentBuilder.() -> Unit) {
        add(StudentBuilder().apply(block).build())
    }
}
