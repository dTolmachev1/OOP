package io.github.dtolmachev1.attendance

import io.github.dtolmachev1.config.ConfigLoader
import io.github.dtolmachev1.dsl.constructors.Student
import io.github.dtolmachev1.git.GitHandler

import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

class Attendance {
    fun checkAttendance(groupId: String, taskTitle: String, date: String) {
        val group: List<Student> = configureGroup(groupId)
        if(group.isEmpty()) {
            println("No students from this group")
            return
        }
        checkCommits(group, taskTitle, date)
    }

    private fun configureGroup(groupId: String): List<Student> {
        return loadConfigs().filter { it.group == groupId }
    }

    private fun loadConfigs(): MutableList<Student> {
        val configLoader = ConfigLoader()
        val students: MutableList<Student> = mutableListOf()
        Files.newDirectoryStream(Paths.get("./configs"), "*.kts").forEach { students.add(configLoader.load(it.fileName.toString().substringBeforeLast('.'))) }
        return students
    }

    private fun checkCommits(group: List<Student>, taskTitle: String, date: String) {
        val gitHandler = GitHandler()
        group.forEach { gitHandler.pull(it.name, taskTitle) }
        val localDate: LocalDate = SimpleDateFormat("dd.MM.yyyy").parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val start: Date = Date.from(localDate.with(DayOfWeek.MONDAY).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
        val end: Date = Date.from(localDate.with(DayOfWeek.SUNDAY).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
        group.forEach {
            if(gitHandler.isStudentCommitted(it.name, start, end)) {
                println("${it.name} was on lesson $date")
            } else println("${it.name} was absent from lesson $date")
        }
    }
}
