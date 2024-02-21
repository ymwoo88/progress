import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun properties(key: String) = (project.findProperty(key) ?: "") as String

fun getVersionName(): String {
	return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy.MM.dd.HHmmss"))
}

fun getRestDocsUrl(): String {
	return when(properties("profile")) {
		"docker" -> "http://docker-hats.hanatour.com"
		"dev" -> "http://dev-hats.hanatour.com"
		"qa" -> "http://qa-hats.hanatour.com"
		"prod" -> "http://hats.hanatour.com"
		else -> "http://localhost:8080"
	}
}

plugins {
	id("com.epages.restdocs-api-spec") version "0.19.0"
}

description = "progress api"

apply(plugin = "com.epages.restdocs-api-spec")

dependencies {
	implementation(project(":progress-starter"))
	runtimeOnly("com.h2database:h2")

	annotationProcessor("com.querydsl:querydsl-apt:${dependencyManagement.importedProperties["querydsl.version"]}:jakarta")// querydsl JPAAnnotationProcessor 사용 지정
	annotationProcessor("jakarta.persistence:jakarta.persistence-api")// java.lang.NoClassDefFoundError(javax.annotation.Entity) 발생 대응
	annotationProcessor("jakarta.annotation:jakarta.annotation-api")// java.lang.NoClassDefFoundError (javax.annotation.Generated) 발생 대응

	// test
	testImplementation(testFixtures(project(":progress-starter")))
}


tasks {
	val buildApiSpecDir = "build/api-spec"
	val resourceApiSpecDir = "../resources/api-spec"

	create<Copy>("copyApiSpec") {
		from(buildApiSpecDir)
		into(resourceApiSpecDir)
	}

	clean {
		doLast {
			delete(buildApiSpecDir)
			fileTree(resourceApiSpecDir) {
				include("**/${project.name}*.*")
			}.forEach { it.delete() }
		}
	}
}
