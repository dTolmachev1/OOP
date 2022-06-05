package io.github.dtolmachev1.dsl.sentinels

import io.github.dtolmachev1.dsl.builders.MarkBuilder
import io.github.dtolmachev1.dsl.constructors.Mark

class Marks : ArrayList<Mark>() {
    fun mark(block: MarkBuilder.() -> Unit) {
        add(MarkBuilder().apply(block).build())
    }
}
