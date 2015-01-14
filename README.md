EspressoSpoonDemo
=================

This is a demo project to show how spoon works with espresso.

<img src="https://raw.githubusercontent.com/emmasuzuki/EspressoSpoonDemo/master/demo1.png" width="500">

<img src="https://raw.githubusercontent.com/emmasuzuki/EspressoSpoonDemo/master/demo2.png" width="500">

##Spoon
Test runner and reporting tool which runs instrumentation tests.
Spoon runs tests on all devices detected by adb.
After the tests complete, spoon generates easy, meaningful summary in HTML.

More about Spoon: http://square.github.io/spoon/

##Espresso
I took Espresso as my instrumentation tests.
Espresso is light-weight instrumentation tests and FAST ! 

More about Espresso: https://code.google.com/p/android-test-kit/wiki/Espresso

##Setup
`app/build.gradle`

1. Add classpath to build script dependencies
  ```
  dependencies {
    classpath 'com.stanfy.spoon:spoon-gradle-plugin:0.14.1'
  }
  ```
  
2. Add dependencies
  ```
  androidTestCompile 'com.android.support.test.espresso:espresso-core:2.0'
  androidTestCompile 'com.squareup.spoon:spoon-client:1.1.1'
  ```

3. Add testInstrumentationRunner to defaultConfig
  ```
  testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
  ```

4. Add these lines to fix duplicate files issue
  ```
  packagingOptions {
    exclude 'LICENSE.txt'
  }
  ```

5. Add spoon block
  ```
  spoon {
    debug = true;
  }
  ```

##Write test with Espresso
  ```
  public void testSetMismatchError() {
    // Make screenshot before performance
    Spoon.screenshot(getActivity(), "initial_state");
  
    // Setup
    onView(withId(R.id.email)).perform(typeText("espresso@spoon.com\n"));
    onView(withId(R.id.password)).perform(typeText("lemoncake\n"));
  
    // Action
    onView(withId(R.id.submit)).perform(click());
  
    // Make screenshot after performance
    Spoon.screenshot(getActivity(), "after_state");    
  
    // Test
    onView(withText(R.string.msg_mismatch)).check(matches(isDisplayed()));
  }
  ```

##Run
It's Simple ! Plug-in all devices you want to test or open GenyMotion emulators and execute

`$ ./gradlew spoon`


After run, spoon generates spoon folder in app/build directory.
You can open index.html and see the result.

https://cdn.rawgit.com/emmasuzuki/EspressoSpoonDemo/master/app/build/spoon/debug/index.html

Also this page is dynamic so you can click on red bar to see details.

##Jacoco
Jacoco is a code coverage for Java.
It includes following analysis:
- Line coverage
- Method coverage
- Branch coverage
- Block coverage

Spoon and Jacoco are both reporting tools. Spoon is more like a dev friendly output to find what test is failing and why.  While Jacoco is coverage tool where it shows which lines are tested and which are not plus overall metrics of how much your code is tested.

See in action:
https://cdn.rawgit.com/emmasuzuki/EspressoSpoonDemo/master/app/build/outputs/reports/coverage/debug/index.html

Setup:
`app/build.gradle`

1. Enable coverage

    ```
    buildTypes {
      debug {
          testCoverageEnabled true
      }
    }
   ```
   
2. Add Jacoco in android block

    ```
    jacoco {
      version "0.7.1.201405082137"
    }
    ```
    
3. Define source directory

    ```
    def coverageSourceDirs = [
      './src/androidTest/java'
    ]
    ```
    
4. Add Jacoco task

    ```
    task jacocoTestReport(type: JacocoReport, dependsOn: "connectedAndroidTest") {
      group = "Reporting"
      description = "Generates Jacoco coverage reports"
      reports {
        xml.enabled = true
        html.enabled = true
      }
      classDirectories = fileTree(
        dir: 'build/intermediates/classes',
        excludes: ['**/R.class',
                   '**/R$*.class',
                   '**/BuildConfig.*',
                   '**/Manifest*.*'
        ]
      )
      sourceDirectories = files(coverageSourceDirs)
      additionalSourceDirs = files(coverageSourceDirs)
      executionData = files('build/jacoco/connectedAndroidTest.exec')
    }
    ```

Run

`$./gradlew jacocoTestReport`

After run the command, files are generated at `app/build/outputs/reports/coverage/`.  Open index.html and see your report.

##Any Questions ? 
Please feel free to contact me at emma11suzuki@gmail.com
