plugins {
    id("java")
    id("org.springframework.boot")
    id("io.freefair.lombok")
}

dependencies {

    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter")
    implementation("org.springframework.cloud:spring-cloud-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
}
tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "com.javastart.gateway.GatewayApplication"
        )
    }
}
