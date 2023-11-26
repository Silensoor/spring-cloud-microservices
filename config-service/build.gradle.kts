plugins {
    id("java")
    id("org.springframework.boot")
    id("io.freefair.lombok")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.cloud:spring-cloud-config-server")
}
tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "com.javastart.config.ConfigApplication"
        )
    }
}
