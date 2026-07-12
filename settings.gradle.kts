pluginManagement {
	repositories {
		maven("https://maven.neoforged.net/releases/") { name = "NeoForged" }
		mavenCentral()
		gradlePluginPortal()
	}
}

plugins {
	// Lets Gradle auto-provision the JDK required by gradle/gradle-daemon-jvm.properties
	id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "GodMode"
