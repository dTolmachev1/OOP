package io.github.dtolmachev1.dsl

import io.github.dtolmachev1.dsl.builders.StudentBuilder
import io.github.dtolmachev1.dsl.constructors.Student

fun student(block: StudentBuilder.() -> Unit): Student = StudentBuilder().apply(block).build()
