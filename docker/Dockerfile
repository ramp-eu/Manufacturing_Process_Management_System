

ARG NODE_VERSION=10.19.0-slim
FROM node:${NODE_VERSION}
#ARG GITHUB_ACCOUNT=jason-fox
#ARG GITHUB_REPOSITORY=TTE.project1
#ARG DOWNLOAD=latest
#ARG SOURCE_BRANCH=master

#ENV GITHUB_ACCOUNT=${GITHUB_ACCOUNT}
#ENV GITHUB_REPOSITORY=${GITHUB_REPOSITORY}
#ENV DOWNLOAD=${DOWNLOAD}

#RUN apt-get update && \
#	# Ensure that unzip, wget and curl are installed
#	apt-get install -y --no-install-recommends unzip ca-certificates curl wget && \
#	if [ "${DOWNLOAD}" = "latest" ] ; \
#	then \
#		RELEASE="${SOURCE_BRANCH}"; \
#		echo "INFO: Building Latest Development from ${SOURCE_BRANCH} branch."; \
#	elif [ "${DOWNLOAD}" = "stable" ]; \
#	then \
#		RELEASE=$(curl -s https://api.github.com/repos/"${GITHUB_ACCOUNT}"/"${GITHUB_REPOSITORY}"/releases/latest | grep 'tag_name' | cut -d\" -f4); \
#		echo "INFO: Building Latest Stable Release: ${RELEASE}"; \
#	else \
#	 	RELEASE="${DOWNLOAD}"; \
#	 	echo "INFO: Building Release: ${RELEASE}"; \
#	fi && \
#	RELEASE_CONCAT=$(echo "${RELEASE}" | tr / -); \
#	wget --no-check-certificate -O source.zip https://github.com/"${GITHUB_ACCOUNT}"/"${GITHUB_REPOSITORY}"/archive/"${RELEASE}".zip && \
#	unzip source.zip && \
#	rm source.zip && \
#	mv "${GITHUB_REPOSITORY}-${RELEASE_CONCAT}" /opt/component && \
#	# Remove unzip and clean apt cache
#	apt-get clean && \
#	apt-get remove -y unzip && \
#	apt-get -y autoremove && \
#	rm -rf /var/lib/apt/lists/*

 # WORKDIR /opt/component

#RUN \
#	# Ensure that Git is installed prior to running npm install
#	apt-get update && \
#	apt-get install -y --no-install-recommends git && \
#	npm install pm2@3.2.2 -g && \
#	echo "INFO: npm install --production..." && \
#	npm install --production && \
#	# Remove Git and clean apt cache
#	apt-get clean && \
#	apt-get remove -y git && \
#	apt-get -y autoremove && \
#	chmod +x docker/entrypoint.sh && \
#	apt-get clean && \
#	rm -rf /var/lib/apt/lists/*

# Install Updates
#RUN apt-get update -y && \
#     apt-get upgrade -y && \
#     apt-get install -y software-properties-common && \
#     apt-add-repository -y ppa:openjdk-r/ppa && \
#     apt-get update -y && \
#     rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* /var/cache/*


FROM maven:3-jdk-11 as builder
# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
#Download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins
#Copy source code
COPY src /build/src
# Build application
RUN mvn package spring-boot:repackage

#USER node
#ENV NODE_ENV=production

FROM openjdk:11-slim as runtime
COPY --from=builder build/target/*.jar mpms.jar
RUN chmod +x mpms.jar
RUN ls -la /mpms.jar
EXPOSE 8080

RUN mkdir -p /script
WORKDIR /script
COPY docker/entrypoint.sh /script
RUN chmod +x /script/entrypoint.sh

#RUN ./entrypoint.sh



ENTRYPOINT ["sh","/script/entrypoint.sh"]
