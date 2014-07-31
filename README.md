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

More about Espresso: https://code.google.com/p/android-test-kit/wiki/Espresso

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

3. Add testInstrumentationRunner to defaultConfig
  `testInstrumentationRunner "com.google.android.apps.common.testing.testrunner.GoogleInstrumentationTestRunner"`

4. Add these lines to fix duplicate files issue
  ```
  packagingOptions {
    exclude 'LICENSE.txt'
  }
  ```

5. Add spoon blick
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

`$ gradle spoon`


After run, spoon generates spoon folder in app/build directory.
You can open index.html and see the result.

https://cdn.rawgit.com/emmasuzuki/EspressoSpoonDemo/master/app/build/spoon/debug/index.html

Also this page is dynamic so you can click on red bar to see details.


## Any Questions ? 
Please feel free to contact me at emma11suzuki@gmail.com
