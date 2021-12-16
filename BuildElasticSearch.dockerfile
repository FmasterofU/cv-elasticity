FROM openjdk:13.0.1-jdk-buster as build
# build does not work, using assemble; reason: https://github.com/elastic/elasticsearch/issues/39855#issuecomment-471427324
RUN apt update && apt install git -y && \
    git clone https://github.com/chenejac/udd06 && \
    cd udd06 && ./gradlew clean assemble && \
    cp build/distributions/serbian-analyzer-1.0-SNAPSHOT.zip /serbian-analyzer-1.0-SNAPSHOT.zip

FROM docker.elastic.co/elasticsearch/elasticsearch:7.4.0 as image
COPY --from=build /serbian-analyzer-1.0-SNAPSHOT.zip /serbian-analyzer-1.0-SNAPSHOT.zip
RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install file:///serbian-analyzer-1.0-SNAPSHOT.zip