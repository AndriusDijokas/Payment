FROM gradle:7.2.0-jdk17 as builder
WORKDIR /var/lib/build
ADD ./ ./

RUN gradle build -x test


FROM openjdk:17-alpine

RUN apk add --no-cache bash

EXPOSE 8080

COPY --from=builder /var/lib/build/build/libs/Payment-0.0.1-SNAPSHOT.jar /Payment.jar

COPY wait-for-it.sh .
COPY docker-entrypoint.sh .
RUN chmod +x ./wait-for-it.sh ./docker-entrypoint.sh

ENTRYPOINT ["./docker-entrypoint.sh"]
CMD ["java", "-jar","/Payment.jar"]