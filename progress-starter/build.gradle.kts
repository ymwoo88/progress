fun properties(key: String) = (project.findProperty(key) ?: "") as String

plugins {
	id("java-library")
	id("java-test-fixtures")
}

description = "Spring boot starter for HATs"

tasks {
	bootJar {
		enabled = false
	}
	jar {
		enabled = true
	}
}

dependencies {
	// Using only starter
	testRuntimeOnly("com.h2database:h2")

	// Using starter and module
	api("org.springframework.boot:spring-boot-starter-data-rest")
	api("org.springframework.boot:spring-boot-starter-validation")
	api("org.springframework.boot:spring-boot-starter-actuator")
	api("org.springframework.boot:spring-boot-starter-webflux")
	api("org.springframework.boot:spring-boot-starter-security")
	api("org.springframework.boot:spring-boot-starter-data-jpa")
	api("org.springframework.boot:spring-boot-starter-data-redis")
	api("org.springframework.boot:spring-boot-starter-data-elasticsearch")
	api("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	api("org.springframework.boot:spring-boot-starter-cache")
//	api("software.amazon.awssdk:s3")
	api("com.github.ben-manes.caffeine:caffeine")
	api("io.micrometer:micrometer-tracing-bridge-brave")
	api("io.zipkin.reporter2:zipkin-reporter-brave")
//	api("org.apache.commons:commons-collections4")
	api("net.logstash.logback:logstash-logback-encoder:7.4")
	api("io.netty:netty-resolver-dns-native-macos:4.1.94.Final:osx-aarch_64")
	api("io.hypersistence:hypersistence-utils-hibernate-62:3.6.0")

	api("com.querydsl:querydsl-jpa:${dependencyManagement.importedProperties["querydsl.version"]}:jakarta")
	annotationProcessor("com.querydsl:querydsl-apt:${dependencyManagement.importedProperties["querydsl.version"]}:jakarta")// querydsl JPAAnnotationProcessor 사용 지정
	annotationProcessor("jakarta.persistence:jakarta.persistence-api")// java.lang.NoClassDefFoundError(javax.annotation.Entity) 발생 대응
	annotationProcessor("jakarta.annotation:jakarta.annotation-api")// java.lang.NoClassDefFoundError (javax.annotation.Generated) 발생 대응


	// Using test starter and test module
	testFixturesApi("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testFixturesApi("org.springframework.security:spring-security-test")
	testFixturesApi("org.springframework.restdocs:spring-restdocs-mockmvc")
	testFixturesApi("io.github.autoparams:autoparams:${properties("autoParamsVersion")}")
	testFixturesApi("io.github.autoparams:autoparams-lombok:${properties("autoParamsVersion")}")
	testFixturesApi("com.squareup.okhttp3:mockwebserver")
	testFixturesApi("com.epages:restdocs-api-spec-mockmvc:0.19.0")
}
