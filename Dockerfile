FROM openjdk:10.0.2-13-jre-sid

COPY target/news-service.jar /app/news-service.jar

CMD ["java", "-jar", "/app/news-service.jar"]
