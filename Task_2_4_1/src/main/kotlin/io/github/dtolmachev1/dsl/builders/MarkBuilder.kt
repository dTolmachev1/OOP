package io.github.dtolmachev1.dsl.builders

import io.github.dtolmachev1.dsl.constructors.Mark

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MarkBuilder {
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    private var dateValue: LocalDate = LocalDate.parse("01.01.1970", dateTimeFormatter)
    val score = -1
    var date: String = ""
        set(value) {
            this.dateValue = LocalDate.parse(value, this.dateTimeFormatter)
        }

    fun build(): Mark {
        if(this.score == -1) {
            throw IllegalArgumentException("Score not found")
        }
        if(this.dateValue == LocalDate.parse("01.01.1970", dateTimeFormatter)) {
            throw IllegalArgumentException("Date not found")
        }
        return Mark(this.score, this.dateValue)
    }
}
