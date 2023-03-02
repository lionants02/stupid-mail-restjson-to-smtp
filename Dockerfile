FROM openjdk:17-alpine
RUN apk add --no-cache curl
WORKDIR /stupid_mail
COPY build/libs/thanachai.nstda.th.stupid-mail-all.jar .
EXPOSE 8080/tcp
CMD ["java","-Xmx2000m","-Dfile.encoding=UTF-8", "-jar", "thanachai.nstda.th.stupid-mail-all.jar"]