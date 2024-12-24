FROM ubuntu:20.04

ARG JMETER_VERSION="5.5"
ENV JMETER_HOME /opt/apache-jmeter-${JMETER_VERSION}
ENV JMETER_PLUGINS_FOLDER ${JMETER_HOME}/lib/ext/

WORKDIR ${JMETER_HOME}
RUN  apt-get -y update \
&& apt-get install -y wget gnupg unzip passwd

# Download Apache JMeter
RUN wget https://archive.apache.org/dist/jmeter/binaries/apache-jmeter-${JMETER_VERSION}.tgz
RUN tar -xzf apache-jmeter-${JMETER_VERSION}.tgz
RUN mv apache-jmeter-${JMETER_VERSION}/* /opt/apache-jmeter-${JMETER_VERSION}
RUN rm -r /opt/apache-jmeter-${JMETER_VERSION}/apache-jmeter-${JMETER_VERSION}

# Download Property File Reader plugin
RUN wget -O tag-jmeter-extn-1.1.zip https://www.vinsguru.com/download/87/
RUN unzip tag-jmeter-extn-1.1.zip
RUN mv tag-jmeter-extn-1.1.jar lib/ext

# Download JDK
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
&& apt-get update \
&& apt-get install -y --no-install-recommends \
openjdk-8-jre-headless

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:${PATH}"
RUN update-ca-certificates
