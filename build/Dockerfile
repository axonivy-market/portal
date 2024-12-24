FROM maven:3.9.8-eclipse-temurin-21

# Install groupadd and useradd commands
RUN apt-get update && apt-get install -y --no-install-recommends \
    shadow-utils \
    && rm -rf /var/lib/apt/lists/*

# Create a group and user with the specified IDs
RUN groupadd --gid 1001 build && \
    useradd --uid 1001 --gid 1001 --no-create-home --shell /bin/false build
