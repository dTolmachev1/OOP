package io.github.dtolmachev1.git

import io.github.dtolmachev1.config.ConfigLoader
import io.github.dtolmachev1.dsl.constructors.Student

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.JGitInternalException
import org.eclipse.jgit.api.errors.RefNotFoundException
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter
import org.eclipse.jgit.transport.URIish

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Date

class GitHandler {
    fun pull(name: String, branch: String) {
        val git: Git = Git.open(Paths.get("./repos/$name").toFile())
        try {
            git.checkout().setName(branch).call()
        } catch(e: RefNotFoundException) {
            git.checkout().setCreateBranch(true).setName(branch).call()
        }
        try {
            git.pull().remote = name
            git.pull().call()
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    fun clone(name: String) {
        val path: Path = Paths.get("./repos/$name")
        Git.init().setDirectory(path.parent.toFile()).call()
        if(Files.notExists(path)) {
            Files.createDirectory(path)
        }
        val configLoader = ConfigLoader()
        val student: Student = configLoader.load(name)
        try {
            Git.cloneRepository().setURI(student.repo).setDirectory(path.toFile()).call()
        } catch(e: JGitInternalException) {
            println("Repo already exists")
            return
        }
        val git: Git = Git.open(path.toFile())
        git.remoteAdd().setName(name).setUri(URIish(student.repo)).call()
    }

    @Suppress("ReplaceSizeCheckWithIsNotEmpty")
    fun isStudentCommitted(name: String, start: Date, end: Date): Boolean {
        val git: Git = Git.open(Paths.get("./repos/$name").toFile())
        val revCommit: Iterable<RevCommit> = git.log().setRevFilter(CommitTimeRevFilter.between(start, end)).call()
        if(revCommit.count() > 0) {
            return true
        }
        return false
    }
}
