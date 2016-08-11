buildscript {

    repositories {
        gradleScriptKotlin()
        mavenCentral()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin"))
    }
}

apply {
    plugin("kotlin")
    plugin<ApplicationPlugin>()
}

configure<ApplicationPluginConvention> {
    mainClassName = "samples.HelloWorldKt"
    group = "com.cout970.engine"
    version = "0.0.0"
}

repositories {
    gradleScriptKotlin()
    mavenCentral()
}

val lwjgl_version = "3.0.0"
val joml_version = "1.8.1"
val log4j_version = "1.2.17"

dependencies {
    compile(kotlinModule("stdlib"))

    //https://github.com/LWJGL/lwjgl3
    compile(group = "org.lwjgl", name = "lwjgl",  version = lwjgl_version)
    compile(group = "org.lwjgl", name = "lwjgl-platform", version = lwjgl_version, classifier = "natives-windows")
    compile(group = "org.lwjgl", name = "lwjgl-platform", version = lwjgl_version, classifier = "natives-linux")
    compile(group = "org.lwjgl", name = "lwjgl-platform", version = lwjgl_version, classifier = "natives-osx")

    // https://mvnrepository.com/artifact/org.joml/joml
    compile(group = "org.joml", name = "joml", version = joml_version)

    // https://mvnrepository.com/artifact/log4j/log4j
    compile(group = "log4j", name = "log4j", version = log4j_version)
}
