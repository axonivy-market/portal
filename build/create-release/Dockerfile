FROM maven:3.9.9-eclipse-temurin-21

# Create a group and user with the specified IDs
RUN apt-get update && apt-get install -y --no-install-recommends \
    passwd adduser\
    && apt-get install -y openssh-client\
    && addgroup --gid 1001 build && adduser --uid 1001 --gid 1001 --disabled-password --gecos "" build\
    && apt-get install -y xmlstarlet