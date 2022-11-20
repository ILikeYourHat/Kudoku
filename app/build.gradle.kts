@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin)
}

group = "com.github.ilikeyourhat.kudoku"
version = "1.0"

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.sat4j)
    testImplementation(libs.junit5.api)
    testRuntimeOnly(libs.junit5.engine)
}
