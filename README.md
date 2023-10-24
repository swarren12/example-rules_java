# Example Project using `rules_java`

This is a simple project showing that the current version of `rules_java` does not seem to play nicely with certain settings.

The project should be configured to build on a Java 17 JDK, as specified by the following options in `.bazelrc`:

```
build --java_language_version=17
build --tool_java_language_version=17
build --java_runtime_version=remotejdk_17
build --tool_java_runtime_version=remotejdk_17
```

However, the project is also using Java 17 preview features.
As a result, it fails to compile with:

```
ERROR: BUILD.bazel:4:13: Building libexample.jar (1 source file) failed: (Exit 1): java failed: error executing command (from target //:example) external/rules_java~7.0.6~toolchains~remotejdk21_linux/bin/java '--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED' ... (remaining 19 arguments skipped)
warning: [options] system modules path not set in conjunction with -source 17
src/main/java/com/example/Main.java:10: error: patterns in switch statements are not supported in -source 17
            case Bird b -> System.out.println("Random object was a Bird");
                 ^
  (use -source 21 or higher to enable patterns in switch statements)
```

Attempting to enable preview features via the `--enable-preview` option results in a different failure:

```
ERROR: BUILD.bazel:4:13: Building libexample.jar (1 source file) failed: (Exit 1): java failed: error executing command (from target //:example) external/rules_java~7.0.6~toolchains~remotejdk21_linux/bin/java '--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED' ... (remaining 19 arguments skipped)
error: invalid source release 17 with --enable-preview
  (preview language features are only supported for release 21)
```

There does not appear to be clear documentation around how to force the use of a specific JDK outside of these options.

