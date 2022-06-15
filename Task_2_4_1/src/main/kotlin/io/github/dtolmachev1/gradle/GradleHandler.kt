package io.github.dtolmachev1.gradle

import org.gradle.tooling.BuildException
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProjectConnection

import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import java.nio.file.Paths

class GradleHandler {
    fun test(name: String, project: String): Boolean {
        try {
            runTasks(name, project, "test")
        } catch(e: BuildException) {
            return false
        }
        return true
    }

    fun javadoc(name: String, project: String) {
        runTasks(name, project, "javadoc")
        prepareArtifacts(Paths.get("./repos/$name/$project/build/docs/javadoc"), Paths.get("./reports/$name/$project/javadoc"))
    }

    fun checkStyle(name: String, project: String) {
        runTasks(name, project, "checkstyleMain")
        prepareArtifacts(Paths.get("./repos/$name/$project/build/reports/checkstyle"), Paths.get("./reports/$name/$project/checkstyle"))
    }

    @SuppressWarnings("SpellCheckingInspection")
    fun testReport(name: String, project: String) {
        runTasks(name, project, "jacocoTestReport")
        prepareArtifacts(Paths.get("./repos/$name/$project/build/jacocoHtml"), Paths.get("./reports/$name/$project/testreport"))
    }

    @Suppress("NAME_SHADOWING")
    private fun runTasks(name: String, project: String, tasks: String) {
        val path: Path = Paths.get("./repos/$name/$project")
        if(Files.notExists(path)) {
            throw NoSuchFileException("$project not found in $name's repo")
        }
        val projectConnection: ProjectConnection = GradleConnector.newConnector().forProjectDirectory(path.toFile()).connect()
        projectConnection.use { projectConnection ->
            projectConnection.newBuild().forTasks(tasks).run()
        }
    }

    private fun prepareArtifacts(srcDir: Path, destDir: Path) {
        if(Files.notExists(destDir)) {
        Files.createDirectory(destDir)
        } else Files.newDirectoryStream(destDir).forEach { it.toFile().deleteRecursively() }
        Files.newDirectoryStream(srcDir).forEach { it.toFile().copyRecursively(destDir.toFile()) }
    }
}
