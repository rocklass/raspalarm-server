FROM @dockerContainerFrom@
#Add repo archlinuxfr to Pacman
RUN echo '[archlinuxfr]' >> /etc/pacman.conf
RUN echo 'SigLevel = Never' >> /etc/pacman.conf
RUN echo 'Server = http://repo.archlinux.fr/$arch' >> /etc/pacman.conf
#Install dependencies
RUN pacman -Syyu --noconfirm
RUN pacman -S base-devel yaourt jre8-openjdk-headless apache --noconfirm; exit 0
RUN yaourt -Syua --noconfirm
#Create user raspalarm
RUN useradd -ms /bin/bash raspalarm
RUN echo 'raspalarm ALL=NOPASSWD: ALL' >> /etc/sudoers
USER raspalarm
#Install zoneminder
RUN yaourt -S zoneminder --noconfirm; exit 0

ADD @dockerContainerJarName@-@dockerContainerJarVersion@.jar app.jar
ADD fcmjava.properties fcmjava.properties
ENTRYPOINT [ "java", "-jar", "/app.jar" ]