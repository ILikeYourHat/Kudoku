package io.github.ilikeyourhat.kudoku.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import pl.allegro.tech.build.axion.release.domain.VersionConfig

class PublishingPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("maven-publish")
                apply("signing")
                apply("pl.allegro.tech.build.axion-release")
            }
            configure<JavaPluginExtension> {
                withSourcesJar()
                withJavadocJar()
            }
            configure<PublishingExtension> {
                afterEvaluate {
                    publications {
                        create<MavenPublication>("release") {
                            from(components["java"])
                            groupId = "io.github.ilikeyourhat.kudoku"
                            artifactId = target.name
                            version = extensions.getByType<VersionConfig>().version
                            pom {
                                name.set("Kudoku")
                                description.set("A sudoku engine written in Kotlin")
                                url.set("https://github.com/ILikeYourHat/Kudoku")
                                licenses {
                                    license {
                                        name.set("The Apache License, Version 2.0")
                                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                                    }
                                }
                                developers {
                                    developer {
                                        id.set("ILikeYourHat")
                                        name.set("Marcin Laskowski")
                                    }
                                }
                                scm {
                                    connection.set("git@github.com:ILikeYourHat/Kudoku.git")
                                    developerConnection.set("git@github.com:ILikeYourHat/Kudoku.git")
                                    url.set("https://github.com/ILikeYourHat/Kudoku")
                                }
                            }
                        }
                    }
                    repositories {
                        maven {
                            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                            credentials {
                                username = findProperty("mavenUsername") as String?
                                password = findProperty("mavenPassword") as String?
                            }
                        }
                    }
                }
            }
        }
    }
}