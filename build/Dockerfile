FROM maven:3.10.0-eclipse-temurin-25

ARG MAVEN_VERSION=3.10.1
ARG MAVEN_SHA512=8af2fcc48ee6b8f8fc0b21054226e14404b88500df6483cdeb3ae603408f6de27b1397ac591a9fb0d98fee64f4f624fee1383efd25b8998786cda1598fb9ea39

# Create a group and user with the specified IDs
RUN apt-get update && apt-get install -y --no-install-recommends \
    passwd adduser zip unzip curl \
    && apt-get install -y openssh-client\
    && addgroup --gid 1001 build && adduser --uid 1001 --gid 1001 --disabled-password --gecos "" build

RUN curl -fsSL "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/${MAVEN_VERSION}/apache-maven-${MAVEN_VERSION}-bin.tar.gz" -o /tmp/apache-maven.tar.gz \
    && echo "${MAVEN_SHA512}  /tmp/apache-maven.tar.gz" | sha512sum --check --status \
    && tar -xzf /tmp/apache-maven.tar.gz -C /opt \
    && rm /tmp/apache-maven.tar.gz

ENV MAVEN_HOME=/opt/apache-maven-3.10.1
ENV PATH="/opt/apache-maven-3.10.1/bin:${PATH}"
