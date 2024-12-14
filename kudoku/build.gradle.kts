plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
    alias(libs.plugins.kudoku.publishing)
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
