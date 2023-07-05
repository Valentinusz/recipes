plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.flywaydb.flyway") version "9.8.1"
	id("io.freefair.lombok") version "8.0.1"
}

group = "com.valentinusz"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.flywaydb:flyway-core:9.19.4") // https://mvnrepository.com/artifact/org.flywaydb/flyway-core
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-security")
//	implementation("org.springframework.boot:spring-boot-starter-oauth2-authorization-server")
//	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("com.github.javafaker:javafaker:1.0.2")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.xerial:sqlite-jdbc:3.40.1.0")
	implementation("org.hibernate.orm:hibernate-community-dialects:6.2.4.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework:spring-webflux")
	testImplementation("org.springframework.graphql:spring-graphql-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
