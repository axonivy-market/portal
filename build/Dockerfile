FROM maven:3.9.8-eclipse-temurin-21

# Install necessary tools if groupadd and useradd are not available
RUN apt-get update && apt-get install -y --no-install-recommends \
    passwd \
    && rm -rf /var/lib/apt/lists/*

# Create a group and user with the specified IDs
RUN addgroup --gid 1001 build && adduser --uid 1001 --gid 1001 --disabled-password --gecos "" build
