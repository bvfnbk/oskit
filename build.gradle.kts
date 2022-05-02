import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    `maven-publish`
}

group = "com.github.bvfnbk"
version = "0.1.1"

repositories {
    mavenCentral()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/bvfnbk/oskit")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    // Test
    testImplementation(kotlin("test"))
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.25")
}

val compileKotlin: KotlinCompile by tasks
val compileTestKotlin: KotlinCompile by tasks
val test: Test by tasks

compileKotlin.kotlinOptions {
    jvmTarget = "16"
}

compileTestKotlin.kotlinOptions {
    jvmTarget = "16"
}

test.useJUnitPlatform()
