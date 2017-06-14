# base image
FROM @dockerContainerFrom@

# define file encoding
ENV LANG C.UTF-8

# install and configure java
RUN { \
        echo '#!/bin/sh'; \
        echo 'set -e'; \
        echo; \
        echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
    } > /usr/local/bin/docker-java-home \
    && chmod +x /usr/local/bin/docker-java-home
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin
ENV JAVA_VERSION 8u131
ENV JAVA_ALPINE_VERSION 8.131.11-r1
RUN set -x \
    && apk add --update --no-cache openjdk8="$JAVA_ALPINE_VERSION" \
    && [ "$JAVA_HOME" = "$(docker-java-home)" ]

# install and configure zoneminder
RUN apk --update --no-cache add mariadb mariadb-client pwgen zoneminder lighttpd php5-fpm php5-pdo php5-pdo_mysql

# java app
ADD @dockerContainerJarName@-@dockerContainerJarVersion@.jar app.jar
ADD fcmjava.properties fcmjava.properties

# scripts
ADD scripts/run.sh /scripts/run.sh
RUN mkdir /scripts/pre-exec.d && \
    mkdir /scripts/pre-init.d && \
    chmod -R 755 /scripts


VOLUME ["/var/lib/mysql"]

ENTRYPOINT ["/scripts/run.sh"]
