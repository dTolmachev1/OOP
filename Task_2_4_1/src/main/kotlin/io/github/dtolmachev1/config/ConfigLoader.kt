package io.github.dtolmachev1.config

import io.github.dtolmachev1.dsl.constructors.Student

import java.nio.file.Files
import java.nio.file.Paths

import javax.script.ScriptEngineManager

class ConfigLoader {
    fun load(name: String): Student {
        val config: String = Files.readString(Paths.get("./configs/$name.kts"))
        var evaluated: Student
        with(ScriptEngineManager().getEngineByExtension("kts")) {
            evaluated = eval(config) as Student
        }
        return evaluated
    }
}