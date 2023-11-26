import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    idea
    id("io.spring.dependency-management")
    id("org.springframework.boot") apply false
    id("io.freefair.lombok") apply false
}

idea {
    project {
        languageLevel = IdeaLanguageLevel(21)
    }
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

allprojects {
    group = "com.javastart"
    version = "1.0-SNAPSHOT"
    repositories {
        mavenLocal()
        mavenCentral()
    }

    val testcontainersBom:  String by project
    val protobufBom:        String by project
    val cloud:              String by project
    val postgres:           String by project
    val sleuth:             String by project
    val hystrix:            String by project

    apply(plugin = "io.spring.dependency-management")

    dependencyManagement {
        dependencies {
            imports {
                mavenBom(SpringBootPlugin.BOM_COORDINATES)
                mavenBom("org.testcontainers:testcontainers-bom:$testcontainersBom")
                mavenBom("com.google.protobuf:protobuf-bom:$protobufBom")
                mavenBom("org.springframework.cloud:spring-cloud-dependencies:$cloud")
            }
            dependency("org.springframework.cloud:spring-cloud-starter-netflix-zuul:$hystrix")
            dependency("org.springframework.cloud:spring-cloud-starter-sleuth:$sleuth")
            dependency("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:$hystrix")
            dependency("org.postgresql:postgresql:$postgres")
        }
    }
    configurations.all{
        resolutionStrategy{
            failOnVersionConflict()
            force("com.netflix.archaius:archaius-core:0.7.7")
            force("commons-configuration:commons-configuration:1.10")
            force("com.google.guava:guava:19.0")
            force("org.hdrhistogram:HdrHistogram:2.1.12")
            force("com.netflix.servo:servo-core:0.12.21")
            force("io.reactivex:rxjava:1.2.2")
            force("org.apache.httpcomponents:httpclient:4.5.13")
            force("com.netflix.netflix-commons:netflix-commons-util:0.3.0")
            force("com.netflix.hystrix:hystrix-core:1.5.18")
            force("jakarta.inject:jakarta.inject-api:2.0.1")
            force("com.fasterxml.woodstox:woodstox-core:6.5.1")
            force("org.glassfish.hk2:hk2-api:3.0.4")
        }
    }

}

subprojects {
    plugins.apply(JavaPlugin::class.java)
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.addAll(listOf("-Xlint:all,-serial,-processing"))
    }
}


