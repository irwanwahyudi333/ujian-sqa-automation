# Base image menggunakan Eclipse Temurin Java 21 (Alpine) yang stabil dan bebas rate limit public ECR
FROM eclipse-temurin:21-jre-alpine

ARG RELEASE=2.29.0
ARG ALLURE_REPO=https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline

# Install tools yang dibutuhkan (bash, wget, tar, gzip, coreutils, curl, findutils)
RUN apk update && \
    apk add --no-cache bash wget tar gzip coreutils findutils curl && \
    rm -rf /var/cache/apk/*

# Unduh dan pasang Allure CLI dengan retry logic
RUN for i in 1 2 3; do \
        wget --no-verbose -O /tmp/allure-$RELEASE.tgz $ALLURE_REPO/$RELEASE/allure-commandline-$RELEASE.tgz && break || sleep 2; \
    done && \
    tar -zxvf /tmp/allure-$RELEASE.tgz -C / && \
    rm -rf /tmp/* && \
    chmod -R +x /allure-$RELEASE/bin

ENV ROOT=/app \
    PATH=$PATH:/allure-$RELEASE/bin

RUN mkdir -p $ROOT
WORKDIR $ROOT
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
