plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
    alias(libs.plugins.axion)
}

group = "io.github.ilikeyourhat.kudoku"
version = scmVersion.version

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
        vendor = JvmVendorSpec.ADOPTIUM
    }
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("$rootDir/config/detekt.yml")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.sat4j)

    testRuntimeOnly(libs.junit5.platform)

    testImplementation(libs.junit5.api)
    testImplementation(libs.junit5.params)
    testImplementation(libs.kotest)
    testImplementation(libs.mockk)
}
