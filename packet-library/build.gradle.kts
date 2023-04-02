plugins {
    kotlin("jvm") version "1.8.10"
    application
}

group = "com.zeriamc.networking"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.ghidorah.uk/public")
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.10")
    implementation("org.apache.logging.log4j:log4j-api:2.0-beta9")
    implementation("com.esotericsoftware:kryonet:2.22.0-RC1")
    implementation("com.google.guava:guava:11.0.2")

    compileOnly("com.zeriamc:logging:1.0.0")
}

kotlin {
    sourceSets {
        val main by getting
    }
}