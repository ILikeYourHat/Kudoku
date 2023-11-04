plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.detekt)
}

group = "com.github.ilikeyourhat.kudoku"
version = "1.0"

tasks.test {
    useJUnitPlatform()
}

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

dependencies {
    implementation(libs.sat4j)

    testRuntimeOnly(libs.junit5.engine)
    testImplementation(libs.junit5.api)
}
