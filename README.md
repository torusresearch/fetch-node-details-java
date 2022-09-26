# Fetch-Node-Details-Java

[![](https://jitpack.io/v/org.torusresearch/fetch-node-details-java.svg)](https://jitpack.io/#org.torusresearch/fetch-node-details-java)

## Introduction

Use this package to fetches details about Torus nodes, from a specified Smart Contract that holds details about the list of nodes & network.
This will dynamically get updates about the node list, allowing the front end to continue querying the different set of nodes after migrations.

This utility library serves to find the endpoints and public keys associated
with the current set of qualified nodes, which are used for key lookups, key
assignments, and key retrievals by other dependent libraries.

README.md
## Features
- Multi network support
- Caching
- Follows kotlin naming conventions
- Exposes underlying web3j implementation
- All API's return `CompletableFutures`

## Getting Started

Typically your application should depend on release versions of fetch-node-details, but you may also use snapshot dependencies for early access to features and fixes, refer to the Snapshot Dependencies section.
This project uses [jitpack](https://jitpack.io/docs/) for release management

Add the relevant dependency to your project:

```groovy
repositories {
        maven { url "https://jitpack.io" }
   }
   dependencies {
         implementation 'org.torusresearch:fetch-node-details-java:3.0.0'
   }
```

## Requirements

- Android - API level 24
- Java 8 / 1.8
