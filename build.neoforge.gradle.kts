plugins {
	id("org.jetbrains.kotlin.jvm") version "2.4.0" // must match the Kotlin bundled by Kotlin for Forge
	id("net.neoforged.moddev") version "2.0.141"
}

val javaVersion = JavaVersion.VERSION_25
val mcVersionRangeForNeoForge: String = sc.properties["mod.mc_compat"]

version = "${property("mod.version")}+${sc.current.version}"
group = property("mod.group").toString()

base {
	archivesName = "${property("mod.id")}-neoforge"
}

repositories {
	maven("https://thedarkcolour.github.io/KotlinForForge/") { name = "KotlinForForge" }
}

dependencies {
	implementation("thedarkcolour:kotlinforforge-neoforge:${property("deps.kotlin_forge")}")
}

neoForge {
	version = sc.properties["deps.neo_loader"]

	mods {
		register("godmode") {
			sourceSet(sourceSets.main.get())
		}
	}

	runs {
		register("client") {
			client()
			gameDirectory = rootProject.file("run")
		}
		register("server") {
			server()
			gameDirectory = rootProject.file("run")
		}
	}
}

tasks {
	processResources {
		inputs.property("minecraftVersionRange", mcVersionRangeForNeoForge)
		inputs.property("version", project.version)

		filesMatching("META-INF/neoforge.mods.toml") {
			expand(mapOf(
				"minecraftVersionRange" to inputs.properties["minecraftVersionRange"],
				"version" to inputs.properties["version"],
			))
		}

		exclude("fabric.mod.json")
	}

	named("createMinecraftArtifacts") {
		dependsOn("stonecutterGenerate")
	}
}

tasks.withType<JavaCompile>().configureEach {
	options.release.set(javaVersion.majorVersion.toInt())
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
	compilerOptions {
		jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(javaVersion.majorVersion)
	}
}

java {
	sourceCompatibility = javaVersion
	targetCompatibility = javaVersion

	toolchain {
		languageVersion = JavaLanguageVersion.of(javaVersion.majorVersion)
	}
}
