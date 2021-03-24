#outdated

FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /
COPY pom.xml /
RUN mvn install
COPY /wait-for-it.sh /

COPY /src /src

CMD ["./wait-for-it.sh", "db:3306","-t", "30", "--", "mvn", "test"]