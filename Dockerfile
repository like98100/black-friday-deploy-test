FROM openjdk:17-slim


WORKDIR /app

COPY  build/libs/blackfriday.jar blackfriday.jar

RUN echo "Copied jar file Check" && ls -la /app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar" ,"blackfriday.jar"]
#ENTRYPOINT ["java", "-jar" ,"blackfriday.jar" , "-Dspring.profiles.active=dev"]