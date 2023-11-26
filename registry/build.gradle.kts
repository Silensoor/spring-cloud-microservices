plugins {
    id("java")
    id("org.springframework.boot")
    id("io.freefair.lombok")
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
}
tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "com.javastart.registry.RegistryApplication"
        )
    }
}
