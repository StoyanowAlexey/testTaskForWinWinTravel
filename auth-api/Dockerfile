FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
# Копіюємо jar файл, який ти скомпілював через Maven
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]