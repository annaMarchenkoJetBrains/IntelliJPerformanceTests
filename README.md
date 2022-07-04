# IntelliJPerformanceTests

## How do tests work

In order to write integration tests on different JetBrains products we use the library 
**com.jetbrains.intellij.ide:ide-starter**

The library downloads the last newest EAP and runs tests on it.

The most important entities in the library are:

**IDETestContext** - which is a downloaded and prepared to be run IDE

**TestCases** - a test case defines which product (IDE) you will use in your test and which project will be opened when IDE will be started in your test

**JUnit4StarterRule** - allows to run your test on the already downloaded IDE (which should be placed under *out/perf-startup/installers/<product_code>*)

The most important methods in the library are:

**initializeTestRunner(testName: String, testCase: TestCase)** 
which creates an object of IDETestContext with all needed for IDE directories (***system*** dir, ***config*** dir, ***plugins*** dir and ***logs*** dir).

You can call some really useful methods on this object in order to set up the IDE properly (like f.ex. setProfiler method)

**runIDE()**
which creates a test script from commands which you passes to the method, runs IDE, executes these commands and kill IDE when the time defined in timeout is over and IDE is still alive


## Tests' artifacts

Tests' artifacts are stored under ***out/perf-startup/tests*** directory

Downloaded installers are stored under ***out/perf-startup/installers*** directory

Downloaded plugins, JBRs, projects and unpacked builds are stored under ***out/perf-startup/cache*** directory

## Running tests
You can try our framework by running the test ***IntegrationPerformanceTests.communitySourcesIndexing***

## How to use another project in tests
You can either put the project under ***projectsForTests*** or set a path to your project when you create an object of *TestCases* classes

You can also download your project from any source. 