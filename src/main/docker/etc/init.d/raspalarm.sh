#!/sbin/openrc-run

name=raspalarm

start() {
        ebegin "Starting $name"
        java -jar /app.jar
        eend $?
}

stop() {
        ebegin "Stopping $name"
        pkill -f java -jar
        eend $?
}