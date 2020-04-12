FROM openjdk:11

COPY . /app
WORKDIR /app
RUN ./mvnw install

ENTRYPOINT ["/app/mvnw"]
