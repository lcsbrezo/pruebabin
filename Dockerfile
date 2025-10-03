# Stage 1: Construir el JAR con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos los archivos de proyecto al contenedor
COPY pom.xml .
COPY src ./src

# Construimos el proyecto y generamos el JAR
RUN mvn clean package -DskipTests

# Stage 2: Crear imagen ligera con el JAR
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiamos el JAR desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto de Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]