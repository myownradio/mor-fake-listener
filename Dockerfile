FROM maven:3.3.9-jdk-8

COPY . /app

WORKDIR /app

RUN mvn install

CMD ["mvn", "exec:java"]
