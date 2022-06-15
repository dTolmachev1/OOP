import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.gradle.org/gradle/libs-releases") }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.eclipse.jgit:org.eclipse.jgit:6.1.0.202203080745-r")
    implementation("org.gradle:gradle-tooling-api:7.4.2")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.6.21")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.21")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.6.21")
    implementation("org.jetbrains.kotlin:kotlin-script-util:1.6.21")
    implementation("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:1.6.21")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.21")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.4")
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.5")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.5")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.36")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("io.github.dtolmachev1.application.ConsoleAppKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "io.github.dtolmachev1.application.ConsoleAppKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
