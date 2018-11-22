FROM java:8
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

COPY  ./deploy/target/officedict.deploy-1.0-SNAPSHOT.jar /usr/share

EXPOSE 8081
ENTRYPOINT java -jar /usr/share/officedict.deploy-1.0-SNAPSHOT.jar
