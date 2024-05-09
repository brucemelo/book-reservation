plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.3.6"
    id("io.micronaut.aot") version "4.3.6"
    id("org.jooq.jooq-codegen-gradle") version "3.19.8"
}

version = "1.0"
group = "com.github.brucemelo"

val javaVersion = "21"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.liquibase:micronaut-liquibase")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.sql:micronaut-jooq")
    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.h2database:h2")
    testImplementation("io.micronaut:micronaut-http-client")
    jooqCodegen("org.jooq:jooq-meta-extensions-liquibase:3.19.8")
    annotationProcessor("io.soabase.record-builder:record-builder-processor:41")
    compileOnly("io.soabase.record-builder:record-builder-core:41")
    implementation("io.micronaut.validation:micronaut-validation")
    annotationProcessor("io.micronaut.validation:micronaut-validation-processor")
    implementation("org.mapstruct:mapstruct:1.6.0.Beta1")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0.Beta1")
}

application {
    mainClass = "com.github.brucemelo.bookreservation.Application"
}

java {
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
}

graalvmNative.toolchainDetection = false
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.github.brucemelo.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}

sourceSets {
    main {
        java.srcDirs("build/generated-sources/jooq")
    }
}

tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = javaVersion
}

jooq {
    configuration {
        generator {
            database {
                forcedTypes {
                    forcedType {
                        userType = "com.github.brucemelo.bookreservation.domain.BookStatus"
                        includeExpression = ".*\\.status"
                        converter = "com.github.brucemelo.bookreservation.infrastructure.BookStatusConverter"
                    }
                }
                name = "org.jooq.meta.extensions.liquibase.LiquibaseDatabase"
                target {
                    packageName = "com.github.brucemelo.jooq.generated"
                }
                properties {
                    property {
                        key = "rootPath"
                        value = "${projectDir}/src/main/resources"
                    }
                    property {
                        key = "scripts"
                        value = "db/liquibase-changelog.xml"
                    }
                }
            }
        }
    }
}

tasks.compileJava {
    dependsOn(tasks.jooqCodegen)
}


