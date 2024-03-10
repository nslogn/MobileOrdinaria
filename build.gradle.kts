// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    //val roomVersion = "2.6.1"
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    //id("androidx.room") version roomVersion apply false
    //kotlin("jvm") version "1.9.21" apply false
}

buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.9.22"))
    }
}