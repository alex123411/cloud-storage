plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.alext123411"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
dependencies {
    implementation(libs.guava)
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.2")
    implementation("mysql:mysql-connector-java:8.0.33")
    // security
    implementation("org.springframework.boot:spring-boot-starter-security:3.3.0")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server:3.3.2")
//    implementation("org.apache.commons:commons-lang3:3.17.0")
//    runtimeOnly("com.nimbusds:oauth2-oidc-sdk:11.14")
//    compileOnly("javax.servlet:servlet-api:2.5")
    // lombok
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    testCompileOnly("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
