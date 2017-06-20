raspalarm-server
================
Custom alarm web app for Raspberry Pi

Build
-----
```gradle
./gradlew clean build dockerBuildImage -Penv=dev
```

Run
---
```shell
docker run -it -p 80:80 -p 8080:8080 -p 8081:8081 --device=/dev/video0 rocklass/raspalarm-server
```
