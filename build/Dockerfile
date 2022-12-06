FROM maven:3.8.6-eclipse-temurin-17 AS m2-cache

RUN addgroup --gid 1000 build && adduser --uid 1000 --gid 1000 --disabled-password --gecos "" build

# copy all poms and project root files. See .dockerignore
COPY . /usr/src/mvn
WORKDIR /usr/src/mvn
USER build
# downloads dependencies ('mvn dependency:go-offline' is not working with tycho)
RUN mvn --batch-mode --settings maven/settings.xml --threads 4 validate 

FROM maven:3.8.6-eclipse-temurin-17

RUN addgroup --gid 1000 build && adduser --uid 1000 --gid 1000 --disabled-password --gecos "" build

# install iproute2 which contains ss (a native program to get all open sockets, needed in some ci tests)
RUN apt-get update && \
    apt-get install -y iproute2 && \
    rm -rf /var/lib/apt/lists/*

COPY --from=m2-cache /home/build/.m2/ /home/build/.m2/