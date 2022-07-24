# `com.tomuvak.testing-coroutines` – a multi-platform Kotlin library with a utility to run async tests
This library is licensed under the [MIT License](https://en.wikipedia.org/wiki/MIT_License);
see [LICENSE.txt](LICENSE.txt).

## Table of contents
* [Rationale](#rationale)
* [How to use `com.tomuvak.testing-coroutines`](#how-to-use-comtomuvaktesting-coroutines)
  * [Including this library in a Kotlin project](#including-this-library-in-a-kotlin-project)
  * [Using the functionality in code](#using-the-functionality-in-code)

## Rationale
One of the nice things about multi-platform Kotlin is that ideally a single version of a piece of code can be used
without change on different platforms. Unfortunately, (currently) there are still cases where it seems this goal could
theoretically be achieved, but for some reason Kotlin and its standard library do not provide a way to do so.

One of these cases is [kotlinx.coroutines.runBlocking][1], which is not supported in Kotlin JS.

This library tries to fill in that gap, specifically for the use case of test functions which make use of coroutines /
async functionality.

## How to use `com.tomuvak.testing-coroutines`

### Including this library in a Kotlin project
To add the library from
[GitHub Packages](https://docs.github.com/en/packages/learn-github-packages/introduction-to-github-packages), a
reference to this repository's GitHub Packages
[Maven repository](https://maven.apache.org/guides/introduction/introduction-to-repositories.html) needs to be added
inside the `repositories { ... }` block in the project's `build.gradle.kts` file:

```kotlin
    maven {
        url = uri("https://maven.pkg.github.com/tomuvak/testing-coroutines")
        credentials { // See note below
            username = "<GitHub user name>"
            password = "<GitHub personal access token>"
        }
    }
```

and the dependency should be declared for the relevant source set(s) inside the relevant `dependencies { ... }` block(s)
inside the `sourceSet { ... }` block, e.g.

```kotlin
        val commonTest by getting {
            dependencies {
                implementation("com.tomuvak.testing-coroutines:testing-coroutines:0.0.1")
            }
        }
```

to add it for the test source sets on all platforms in a multi-platform project.

Note about credentials: it seems that even though this repository is public and everyone can download this library from
GitHub Packages, one still needs to supply credentials for some reason. Any GitHub user should work, when provided with
a [personal access
token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)
for the user with (at least) the `read:packages` scope.

**You might want to keep the credentials private**, for example in case the GitHub user has access to private packages
(as GitHub personal access tokens can be restricted in the type of operations they're used for, but not in the
repositories they can access), or all the more so in case the token has a wider scope (and note also that one can change
a token's scope after its creation, so it's possible that at some future point the user might inadvertently grant a
token which was meant to be restricted more rights).

See this library's own [Gradle script](build.gradle.kts) for an example of one way this could be done by means of
storing private information in a local file which is not source-controlled. In this case the file – which is Git-ignored
– is called `local.properties`, and it includes lines like the following:

```properties
githubUser=<user name>
githubToken=<personal access token for the user above, with the `read:packages` scope>
```

### Using the functionality in code
In test code, simply `import com.tomuvak.testing.coroutines.asyncTest`, and wrap any test which makes use of coroutines
/ async functionality in a call to `asyncTest { }`.

```kotlin
import com.tomuvak.testing.coroutines.asyncTest
import kotlin.test.Test

class SomeTestSuite {
    @Test fun `a normal test function`() {
        // "Normal" (= synchronous) test code here
    }

    @Test fun `an async test function`() = asyncTest { // Note use of asyncTest
        // The code here can call suspended functions and make use of CoroutineScope.
    }
}
```

The above code can be written in the same form no matter the platform.

[1]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/run-blocking.html
