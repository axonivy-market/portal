FROM maven:3.9.12-eclipse-temurin-25

# Create a group and user with the specified IDs
RUN apt-get update && apt-get install -y --no-install-recommends \
    passwd adduser\
    && apt-get install -y openssh-client\
    && apt-get install -y xmlstarlet\
    && addgroup --gid 1001 build && adduser --uid 1001 --gid 1001 --disabled-password --gecos "" build\
    && apt-get install -y curl gnupg\
    && curl -fsSL https://cli.github.com/packages.gpg | apt-key add -\
    && echo "deb https://cli.github.com/packages focal main" | tee /etc/apt/sources.list.d/github-cli.list > /dev/null\
    && apt-get update && apt-get install -y gh
