package io.github.dtolmachev1.report

import io.github.dtolmachev1.config.ConfigLoader
import io.github.dtolmachev1.dsl.constructors.AssignedTask
import io.github.dtolmachev1.dsl.constructors.Student
import io.github.dtolmachev1.gradle.GradleHandler

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate

import kotlinx.html.HTML
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.h3
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.li
import kotlinx.html.table
import kotlinx.html.td
import kotlinx.html.th
import kotlinx.html.title
import kotlinx.html.tr
import kotlinx.html.ul
import kotlinx.html.stream.appendHTML

class ReportBuilder {
    fun build(name: String) {
        val path: Path = Paths.get("./reports/$name/report.html")
        if(Files.notExists(path)) {
            Files.createDirectories(path.parent)
            Files.createFile(path)
        }
        val configLoader = ConfigLoader()
        val student: Student = configLoader.load(name)
        val testResults: Map<String, Boolean> = runTests(name, student.tasks)
        Files.newBufferedWriter(path).use { bufferedWriter ->
            bufferedWriter.appendHTML().html(block = createReport(student, testResults))
        }
    }

    private fun createReport(student: Student, testResults: Map<String, Boolean>): HTML.() -> Unit = {
        head {
            title {
                +"Report"
            }
        }
        body {
            h1 {
                +"Report for ${student.name} generated at ${LocalDate.now()}"
            }
            h3 {
                +"General information"
            }
            ul {
                li {
                    +"Username: ${student.userName}"
                }
                li {
                    +"Github repo: "
                    a(student.repo) {
                        +student.repo
                    }
                }
                li {
                    +"Group: ${student.group}"
                }
            }
            h3 {
                +"Test results"
            }
            table {
                tr {
                    th {
                        +"Task"
                    }
                    th {
                        +"Result"
                    }
                }
                for((key, value) in testResults.entries.iterator()) {
                    tr {
                        td {
                            +key
                        }
                        td {
                            + if(value) "PASSED" else "FAILED"
                        }
                    }
                }
            }
            h3 {
                +"Lessons"
            }
            table {
                tr {
                    th {
                        +"Date"
                    }
                    th {
                        +"Attendance"
                    }
                }
                for(lesson in student.lessons) {
                    tr {
                        td {
                            +lesson.date.toString()
                        }
                        td {
                            + if(lesson.attendance) "ATTENDED" else "ABSENT"
                        }
                    }
                }
            }
            h3 {
                +"Marks"
            }
            table {
                tr {
                    th {
                        +"Date"
                    }
                    th {
                        +"Score"
                    }
                }
                for(mark in student.marks) {
                    tr {
                        td {
                            +mark.date.toString()
                        }
                        td {
                            +mark.score.toString()
                        }
                    }
                }
            }
        }
    }

    private fun runTests(name: String, assignedTasks: List<AssignedTask>): Map<String, Boolean> {
        val gradleHandler = GradleHandler()
        return assignedTasks.associate { it.title to gradleHandler.test(name, it.title) }
    }
}
