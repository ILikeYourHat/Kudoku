plugins {
    application
    alias(libs.plugins.kotlin)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
    alias(libs.plugins.axion)
}

application {
    applicationName = "kudoku"
    mainClass = "io.github.ilikeyourhat.kudoku.cli.KudokuKt"
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

    implementation(project(":core"))
    implementation(libs.clikt)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotest)
}
