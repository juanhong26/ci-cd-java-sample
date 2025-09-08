plugins {
    application
}

application {
    mainClass.set("org.example.App")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.example.App"
    }
}

tasks.register<Jar>("fatJar") {
    archiveClassifier.set("all")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.exists() }.map {
            if (it.isDirectory) it else zipTree(it)
        }
    })

    manifest {
        attributes["Main-Class"] = "org.example.App"
    }
}
