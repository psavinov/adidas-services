FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
COPY AdidasService.mv.db /
ENTRYPOINT ["java","-cp","app:app/lib/*","-Dspring.profiles.active=docker","com.adidas.service.review.ReviewServiceApplication"]