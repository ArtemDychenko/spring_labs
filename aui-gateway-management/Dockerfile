FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="aui-gateway-management"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"
LABEL org.opencontainers.image.description="Movie viewer, gateway module."
LABEL org.opencontainers.image.licenses="MIT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:aui-gateway-management
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENV SPRING_H2_CONSOLE_enabled=true

ENV RPG_GATEWAY_HOST=localhost:8080
ENV RPG_MOVIE_URL=http://localhost:8090
ENV RPG_DIRECTOR_URL=http://localhost:8091

EXPOSE 8080

COPY target/aui-gateway-management-${VERSION}.jar /opt/aui-gateway-management/aui-gateway-management.jar

CMD ["java", "-jar", "/opt/aui-gateway-management/aui-gateway-management.jar"]
