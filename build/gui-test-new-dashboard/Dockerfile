FROM maven:3.9.9-eclipse-temurin-21

# 1. Install Firefox dependencies + Helper tools
# Detect OS version or try installing both variants to be safe
RUN apt-get update && apt-get install -y --no-install-recommends \
    # Firefox Core Dependencies
    libgtk-3-0 \
    libdbus-glib-1-2 \
    libx11-xcb1 \
    libxt6 \
    # Rendering (needed for screenshots)
    libpango-1.0-0 \
    libcairo2 \
    libgdk-pixbuf-2.0-0 \
    && rm -rf /var/lib/apt/lists/*

# 2. Create Group and User
RUN addgroup --gid 1001 build \
    && adduser --uid 1001 --gid 1001 --disabled-password --gecos "" build

USER build
