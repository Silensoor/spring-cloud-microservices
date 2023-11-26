rootProject.name = "spring-cloud-microservices"
include("account-service")
include("bill-service")
include("config-service")
include("deposit-service")
include("gateway")
include("notification-service")
include("registry")

pluginManagement {
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val freefair: String by settings

    plugins {
        id("org.springframework.boot") version springframeworkBoot
        id("io.spring.dependency-management") version dependencyManagement
        id("io.freefair.lombok") version freefair
    }
}
