# assertj-reflection

[![Maven Central Version][maven-central-badge]][maven-central]
[![Javadoc][javadoc-badge]][javadoc]
[![Coverage Status][coveralls-badge]][coveralls]

An extension to [AssertJ] that provides assertion methods using reflection.

## Usage

Check out the [examples directory](./examples) to see how this library can be used.
All available assertions can be found in the project's [javadoc].

## Installation

### Gradle

To install `assertj-reflection` using Gradle, add it to the list of dependencies in your project's `build.gradle`:

```groovy
dependencies {
    testImplementation 'io.github.sanderploegsma:assertj-reflection:0.1.0'
}
```

### Maven

To install `assertj-reflection` using Maven, add it to the list of dependencies in your project's `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>io.github.sanderploegsma</groupId>
        <artifactId>assertj-reflection</artifactId>
        <version>0.1.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

[AssertJ]: https://github.com/assertj/assertj
[coveralls]: https://coveralls.io/github/sanderploegsma/assertj-reflection?branch=main
[coveralls-badge]: https://coveralls.io/repos/github/sanderploegsma/assertj-reflection/badge.svg?branch=main
[javadoc]: https://sanderploegsma.github.io/assertj-reflection/
[javadoc-badge]: https://img.shields.io/badge/docs-javadoc-blue
[maven-central]: https://central.sonatype.com/artifact/io.github.sanderploegsma/assertj-reflection
[maven-central-badge]: https://img.shields.io/maven-central/v/io.github.sanderploegsma/assertj-reflection
