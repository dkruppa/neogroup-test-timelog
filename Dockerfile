FROM openjdk:17
COPY target/timelog-0.0.1.jar timelog-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/timelog-0.0.1.jar", "--spring.profiles.active=docker"]
