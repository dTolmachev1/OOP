package io.github.dtolmachev1.dsl.sentinels

import io.github.dtolmachev1.dsl.builders.LessonBuilder
import io.github.dtolmachev1.dsl.constructors.Lesson

class Lessons : ArrayList<Lesson>() {
    fun lesson(block: LessonBuilder.() -> Unit) {
        add(LessonBuilder().apply(block).build())
    }
}
