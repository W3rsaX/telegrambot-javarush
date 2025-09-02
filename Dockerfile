FROM openjdk:21
WORKDIR /app
ARG JAR_FILE=target/*.jar
ENV BOT_TOKEN=8369059576:AAG0zzeJk9ifIDY31iKD40h8MxtYC0fBCYw
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]