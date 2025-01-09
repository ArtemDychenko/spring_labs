FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="aui-movie-management"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"
LABEL org.opencontainers.image.description="Movie elements in director category"
LABEL org.opencontainers.image.licenses="MIT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:mysql://aui_lab5_js-aui-movie-db-1:3306/movie_db
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=com.mysql.cj.jdbc.Driver
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=password

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.MySQLDialect
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

EXPOSE 8080

COPY target/aui-movie-management-${VERSION}.jar /opt/aui-movie-management/aui-movie-management.jar

CMD ["java", "-jar", "/opt/aui-movie-management/aui-movie-management.jar"]
