import com.google.protobuf.gradle.*

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.allopen") version "1.9.25"
    id("com.google.protobuf") version "0.9.5"
}

val springGrpcVersion by extra("0.11.0")

group = "com.zoroxnekko"
version = "0.0.1-SNAPSHOT"
description = "billing-service"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    if (JavaVersion.current().isJava9Compatible()) {
        implementation("javax.annotation:javax.annotation-api:1.3.1")
    }
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.grpc:grpc-netty-shaded:1.75.0")
    implementation("io.grpc:grpc-protobuf:1.75.0")
    implementation("io.grpc:grpc-stub:1.75.0")
    implementation("org.springframework.grpc:spring-grpc-spring-boot-starter")
    implementation("com.google.protobuf:protobuf-java:4.32.1")
    compileOnly("org.apache.tomcat:tomcat-annotations-api:11.0.11")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.grpc:spring-grpc-dependencies:$springGrpcVersion")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.32.1"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.75.0"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc") { }
            }
        }
    }
}
