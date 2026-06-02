FROM selenium/standalone-firefox:4 AS selenium

FROM maven:3.9.12-eclipse-temurin-25

COPY --from=selenium /usr/bin/firefox /usr/bin/firefox
COPY --from=selenium /usr/bin/geckodriver /usr/bin/geckodriver

# Create a group and user with the specified IDs, install Firefox system dependencies
RUN apt-get update && apt-get install -y --no-install-recommends \
    passwd adduser zip unzip openssh-client \
    libgtk-3-0 libdbus-glib-1-2 libxt6 libx11-xcb1 \
    && addgroup --gid 1001 build && adduser --uid 1001 --gid 1001 --disabled-password --gecos "" build
