FROM maven:3.9.8-eclipse-temurin-21

# Create a group and user with the specified IDs
RUN apt-get update && apt-get install -y --no-install-recommends \
    passwd adduser\
    && apt-get install -y openssh-client\
    && addgroup --gid 1001 build && adduser --uid 1001 --gid 1001 --disabled-password --gecos "" build\
    && apt-get install -y xmlstarlet

ENV JDK_DOWNLOAD_URL https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.6%2B7/OpenJDK21U-jdk_x64_alpine-linux_hotspot_21.0.6_7.tar.gz

RUN curl -L --fail --silent --show-error --retry 3 $JDK_DOWNLOAD_URL \
    | tar -xz -C /opt/

ENV JAVA_HOME=/opt/jdk-21
ENV PATH="$JAVA_HOME/bin:${PATH}"

RUN java -version
RUN echo $JAVA_HOME