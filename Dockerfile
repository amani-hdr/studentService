# Étape 1 : Construire l'application avec Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build [cite: 67]
WORKDIR /app [cite: 68]
COPY pom.xml . [cite: 69]
COPY src ./src [cite: 70]
RUN mvn clean package -DskipTests [cite: 71]

# Étape 2 : Créer une image d'exécution légère
FROM eclipse-temurin:17-jdk [cite: 73]
WORKDIR /app [cite: 74]
# Copie du JAR depuis l'étape de construction
COPY --from=build /app/target/*.jar app.jar [cite: 75] 
EXPOSE 8080 [cite: 75]
CMD ["java", "-jar", "app.jar"] [cite: 76]