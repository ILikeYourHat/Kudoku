plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
    alias(libs.plugins.axion)
    alias(libs.plugins.maven.publish)
}

kotlin {
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(17)
        vendor = JvmVendorSpec.ADOPTIUM
    }
    compilerOptions {
        allWarningsAsErrors = true
        extraWarnings = true
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

kover {
    reports {
        total {
            xml.onCheck = true
            html.onCheck = true
        }
    }
}

dependencies {
    detektPlugins(libs.detekt.formatting)

    implementation(libs.sat4j)
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(platform(libs.junit5.bom))
    testRuntimeOnly(libs.junit5.platform)
    testImplementation(libs.bundles.junit5)
    testImplementation(libs.kotest)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()
    coordinates(
        groupId = "io.github.ilikeyourhat.kudoku",
        artifactId = "kudoku",
        version = scmVersion.version
    )
    pom {
        name = "Kudoku"
        description = "A sudoku engine written in Kotlin"
        url = "https://github.com/ILikeYourHat/Kudoku"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "ILikeYourHat"
                name = "Marcin Laskowski"
            }
        }
        scm {
            connection = "git@github.com:ILikeYourHat/Kudoku.git"
            developerConnection = "git@github.com:ILikeYourHat/Kudoku.git"
            url = "https://github.com/ILikeYourHat/Kudoku"
        }
    }
}
