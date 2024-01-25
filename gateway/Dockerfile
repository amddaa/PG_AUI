FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="lab-gateway"
LABEL org.opencontainers.image.authors="Adam Ślusarek"
LABEL org.opencontainers.image.source=""
LABEL org.opencontainers.image.url=""
LABEL org.opencontainers.image.vendor="Gdańsk University of Technology"
LABEL org.opencontainers.image.version="1.0.0-snapshot"
LABEL org.opencontainers.image.description="AUI lab project"
LABEL org.opencontainers.image.licenses="MIT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="1.0.0-snapshot"

ENV SERVER_PORT=8080

ENV LAB_GATEWAY_HOST=localhost:8080

ENV EUREKA_INSTANCE_INSTANCE_ID=1

ENV SPRING_CONFIG_IMPORT=optional:configserver:http://localhost:8086

EXPOSE 8080

COPY target/lab-gateway-${VERSION}.jar /opt/lab-gateway/lab-gateway.jar

CMD ["java", "-jar", "/opt/lab-gateway/lab-gateway.jar"]
