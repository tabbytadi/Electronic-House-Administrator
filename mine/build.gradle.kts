plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate:hibernate-core:5.6.15.Final")
    implementation("mysql:mysql-connector-java:8.0.28")
    implementation("org.hibernate.validator:hibernate-validator:6.0.16.Final")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.glassfish:javax.el:3.0.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}


tasks.test {
    useJUnitPlatform()
}