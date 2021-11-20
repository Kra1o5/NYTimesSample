plugins {
    id ("java-library")
    id ("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    Libs.kotlinLibs.forEach { kotlinLibs ->
        implementation(kotlinLibs)
    }
    Libs.testLibs.forEach { testLibs ->
        testImplementation(testLibs)
    }
}