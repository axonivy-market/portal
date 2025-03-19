FROM maven:3.9.8-eclipse-temurin-21

# Create a group and user with the specified IDs
RUN apt-get update && apt-get install -y --no-install-recommends \
    passwd adduser\
    && apt-get install -y openssh-client\
    && addgroup --gid 1001 build && adduser --uid 1001 --gid 1001 --disabled-password --gecos "" build\
    && apt-get install -y xmlstarlet\
    && curl \
    && rm -rf /var/lib/apt/lists/*

RUN curl -L --fail --silent --show-error --retry 3 https://download.java.net/java/early_access/jdk21/5/GPL/openjdk-21-ea+5_linux-x64_bin.tar.gz \
    | tar -xz -C /opt/

ENV JAVA_HOME /opt/jdk-21
ENV PATH $JAVA_HOME/bin:$PATH

    # Verify Java installation
RUN java -version
RUN echo $JAVA_HOME