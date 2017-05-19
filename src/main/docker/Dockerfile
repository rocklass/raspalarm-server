FROM frolvlad/alpine-oraclejdk8:slim
ADD raspalarm-server-0.1.0.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]