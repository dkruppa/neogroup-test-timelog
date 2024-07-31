FROM openjdk:17
COPY target/timelog-0.0.1-SNAPSHOT.jar timelog-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/timelog-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]
