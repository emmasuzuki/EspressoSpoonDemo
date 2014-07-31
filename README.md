EspressoSpoonDemo
=================

This is a demo project to show how spoon works with espresso.

##Spoon
Test runner and reporting tool which runs instrumentation tests.
Spoon runs tests on all devices detected by adb.
After the tests complete, spoon generates easy, meaningful summary in HTML.

More about Spoon: http://square.github.io/spoon/

##Espresso
I took Espresso as my instrumentation tests.
Espresso is light-weight instrumentation tests and FAST ! 

##Setup
`build.gradle`

1. Add classpath to build script dependencies
```
dependencies {
  classpath 'com.stanfy.spoon:spoon-gradle-plugin:0.10.0'
}
```

2. Add dependencies
```
androidTestCompile 'com.jakewharton.espresso:espresso:1.1-r3'
androidTestCompile 'com.squareup.spoon:spoon-client:1.1.0'
```

3. Add `testInstrumentationRunner "com.google.android.apps.common.testing.testrunner.GoogleInstrumentationTestRunner"` to android defaultConfig
 
4. 
