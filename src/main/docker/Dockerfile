FROM @dockerContainerFrom@
RUN pacman -Syyu --noconfirm
RUN pacman -S jre8-openjdk-headless --noconfirm
ADD @dockerContainerJarName@-@dockerContainerJarVersion@.jar app.jar
ADD fcmjava.properties fcmjava.properties
ENTRYPOINT [ "java", "-jar", "/app.jar" ]