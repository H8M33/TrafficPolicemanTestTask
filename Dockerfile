# Используем официальный образ с OpenJDK
FROM openjdk:17-jdk-alpine

# Копируем jar-файл в контейнер
COPY build/libs/TrafficPoliceman-0.0.1-SNAPSHOT.jar app.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "/app.jar"]
