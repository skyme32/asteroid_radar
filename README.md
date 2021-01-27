 Purpose of this Folder

This folder should contain the scaffolded project files to get a student started on their project. This repo will be added to the Classroom for students to use, so please do not have any solutions in this folder.

## Note: Android Kotlin Gradle Update
Use the updated Gradle version in the `~/gradle/wrapper/gradle-wrapper.properties` file:
```
distributionUrl = https\://services.gradle.org/distributions/gradle-6.1.1-all.zip
```

## Note teacher:
```
It's not recommended to leave your personal API access code exposed.

You can use the gradle.properties file, which is in the project root folder and create a new line with a variable to store your access code:

myApiNasa="123456abcdefgh" // Your access code
In the app/build.gradle file, create an alias for the above variable:

android {
    ...
    buildTypes.each {
        it.buildConfigField 'String', 'NASA_API_KEY', 'myApiNasa'
    }
}
In Java, you can retrieve the code as below and then concatenate it in the access URL:

String apiKey = BuildConfig.NASA_API_KEY;
```
