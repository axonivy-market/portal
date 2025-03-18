FROM maven:3.9.8-eclipse-temurin-21

# Create a group and user with the specified IDs
RUN apt-get update && apt-get install -y --no-install-recommends \
    passwd adduser\
    && apt-get install -y openssh-client\
    && addgroup --gid 1001 build && adduser --uid 1001 --gid 1001 --disabled-password --gecos "" build\
    && apt-get install -y xmlstarlet

# Download JDK
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
&& apt-get update \
&& apt-get install -y --no-install-recommends \
openjdk-8-jre-headless

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:${PATH}"
RUN update-ca-certificates