# UQPAY Android Host-UI Demo

UQPAY Host-UI is a readymade UI that allows you to accept card in your app.

## Info
for this moment the the Host-UI SDK of Android is snapshot version. but the business logic is stable.
before the stable version will only update the UI & UX

## Testing it in your project

Set the maven snapshot repository (for the snapshot version we only upload to maven central snapshot repository )
```groovy
allprojects {
    repositories {
        maven {
            url 'https://oss.sonatype.org/content/repositories/snapshots/'
        }
    }
}
```

Add the dependency in your `build.gradle`:
```groovy
dependencies {
    implementation 'com.uqpay.sdk:host-ui:1.1.0-SNAPSHOT'
}
```

## Usage

You can read the code on `MainActivity.kt` of this demo project

## Helps
* [Read the docs](https://developer.uqpay.com/api/#/)
* Find a bug or any feedback? [Open an issue](https://github.com/uqpaytechnology/host-ui-android-demo/issues)