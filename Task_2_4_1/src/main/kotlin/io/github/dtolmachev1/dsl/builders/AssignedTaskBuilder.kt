package io.github.dtolmachev1.dsl.builders

import io.github.dtolmachev1.dsl.constructors.AssignedTask

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AssignedTaskBuilder {
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    private var dateValue: LocalDate = LocalDate.parse("01.01.1970", dateTimeFormatter)
    var title: String = ""
    var date: String = ""
        set(value) {
            this.dateValue = LocalDate.parse(value, this.dateTimeFormatter)
        }

    fun build(): AssignedTask {
        if(this.title == "") {
            throw IllegalArgumentException("ID not found")
        }
        if(this.dateValue == LocalDate.parse("01.01.1970", dateTimeFormatter)) {
            throw IllegalArgumentException("Date not found")
        }
        return AssignedTask(this.title, this.dateValue)
    }
}
