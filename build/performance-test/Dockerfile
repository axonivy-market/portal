# Step 1
FROM ubuntu:20.04

# Step 2
ARG JMETER_VERSION="5.5"
ENV JMETER_HOME /opt/apache-jmeter-${JMETER_VERSION}
ENV JMETER_PLUGINS_FOLDER ${JMETER_HOME}/lib/ext/

# Step 3:
WORKDIR ${JMETER_HOME}
RUN  apt-get -y update \
&& apt-get install -y wget gnupg unzip
# Step 4:
# Download Apache JMeter
RUN wget https://archive.apache.org/dist/jmeter/binaries/apache-jmeter-${JMETER_VERSION}.tgz
RUN tar -xzf apache-jmeter-${JMETER_VERSION}.tgz
RUN mv apache-jmeter-${JMETER_VERSION}/* /opt/apache-jmeter-${JMETER_VERSION}
RUN rm -r /opt/apache-jmeter-${JMETER_VERSION}/apache-jmeter-${JMETER_VERSION}

RUN wget -O tag-jmeter-extn-1.1.zip https://www.vinsguru.com/download/87/
RUN unzip tag-jmeter-extn-1.1.zip
RUN mv tag-jmeter-extn-1.1.jar lib/ext

# Step 5:
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
&& apt-get update \
&& apt-get install -y --no-install-recommends \
openjdk-8-jre-headless
# Step 6:
# Download Command Runner and move it to lib folder
#WORKDIR ${JMETER_LIB_FOLDER}
#RUN wget https://repo1.maven.org/maven2/kg/apc/cmdrunner/${CMDRUNNER_JAR_VERSION}/cmdrunner-${CMDRUNNER_JAR_VERSION}.jar
# Step 7:
# Download JMeter Plugins manager and move it to lib/ext folder
#WORKDIR ${JMETER_PLUGINS_FOLDER}
#RUN wget https://repo1.maven.org/maven2/kg/apc/jmeter-plugins-manager/${JMETER_PLUGINS_MANAGER_VERSION}/jmeter-plugins-manager-${JMETER_PLUGINS_MANAGER_VERSION}.jar
# Step 8:
#WORKDIR ${JMETER_LIB_FOLDER}
#RUN java  -jar cmdrunner-2.2.1.jar --tool org.jmeterplugins.repository.PluginManagerCMD install-all-except jpgc-hadoop,jpgc-oauth,ulp-jmeter-autocorrelator-plugin,ulp-jmeter-videostreaming-plugin,ulp-jmeter-gwt-plugin,tilln-iso8583
# Step 9:
WORKDIR ${JMETER_HOME}
# Step 10:
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:${PATH}"
RUN update-ca-certificates
# COPY tag-jmeter-extn-1.1.jar ${JMETER_PLUGINS_FOLDER}/tag-jmeter-extn-1.1.jar
# COPY portal-jmeter ${JMETER_HOME}/bin/portal-jmeter