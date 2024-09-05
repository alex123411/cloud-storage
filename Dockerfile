# syntax=docker/dockerfile:1

FROM eclipse-temurin:17

WORKDIR /app

COPY build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]

#FROM eclipse-temurin:17-jdk-jammy
#WORKDIR /app
#
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} app.jar
#COPY src ./src
#
#CMD ["java", "-jar", "app.jar"]

##EXPOSE 8080
#
#FROM base as development
#CMD ["./gradlew", "clean", "bootJar", "spring-boot:run", "-Dspring-boot.run.profiles=mysql",
#"-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]
#
#FROM base as build
#RUN ./mvnw package
#
#FROM eclipse-temurin:17-jre-jammy as production
#EXPOSE 8080
#COPY --from=build /app/target/spring-petclinic-*.jar /spring-petclinic.jar
#CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/spring-petclinic.jar"]
