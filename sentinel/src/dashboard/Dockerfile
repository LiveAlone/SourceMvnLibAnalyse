FROM openjdk:11

# set environment
ENV BASE_DIR="/home/sentinel" \
    SERVER_PORT="8090" \
    DASHBOARD_SERVER="localhost:8090" \
    PROJECT_NAME="sentinel-dashboard" \
    JAVA_OPTS="" \
    TIME_ZONE="Asia/Shanghai"

ARG SENTINEL_DASHBOARD_VERSION=1.8.4

WORKDIR /$BASE_DIR

RUN set -x \
    && wget https://github.com/alibaba/Sentinel/releases/download/${SENTINEL_DASHBOARD_VERSION}/sentinel-dashboard-${SENTINEL_DASHBOARD_VERSION}.jar -P $BASE_DIR \
    && ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo '$TIME_ZONE' > /etc/timezone

ADD bin/docker-entrypoint.sh bin/docker-entrypoint.sh

# set startup log dir
RUN mkdir -p logs \
	&& cd logs \
	&& touch start.out \
	&& ln -sf /dev/stdout start.out \
	&& ln -sf /dev/stderr start.out
RUN chmod +x bin/docker-entrypoint.sh

EXPOSE 8090
ENTRYPOINT ["bin/docker-entrypoint.sh"]
