FROM maven:3.8.6-eclipse-temurin-17

RUN addgroup --gid 1001 build && adduser --uid 1001 --gid 1001 --disabled-password --gecos "" build \
    && apt-get update && apt-get install -y --no-install-recommends \
    && apt-get install -y python3