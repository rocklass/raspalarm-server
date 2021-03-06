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
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk/jre
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin
ENV JAVA_VERSION 8u131
ENV JAVA_ALPINE_VERSION 8.131.11-r2
RUN set -x \
    && apk add --update --no-cache openjdk8-jre="$JAVA_ALPINE_VERSION" \
    && [ "$JAVA_HOME" = "$(docker-java-home)" ]

# install packages
RUN apk --update --no-cache add openrc bash curl zoneminder mysql mysql-client lighttpd php5-fpm php5-pdo php5-pdo_mysql php5-cgi php5-ctype

# configure openrc
RUN sed -i 's/#rc_sys=""/rc_sys="lxc"/g' /etc/rc.conf \
    && echo 'rc_provide="loopback net"' >> /etc/rc.conf \
    && sed -i 's/^#\(rc_logger="YES"\)$/\1/' /etc/rc.conf \
    && sed -i '/tty/d' /etc/inittab \
    && sed -i 's/hostname $opts/# hostname $opts/g' /etc/init.d/hostname \
    && sed -i 's/mount -t tmpfs/# mount -t tmpfs/g' /lib/rc/sh/init.sh \
    && sed -i 's/cgroup_add_service /# cgroup_add_service /g' /lib/rc/sh/openrc-run.sh

# configure zoneminder
RUN MYSQL_DATABASE="zm" \
    && MYSQL_USER="@mySqlUser@" \
    && MYSQL_PASSWORD="@mySqlPassword@" \
    && mkdir -p /run/mysqld \
    && chown -R mysql:mysql /run/mysqld \
    && chown -R mysql:mysql /var/lib/mysql \
    && mysql_install_db --user=mysql > /dev/null \
    && tfile=`mktemp` \
    && echo "USE mysql;" >> $tfile \
    && echo "FLUSH PRIVILEGES;" >> $tfile \
    && echo "CREATE DATABASE $MYSQL_DATABASE;" >> $tfile \
    && echo "CREATE USER $MYSQL_USER@localhost IDENTIFIED BY '$MYSQL_PASSWORD';" >> $tfile \
    && echo "GRANT ALL PRIVILEGES on *.* to $MYSQL_USER@localhost;" >> $tfile \
    && cat /usr/share/zoneminder/db/zm_create.sql >> $tfile \
    && /usr/bin/mysqld --user=mysql --bootstrap --verbose < $tfile \
    && rm -f $tfile \
    && sed -i 's/\(pdo_mysql.default_socket\).*/pdo_mysql.default_socket=\/run\/mysqld\/mysqld.sock/g' /etc/php5/php.ini \
    && sed -i 's/\(date.timezone\).*/date.timezone="@phpTimeZone@"/g' /etc/php5/php.ini \
    && sed -i 's/^;\{0,1\}\(user\) .*/user = lighttpd/g' /etc/php5/php-fpm.conf \
    && sed -i 's/^;\{0,1\}\(group\) .*/group = lighttpd/g' /etc/php5/php-fpm.conf \
    && sed -i 's/^#\(\( \)*include "mod_cgi.conf"\)$/\1/' /etc/lighttpd/lighttpd.conf \
    && sed -i 's/^#\(\( \)*include "mod_fastcgi_fpm.conf"\)$/\1/' /etc/lighttpd/lighttpd.conf \
    && sed -i '/\( \)*cgi.assign/a "" => "",' /etc/lighttpd/mod_cgi.conf \
    && sed -i.bak 's/"\/usr\/bin\/php-cgi"/"\/usr\/bin\/php-cgi5"/g' /etc/lighttpd/mod_fastcgi.conf \
    && sed -i 's/\(ZM_WEB_USER\).*/ZM_WEB_USER=lighttpd/g' /etc/zm.conf \
    && sed -i 's/\(ZM_WEB_GROUP\).*/ZM_WEB_GROUP=lighttpd/g' /etc/zm.conf \
    && sed -i 's/\(ZM_DB_TYPE\).*/ZM_DB_TYPE=mysql/g' /etc/zm.conf \
    && sed -i 's/\(ZM_DB_HOST\).*/ZM_DB_HOST=localhost/g' /etc/zm.conf \
    && sed -i 's/\(ZM_DB_NAME\).*/ZM_DB_NAME=zm/g' /etc/zm.conf \
    && sed -i 's/\(ZM_DB_USER\).*/ZM_DB_USER=@mySqlUser@/g' /etc/zm.conf \
    && sed -i 's/\(ZM_DB_PASS\).*/ZM_DB_PASS=@mySqlPassword@/g' /etc/zm.conf \
    && sed -i 's/\(ZM_SERVER_HOST\).*/ZM_SERVER_HOST=/g' /etc/zm.conf \
    && chown lighttpd.lighttpd /etc/zm.conf \
    && chown lighttpd.lighttpd /var/log/zoneminder \
    && addgroup lighttpd video

# install and configure motion
RUN echo http://dl-cdn.alpinelinux.org/alpine/edge/testing >> /etc/apk/repositories \
    && apk --update --no-cache add motion \
    && sed -i 's/\(webcam_localhost\).*/webcam_localhost off/g' /etc/motion/motion-dist.conf \
    && sed -i 's/\(control_localhost\).*/control_localhost off/g' /etc/motion/motion-dist.conf \
    && sed -i 's/\(webcam_port\).*/webcam_port 8081/g' /etc/motion/motion-dist.conf \
    && sed -i 's/\(control_port\).*/control_port 8082/g' /etc/motion/motion-dist.conf

# install raspalarm app
ADD @dockerContainerJarName@-@dockerContainerJarVersion@.jar app.jar
ADD fcmjava.properties fcmjava.properties
ADD etc/init.d/raspalarm.sh /etc/init.d/raspalarm

# define start services
RUN rc-update add lighttpd default \
    && rc-update add mariadb default \
    && rc-update add php-fpm default \
    && rc-update add zoneminder default \
    && rc-update add raspalarm default \
    && rc-update add motion default

VOLUME ["/var/lib/mysql", "/var/cache/zoneminder"]

EXPOSE 80 8080 8081

CMD ["/sbin/init"]
