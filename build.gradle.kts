plugins {
    kotlin("multiplatform") version "1.3.61"
}

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations {
            val main by getting {
                kotlinOptions {
                    freeCompilerArgs = freeCompilerArgs + "-progressive"
                    jvmTarget = "1.8"
                }
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:+")
                implementation("org.telegram:telegrambots:+")
                implementation("org.apache.logging.log4j:log4j-core:+")
                implementation("org.apache.logging.log4j:log4j-slf4j-impl:+")
            }
        }
        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}