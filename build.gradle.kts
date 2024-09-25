plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.kagouniv"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-websocket")

	//springdoc
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.4.0")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.springframework.security:spring-security-test")

	//testContainers
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mysql")

	runtimeOnly("com.mysql:mysql-connector-j")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	developmentOnly("org.springframework.boot:spring-boot-docker-compose")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	archiveBaseName.set("app")
	archiveVersion.set("") // 버전 번호를 제거
	archiveClassifier.set("") // 분류자를 제거
}
tasks.named<Jar>("jar") {
	enabled = false // 기본 jar task를 비활성화
}

