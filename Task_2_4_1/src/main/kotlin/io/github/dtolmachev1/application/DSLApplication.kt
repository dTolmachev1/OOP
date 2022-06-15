package io.github.dtolmachev1.application

import io.github.dtolmachev1.attendance.Attendance
import io.github.dtolmachev1.config.ConfigBuilder
import io.github.dtolmachev1.config.ConfigLoader
import io.github.dtolmachev1.git.GitHandler
import io.github.dtolmachev1.gradle.GradleHandler
import io.github.dtolmachev1.report.ReportBuilder

import java.nio.file.Files
import java.nio.file.Paths

class DSLApplication {
    fun buildConfig() {
        val configBuilder = ConfigBuilder()
        var name = ""
        while(name == "") {
            println("Student's name:")
            name = readLine() ?: continue
            if (Files.exists(Paths.get("./configs/$name.kts"))) {
                println("$name's config already created")
                return
            }
        }
        var userName = ""
        while(userName == "") {
            println("Student's username:")
            userName = readLine() ?: continue
        }
        var repo = ""
        while(repo == "") {
            println("Student's repo URL:")
            repo = readLine() ?: continue
        }
        var group = ""
        while(group == "") {
            println("Student's group:")
            group = readLine() ?: continue
        }
        configBuilder.build(name, userName, repo, group)
        println("Config creation succeeded")
    }

    fun showConfig(name: String) {
        if(Files.notExists(Paths.get("./configs/$name.kts"))) {
            println("Config not found")
            return
        }
        val configLoader = ConfigLoader()
        println(configLoader.load(name))
    }

    fun buildReport(name: String) {
        val reportBuilder = ReportBuilder()
        reportBuilder.build(name)
        println("Report creation succeeded")
    }

    fun clone(name: String) {
        val gitHandler = GitHandler()
        gitHandler.clone(name)
        println("Cloning succeeded")
    }

    fun pull(name: String, branch: String) {
        if(Files.notExists(Paths.get("./repos/$name"))) {
            println("$name's repo not found")
            return
        }
        val gitHandler = GitHandler()
        gitHandler.pull(name, branch)
        println("Pulling succeeded")
    }

    fun test(name: String, task: String) {
        val gradleHandler = GradleHandler()
        if(gradleHandler.test(name, task)) {
            println("Testing succeeded")
        } else println("Testing failed")
    }

    fun javadoc(name: String, task: String) {
        val gradleHandler = GradleHandler()
        gradleHandler.javadoc(name, task)
        println("Javadoc creation succeeded")
    }

    fun checkStyle(name: String, task: String) {
        val gradleHandler = GradleHandler()
        gradleHandler.checkStyle(name, task)
        println("Code style checking succeeded")
    }

    fun testReport(name: String, task: String) {
        val gradleHandler = GradleHandler()
        gradleHandler.testReport(name, task)
        println("Test coverage checking succeeded")
    }

    fun checkAttendance(group: String, task: String, date: String) {
        val attendance = Attendance()
        attendance.checkAttendance(group, task, date)
    }
}