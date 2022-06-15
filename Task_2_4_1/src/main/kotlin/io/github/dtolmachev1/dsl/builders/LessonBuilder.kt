package io.github.dtolmachev1.dsl.builders

import io.github.dtolmachev1.dsl.constructors.Lesson

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LessonBuilder {
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    private var dateValue: LocalDate = LocalDate.parse("01.01.1970", dateTimeFormatter)
    var date: String = ""
        set(value) {
            this.dateValue = LocalDate.parse(value, this.dateTimeFormatter)
        }
    var attendance: Boolean = false

    fun build(): Lesson {
        if(this.dateValue == LocalDate.parse("01.01.1970", dateTimeFormatter)) {
            throw IllegalArgumentException("Date not found")
        }
        return Lesson(this.dateValue, this.attendance)
    }
}
