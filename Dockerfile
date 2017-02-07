FROM maven:3.3.9-jdk-8

COPY pom.xml /app/
COPY src/** /app/src/

WORKDIR /app

RUN mvn install

CMD ["mvn", "exec:java"]
