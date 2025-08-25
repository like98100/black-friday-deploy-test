FROM openjdk:17-slim

# curl 설치 (헬스체크용)
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY  build/libs/blackfriday.jar blackfriday.jar

RUN echo "Copied jar file Check" && ls -la /app/

EXPOSE 8080

#ENTRYPOINT ["java", "-jar" ,"blackfriday.jar"]
ENTRYPOINT ["java", "-jar" ,"blackfriday.jar" , "--spring.profiles.active=dev"]