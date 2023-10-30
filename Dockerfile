# syntax=docker/dockerfile:experimental
FROM amazoncorretto:21 as build

WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
RUN mkdir -p target/extracted && java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted

FROM amazoncorretto:21
LABEL maintainer="VICYALA82 <victor.ayalamarin@gmail.com>"
VOLUME /tmp
ARG EXTRACTED=/workspace/app/target/extracted
COPY --from=build ${EXTRACTED}/application/ ./
COPY --from=build ${EXTRACTED}/dependencies/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
ENV APP_NAME "assets-app"

ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]