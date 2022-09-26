# Distributed Cache Example Application For JBoss EAP

This is a simple application and test harness for putting some load on a distributed caching implementation with JBoss EAP. The example application creates a session for each new client and stores some data in that session cache. The testing harness creates a number of parallel clients to ensure that it creates a number of separate sessions with different data. The testing harness allows for some sessions to be exercised every few seconds, others every few minutes, and finally after the intended session timeout.

## Build the Enterprise Java Application

```bash
cd example-distributed-cache-application
./mvnw cleam compile package
```

Deploy the application to a JBoss EAP 7.x server (or cluster)

## Build and run the test harness

```bash
cd automated-tests
./mvnw clean compile package
java -jar ./target/performance-testing-automation-1.0.0-SNAPSHOT-jar-with-dependencies.jar -s server1,server2 -t <numThreads>
```