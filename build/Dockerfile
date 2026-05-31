FROM maven:3.9.12-eclipse-temurin-25

# Copy a version-pinned Firefox + matched geckodriver from the official Selenium
# image (Docker Hub) so we don't depend on packages.mozilla.org at build time or
# on a geckodriver download from GitHub at test time.
COPY --from=selenium/standalone-firefox:4 /usr/lib/firefox /usr/lib/firefox
COPY --from=selenium/standalone-firefox:4 /usr/bin/geckodriver /usr/bin/geckodriver

# Create a group and user with the specified IDs.
# Install Firefox's runtime system libraries (GTK/X11/audio) from the Ubuntu archive.
RUN ln -sf /usr/lib/firefox/firefox /usr/bin/firefox \
    && apt-get update && apt-get install -y --no-install-recommends \
       passwd adduser zip unzip openssh-client \
       libgtk-3-0t64 libasound2t64 libdbus-glib-1-2 libx11-xcb1 libxcb-shm0 \
    && rm -rf /var/lib/apt/lists/* \
    && addgroup --gid 1001 build && adduser --uid 1001 --gid 1001 --disabled-password --gecos "" build
