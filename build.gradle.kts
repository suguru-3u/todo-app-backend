plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
	id("idea")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

sourceSets {
	create("integrationTest") {
		java.srcDir("src/integrationTest/kotlin")
		resources.srcDir("src/integrationTest/resources")

		compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
		runtimeClasspath += output + compileClasspath
	}
}

configurations {
	"integrationTestImplementation" {
		extendsFrom(configurations["testImplementation"])
	}
	"integrationTestRuntimeOnly" {
		extendsFrom(configurations["testRuntimeOnly"])
	}
}

// テストフォルダと認識させるために必要
idea {
	module {
		testSources.from(sourceSets["integrationTest"].java.srcDirs)
		testResources.from(sourceSets["integrationTest"].resources.srcDirs)
	}
}

dependencies {
	// SpringのWEBに関する処理の依存関係の追加
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	// JDBC依存関係の追加
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	// Spring Security依存関係の追加
	implementation("org.springframework.boot:spring-boot-starter-security")
 	// Redis依存関係の追加
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.session:spring-session-data-redis")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mysql")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("com.redis:testcontainers-redis")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	// MySQLドライバの依存関係の追加
	runtimeOnly("com.mysql:mysql-connector-j")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register<Test>("integrationTest") {
	description = "Run integration tests"
	group = "verification"

	testClassesDirs = sourceSets["integrationTest"].output.classesDirs
	classpath = sourceSets["integrationTest"].runtimeClasspath

	shouldRunAfter("test")
}

tasks.named<ProcessResources>("processIntegrationTestResources") {
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

