## Build stage
FROM gradle:jdk17-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

## Package stage
FROM openjdk:17-jdk-slim

# Set working dir
WORKDIR /app

EXPOSE 8080

# Copy builded jar
COPY --from=build /home/gradle/src/build/libs/izischool-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
