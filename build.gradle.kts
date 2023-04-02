plugins {
    kotlin("jvm") version "1.8.10"
    application
}

group = "com.zeriamc.networking"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.ghidorah.uk/public")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":packet-library"))

    implementation("com.zeriamc.database:annotation:1.0.0")
    implementation("com.zeriamc.database:database:1.0.0")
    implementation("com.zeriamc:logging:1.0.0")
    implementation("com.zeriamc:misc:1.0.0")

    implementation("com.moshbit:katerbase:0.1.4")
    implementation("org.mongodb:mongodb-driver-sync:4.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("commons-codec:commons-codec:1.15")

    implementation("org.apache.logging.log4j:log4j-api:2.0-beta9")
    implementation("org.apache.logging.log4j:log4j-core:2.0-beta9")
    implementation("com.esotericsoftware:kryonet:2.22.0-RC1")
    implementation("khttp:khttp:1.0.0")
    implementation("com.google.code.gson:gson:2.7")
    implementation("org.json:json:20200518")
}

kotlin {
    sourceSets {
        val main by getting
    }
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClassName = "com.zeriamc.networking.server.ZeriaSocketServer"
}