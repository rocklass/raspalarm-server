FROM @dockerContainerFrom@
RUN pacman -Syyu --noconfirm
RUN pacman -S jre8-openjdk-headless --noconfirm
ADD @dockerContainerJarName@-@dockerContainerJarVersion@.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]