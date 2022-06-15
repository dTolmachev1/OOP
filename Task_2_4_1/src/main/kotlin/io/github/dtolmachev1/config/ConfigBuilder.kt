package io.github.dtolmachev1.config

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class ConfigBuilder {
    fun build(name: String, userName: String, repo: String, group: String) {
        val path: Path = Paths.get("./configs/$name.kts")
        if(Files.notExists(path)) {
            Files.createFile(path)
        }
        if(Files.size(path) == 0L) {
            Files.writeString(path, createConfig(name, userName, repo, group))
        } else println("$name's config already created")
    }

    @SuppressWarnings("SpellCheckingInspection")
    private fun createConfig(name: String, userName: String, repo: String, group: String): String {
        return """
            import io.github.dtolmachev1.dsl.DSL
            
            DSL.student{
                name = $name
                userName = $userName
                repo = $repo
                group = $group
                tasks {
                    /*
                    fill list of assigned tasks in the following way:
                    assignedTask {
                        title = "Task 0.0.0"
                        deadline = "01.01.1970"
                    }
                    */
                }
                lessons {
                    /*
                    Fill list of lessons in the following way:
                    lesson {
                        date = "01.01.1970"
                        attendance = false
                    }
                    */
                }
                marks {
                    /*
                    Fill list of marks in the following way:
                    mark {
                        score = 0
                        date = "01.01.1970"
                    }
                    */
                }
            }
        """.trimIndent()
    }
}
