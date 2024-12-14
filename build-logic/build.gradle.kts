plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.axion.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kudoku.publishing") {
            id = "kudoku.publishing"
            implementationClass = "io.github.ilikeyourhat.kudoku.plugins.PublishingPlugin"
        }
    }
}