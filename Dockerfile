# Etapa 1: Build da aplicação
FROM gradle:7.6.0-jdk17 AS build
WORKDIR /app

# Copiar apenas arquivos necessários para o build
COPY build.gradle settings.gradle gradlew ./
COPY src ./src
COPY gradle ./gradle

# Build do projeto
RUN ./gradlew clean build --no-daemon -x test

# Etapa 2: Configuração do runtime
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar o JAR gerado na etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Expor a porta 8080
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
