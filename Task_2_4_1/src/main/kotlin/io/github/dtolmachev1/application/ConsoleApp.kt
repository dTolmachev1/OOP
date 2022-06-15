package io.github.dtolmachev1.application

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand

@Suppress("OPT_IN_USAGE", "OPT_IN_OVERRIDE")
fun main(args: Array<String>) {
    val dsl = DSLApplication()

    class BuildConfig : Subcommand("build-config", "Build config") {
        override fun execute() {
            dsl.buildConfig()
        }
    }

    class ShowConfig: Subcommand("show-config", "Show student's config") {
        val studentName by argument(ArgType.String, description = "Student's name")

        override fun execute() {
            dsl.showConfig(studentName)
        }
    }

    class BuildReport: Subcommand("build-report", "Build report for student") {
        val studentName by argument(ArgType.String, description = "Student's name")

        override fun execute() {
            dsl.buildReport(studentName)
        }
    }

    class Clone: Subcommand("clone", "Clone student's repo") {
        val studentName by argument(ArgType.String, description = "Student's name")

        override fun execute() {
            dsl.clone(studentName)
        }
    }

    class Pull: Subcommand("pull", "Pull specified branch from given repo") {
        val studentName by argument(ArgType.String, description = "Student's name")
        val branch by argument(ArgType.String, description = "Branch to be pulled")

        override fun execute() {
            dsl.pull(studentName, branch)
        }
    }

    class Test: Subcommand("test", "Test specified task") {
        val studentName by argument(ArgType.String, description = "Student's name")
        val taskTitle by argument(ArgType.String, description = "Task to be tested")

        override fun execute() {
            dsl.test(studentName, taskTitle)
        }
    }

    class Javadoc: Subcommand("javadoc", "Create javadoc for the specified task") {
        val studentName by argument(ArgType.String, description = "Student's name")
        val taskTitle by argument(ArgType.String, description = "Task to be documented")

        override fun execute() {
            dsl.javadoc(studentName, taskTitle)
        }
    }

    class CheckStyle: Subcommand("check-style", "Check code style for the specified task") {
        val studentName by argument(ArgType.String, description = "Student's name")
        val taskTitle by argument(ArgType.String, description = "Task to be checked for code style")

        override fun execute() {
            dsl.checkStyle(studentName, taskTitle)
        }
    }

    class TestReport: Subcommand("test-report", "Create test coverage report for the specified task") {
        val studentName by argument(ArgType.String, description = "Student's name")
        val taskTitle by argument(ArgType.String, description = "Task to be checked for test coverage")

        override fun execute() {
            dsl.testReport(studentName, taskTitle)
        }
    }

    class CheckAttendance: Subcommand("check-attendance", "Check attendance for specified group, task and date") {
        val groupId by argument(ArgType.String, description = "Group id")
        val taskTitle by argument(ArgType.String, description = "Task title")
        val date by argument(ArgType.String, description = "Date")

        override fun execute() {
            dsl.checkAttendance(groupId, taskTitle, date)
        }
    }

    val buildConfig = BuildConfig()
    val showConfig = ShowConfig()
    val buildReport = BuildReport()
    val clone = Clone()
    val pull = Pull()
    val test = Test()
    val javadoc = Javadoc()
    val checkStyle = CheckStyle()
    val testReport = TestReport()
    val checkAttendance = CheckAttendance()
    val parser = ArgParser("DSL")
    parser.subcommands(buildConfig, showConfig, buildReport, clone, pull, test, javadoc, checkStyle, testReport, checkAttendance)
    parser.parse(args)
}
