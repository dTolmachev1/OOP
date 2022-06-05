package io.github.dtolmachev1.dsl.constructors

data class Student(val name: String, val userName: String, val repo: String, val group: String, val tasks: List<AssignedTask>, val lessons: List<Lesson>, val marks: List<Mark>)
