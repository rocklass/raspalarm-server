FROM stephank/archlinux:latest
#FROM stephank/archlinux:arm-latest
RUN pacman -Syyu --noconfirm
RUN pacman -S jre8-openjdk-headless --noconfirm
ADD raspalarm-server-0.1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app.jar" ]