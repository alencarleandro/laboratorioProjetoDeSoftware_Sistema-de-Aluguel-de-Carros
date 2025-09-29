# Stage 1: Build da aplicação
FROM eclipse-temurin:21-jdk-alpine AS build

# Diretório de trabalho
WORKDIR /app

# Copia apenas os arquivos necessários para o build
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Dá permissão para o wrapper do Gradle
RUN chmod +x ./gradlew

# Build da aplicação (gera o JAR)
RUN ./gradlew build -x test

# Stage 2: Container final
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia apenas o JAR gerado do stage anterior
COPY --from=build /app/build/libs/aluguel-carros-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
