# Vegan Realm

## Requirements

Vegan Realm is in Java, it runs on the JVM. We recommend using any version of the JDK from 11 and up. You can consult the [whichjdk](https://whichjdk.com/) website to decide which version to use.

## Getting Started

1. Download dependencies and create the SQLite database:

`./gradlew dbMigrate`

2. Start the server:

`./gradlew run'`

## Search

The [queries.sql](https://github.com/codebards/veganrealm/blob/main/src/queries.sql) file can be used to create the SQLite virtual table required for searching recipes.