import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
    id("com.vanniktech.maven.publish") version "0.30.0"
}

kotlin {
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(17)
        vendor = JvmVendorSpec.ADOPTIUM
    }
    compilerOptions {
        allWarningsAsErrors = true
        extraWarnings = true
        // https://youtrack.jetbrains.com/issue/KT-73556
        freeCompilerArgs.add("-Xsuppress-warning=UNUSED_ANONYMOUS_PARAMETER")
    }
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("$rootDir/config/detekt.yml")
}

tasks.getByName("check") {
    dependsOn += "detektMain"
    dependsOn += "detektTest"
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    detektPlugins(libs.detekt.formatting)

    implementation(libs.sat4j)

    testRuntimeOnly(libs.junit5.platform)
    testImplementation(libs.junit5.api)
    testImplementation(libs.junit5.params)
    testImplementation(libs.kotest)
    testImplementation(libs.mockk)
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates("io.github.ilikeyourhat.kudoku", "kudoku", "0.0.1")
    pom {
        name = "Kudoku"
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
