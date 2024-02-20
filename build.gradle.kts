fun properties(key: String) = (project.findProperty(key) ?: "") as String

plugins {
	id("java")
	id("org.springframework.boot") version "3.2.2" apply false
	id("io.spring.dependency-management") version "1.1.4"
}

subprojects {
	apply(plugin = "java")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	group = "com.woo.progress"
	java.sourceCompatibility = JavaVersion.VERSION_17

	tasks {
		jar {
			enabled = false
		}
	}

	configurations {
		all {
			resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
		}
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}

	repositories {
		mavenCentral()
		maven { url = uri("https://repo.spring.io/milestone") }
		maven { url = uri("https://repo.spring.io/snapshot") }
	}

	dependencies {
		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")
		testCompileOnly("org.projectlombok:lombok")
		testAnnotationProcessor("org.projectlombok:lombok")

		// "@ConfigurationProperties"를 사용하기 위한 의존성
		annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	}

	tasks.named("compileJava") {
		inputs.files(tasks.named("processResources"))
	}

	// generated 파일 위치를 src/main/generated 로 고정
	tasks.withType<JavaCompile>().configureEach {
		options.generatedSourceOutputDirectory.set(file("$projectDir/src/main/generated/"))
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.withType<Wrapper> {
		gradleVersion = properties("gradleVersion")
	}

	tasks.named("clean") {
		doLast {
			// clean-up directory when necessary
			file("$projectDir/src/main/generated/").deleteRecursively()
			file("$projectDir/src/test/generated_tests/").deleteRecursively()
			file("$projectDir/out").deleteRecursively()
		}
	}
}
