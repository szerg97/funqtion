FROM openjdk:20

WORKDIR /app
COPY target/funqtion-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "funqtion-0.0.1-SNAPSHOT.jar"]