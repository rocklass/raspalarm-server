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

# install packages
RUN apk --update --no-cache add openrc zoneminder mysql mysql-client lighttpd php5-fpm php5-pdo php5-pdo_mysql

# configure openrc
RUN sed -i 's/#rc_sys=""/rc_sys="lxc"/g' /etc/rc.conf \
    && echo 'rc_provide="loopback net"' >> /etc/rc.conf \
    && sed -i 's/^#\(rc_logger="YES"\)$/\1/' /etc/rc.conf \
    && sed -i '/tty/d' /etc/inittab \
    && sed -i 's/hostname $opts/# hostname $opts/g' /etc/init.d/hostname \
    && sed -i 's/mount -t tmpfs/# mount -t tmpfs/g' /lib/rc/sh/init.sh \
    && sed -i 's/cgroup_add_service /# cgroup_add_service /g' /lib/rc/sh/openrc-run.sh

# create zoneminder database
RUN mkdir -p /run/mysqld \
    && chown -R mysql:mysql /run/mysqld \
    && chown -R mysql:mysql /var/lib/mysql \
    && mysql_install_db --user=mysql > /dev/null \
    && tfile=`mktemp` \
    && MYSQL_DATABASE="zm" \
    && MYSQL_USER="zmuser" \
    && MYSQL_PASSWORD="zmuser" \
    && MYSQL_ROOT_PASSWORD="root_password" \
    && echo "USE mysql;" >> $tfile \
    && echo "FLUSH PRIVILEGES;" >> $tfile \
    && echo "CREATE DATABASE $MYSQL_DATABASE;" >> $tfile \
    && echo "CREATE USER $MYSQL_USER@localhost IDENTIFIED BY '$MYSQL_PASSWORD';" >> $tfile \
    && echo "GRANT CREATE, INSERT, SELECT, DELETE, UPDATE, DROP on $MYSQL_DATABASE.* to $MYSQL_USER@localhost;" >> $tfile \
    && cat $tfile \
    && /usr/bin/mysqld --user=mysql --bootstrap --verbose=0 < $tfile \
    && rm -f $tfile

# install raspalarm app
ADD @dockerContainerJarName@-@dockerContainerJarVersion@.jar app.jar
ADD fcmjava.properties fcmjava.properties
ADD etc/init.d/raspalarm.sh /etc/init.d/raspalarm

# define start services
RUN rc-update add mariadb
RUN rc-update add raspalarm

VOLUME ["/var/lib/mysql"]

CMD ["/sbin/init"]
