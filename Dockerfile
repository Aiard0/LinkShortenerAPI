### Build and setup
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy pom.xml and cache Maven dependencies
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copy all source code
COPY . .

# Build the app without running tests
RUN mvn -B clean package -DskipTests

### Image
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the default port for the application
EXPOSE 8080

### Run
ENTRYPOINT ["java", "-jar", "app.jar"]