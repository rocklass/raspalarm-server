#!/sbin/openrc-run

name=ScreenMotion

start() {
        ebegin "Starting $name"
        sudo -u video_surveillance /usr/bin/screen -d -m -S motion /usr/bin/motion -c /opt/script/motion/motion.conf
        eend $?
}

stop() {
        ebegin "Stopping $name"
        sudo -u video_surveillance /usr/bin/screen -S motion -X quit
        eend $?
}