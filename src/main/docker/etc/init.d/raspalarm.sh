#!/sbin/openrc-run

name=raspalarm

start() {
        ebegin "Starting $name"
        sudo /etc/init.d/lighttpd start
        sudo /etc/init.d/mariadb start
        sudo /etc/init.d/php-fpm start
        sudo /etc/init.d/zoneminder start
        java -jar /app.jar &
        eend $?
}

stop() {
        ebegin "Stopping $name"
        pkill -f java -jar
        sudo /etc/init.d/zoneminder stop
        sudo /etc/init.d/php-fpm stop
        sudo /etc/init.d/mariadb stop
        sudo /etc/init.d/lighttpd stop
        eend $?
}